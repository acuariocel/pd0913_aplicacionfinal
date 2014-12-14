/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author CEL
 */
public class EnviaCorreo {

    public static void main(String[] args) {
        Funciones ec = new Funciones();
        System.out.println(ec.enviarContrasenia("sgeovanny222@gmail.com", "ARMIJOS ARMIJOS STALIN GEOVANNY", "1234"));

    }

    String miCorreo = "controleqpcel";
    String miContraseña = "loja1234";
    String servidorSMTP = "smtp.gmail.com";
    String puertoEnvio = "465";

    String asunto;
    String cuerpo;

    //metodo que envia el email
    public boolean EnviaEmail(String mailReceptor, String asunto, String cuerpo) {
        boolean res = false;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        Properties props = new Properties();//propiedades a agragar
        props.put("mail.smtp.user", this.miCorreo);//correo origen
        props.put("mail.smtp.host", servidorSMTP);//tipo de servidor
        props.put("mail.smtp.port", puertoEnvio);//puesto de salida
        props.put("mail.smtp.starttls.enable", "true");//inicializar el servidor
        props.put("mail.smtp.auth", "true");//autentificacion
        props.put("mail.smtp.socketFactory.port", puertoEnvio);//activar el puerto
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        SecurityManager security = System.getSecurityManager();

        try {
            Authenticator auth = new autentificadorSMTP();//autentificar el correo
            Session session = Session.getInstance(props, auth);//se inica una session
            // session.setDebug(true);

            MimeMessage msg = new MimeMessage(session);//se crea un objeto donde ira la estructura del correo
            // msg.setText(this.cuerpo);//seteo el cuertpo del mensaje
            msg.setSubject(this.asunto);//setea asunto (opcional)
            msg.setContentID("CEL");
            msg.setHeader("Mensaje", "Correcto");
            msg.setContent(this.cuerpo, "text/html");
            msg.setFrom(new InternetAddress(this.miCorreo));//agrega la la propiedad del correo origen
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mailReceptor)); //agrega el destinatario
            Transport.send(msg);//envia el mensaje
            res = true;
        } catch (MessagingException mex) {//en caso de que ocurra un error se crea una excepcion
            System.err.println(mex);
        }
        return res;
    }

    private class autentificadorSMTP extends Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(miCorreo, miContraseña);//autentifica tanto el correo como la contraseña
        }
    }

    public void escribir(String nombreArchivo, String texto) {
        File f;
        f = new File(nombreArchivo);
        try {
            FileWriter w = new FileWriter(f);
            try (BufferedWriter bw = new BufferedWriter(w); PrintWriter wr = new PrintWriter(bw)) {
                wr.write(texto);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public String fechaFormato(Date d) {
        Date fecha = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaActual = formateador.format(fecha);
        return fechaActual;
    }

    public String obtieneHost() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            return address.getHostName();
        } catch (UnknownHostException ex) {
            return null;
        }
    }

    public String obtieneIp() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            // Cogemos la IP 
            byte[] bIPAddress = address.getAddress();
            // IP en formato String
            String sIPAddress = "";
            for (int x = 0; x < bIPAddress.length; x++) {
                if (x > 0) {
                    // A todos los numeros les anteponemos
                    // un punto menos al primero    
                    sIPAddress += ".";
                }
                // Jugamos con los bytes y cambiamos el bit del signo
                sIPAddress += bIPAddress[x] & 255;
            }
            return sIPAddress;
        } catch (UnknownHostException ex) {
            return null;
        }
    }
}
