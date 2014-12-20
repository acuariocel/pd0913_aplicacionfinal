package vista;

import controlador.Funciones;
import controlador.FuncionesEquipo;
import controlador.FuncionesUsuario;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JButton;
import modelo.Bloquea;
import javax.swing.JOptionPane;
import modelo.Equipos;
import modelo.Usuarios;

/**
 * @web http://www.diegoacuario.blogspot.com
 * @author diegoacuario
 */
public final class Login extends javax.swing.JFrame {

    private final String ipServidor;
    private Usuarios u;
    private String miIp = null;
    private String urlSerWeb;
    private String ipServicioWeb = null;
    private int x = 0;
    boolean isConected = false;
    private Funciones f;
    private FuncionesEquipo fe;
    private FuncionesUsuario fu;
    private Equipos eqp;

    /**
     * Creates new form jFrameBlocked
     *
     * @param estado
     */
    public Login(int estado) {
        f = new Funciones();
        fe = new FuncionesEquipo();
        fu = new FuncionesUsuario();
        ipServidor = Funciones.getFileProperties("classes/confi.properties").getProperty("ip_servidor");
        urlSerWeb = Funciones.getFileProperties("classes/confi.properties").getProperty("servicio_web");
        try {
            miIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            System.out.println(e);
            miIp = null;
        }
        if (miIp == null) {
            JOptionPane.showMessageDialog(rootPane, "No se puede obtener ip del equipo verifique su conexión a la red.");
            System.exit(0);
        } else if (miIp.equals("127.0.0.1")) {
            JOptionPane.showMessageDialog(rootPane, "El equipo se encuentra con la ip 127.0.0.1, verifique su conexión a la red.");
            System.exit(0);
        }
        setUndecorated(true);
        initComponents();
        try {
            eqp = fe.obtieneDatosEquipo(f.obtieneJsonGet(Funciones.getFileProperties("classes/confi.properties").getProperty("servicio_web") + "webresources/modelo.equipos/ip=" + miIp));
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(rootPane, "Error al verificar si equipo esta registrado, verifique la consola");
            System.exit(0);
        }
        if (!conectar()) {
            JOptionPane.showMessageDialog(rootPane, "No se puede concetar al servicio web verifique su archivo de configuración.");
            System.exit(0);
        }

        if (eqp == null && !ipServidor.equals(miIp) && estado == 1) {
            JOptionPane.showMessageDialog(rootPane, "Equipo no esta registrado, ingrese como administrador y regístrelo");
        }

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);//maximizado
        setAlwaysOnTop(true);//siempre al frente
        new Bloquea(this).block(); //nueva instancia de Bloquea pasando como parametros e este JFrame
    }

    public JButton getBtnEntrar() {
        return btnEntrar;
    }

    public void setBtnEntrar(JButton btnEntrar) {
        this.btnEntrar = btnEntrar;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public void setBtnRegistrar(JButton btnRegistrar) {
        this.btnRegistrar = btnRegistrar;
    }

    public boolean conectar() {
        int port;
        try {
            port = Integer.parseInt(urlSerWeb.split("/")[2].split(":")[1]);
        } catch (Exception e) {
            if ((e + "").contains("ArrayIndexOutOfBoundsException")) {
                port = 80;
            } else {
                return false;
            }
        }
        try {
            ipServicioWeb = urlSerWeb.split("/")[2].split(":")[0];
            if (ipServidor.equals(miIp)) {
                btnEntrar.setToolTipText("Ingresar al servidor");
            } else {
                btnEntrar.setToolTipText("Ingresar al equipo");
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();
        btnEntrar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        txtClave = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Calibri Light", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Cédula: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(13, 15, 10, 3);
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Calibri Light", 1, 36)); // NOI18N
        jLabel2.setText("Contraseña: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(13, 15, 10, 3);
        jPanel2.add(jLabel2, gridBagConstraints);

        txtCedula.setFont(new java.awt.Font("Calibri Light", 0, 36)); // NOI18N
        txtCedula.setPreferredSize(new java.awt.Dimension(200, 32));
        txtCedula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCedulaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCedulaFocusLost(evt);
            }
        });
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 45;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 0.8;
        gridBagConstraints.insets = new java.awt.Insets(13, 0, 0, 22);
        jPanel2.add(txtCedula, gridBagConstraints);

        btnSalir.setFont(new java.awt.Font("Calibri Light", 1, 36)); // NOI18N
        btnSalir.setText("Enviar clave");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 40, 8);
        jPanel2.add(btnSalir, gridBagConstraints);

        btnEntrar.setFont(new java.awt.Font("Calibri Light", 1, 36)); // NOI18N
        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 40, 8);
        jPanel2.add(btnEntrar, gridBagConstraints);

        btnRegistrar.setFont(new java.awt.Font("Calibri Light", 1, 36)); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 40, 8);
        jPanel2.add(btnRegistrar, gridBagConstraints);

        txtClave.setFont(new java.awt.Font("Calibri Light", 0, 36)); // NOI18N
        txtClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveActionPerformed(evt);
            }
        });
        txtClave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClaveKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(13, 0, 0, 22);
        jPanel2.add(txtClave, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(jPanel2, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        if (f.enviarContrasenia("acuariocel@gmail.com", "DIEGO ROMERO", "1234")) {
            JOptionPane.showMessageDialog(rootPane, "La clave fue enviada a su correo por favor revise su buzon de entrada", "Mensaje", 1);
        } else {
            JOptionPane.showMessageDialog(rootPane, "La clave no pudo ser enviada verifique su conexión a internet", "Mensaje", 0);

        }

    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        if (txtClave.getText().equals("admin") && txtCedula.getText().length() == 0) {
            new ClaveUsuario(this, rootPaneCheckingEnabled).setVisible(true);
        } else {
            if (txtCedula.getText().length() == 10 && !txtClave.getText().isEmpty() && btnEntrar.isEnabled()) {
                String url = Funciones.getFileProperties("classes/confi.properties").getProperty("servicio_web") + "webresources/modelo.usuarios/login/cedula=" + txtCedula.getText() + ",clave=" + txtClave.getText();
                String json = f.obtieneJsonGet(url);
                if (!json.isEmpty()) {
                    if (json.charAt(0) == '{') {
                        u = fu.obtieneDatosUsuario(json);
                        if (u != null) {
                            this.dispose();
                            System.out.println("ingresando al sistema");
                            new Menu(null, u, eqp).setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(this, "Usuario o contraseña erróneos");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Servidor web no esta levantado");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Usuario o contraseña erróneos");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña erróneos");
            }
        }

    }//GEN-LAST:event_btnEntrarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        new RegistraUsuario(this, rootPaneCheckingEnabled, false, u).setVisible(true);
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void txtClaveKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClaveKeyTyped

    }//GEN-LAST:event_txtClaveKeyTyped

    private void txtCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyReleased

    }//GEN-LAST:event_txtCedulaKeyReleased

    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
        char c = evt.getKeyChar();
        if (((c < '0' || c > '9')) || txtCedula.getText().length() >= 10) {
            evt.consume();
        }

    }//GEN-LAST:event_txtCedulaKeyTyped

    private void txtCedulaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCedulaFocusLost

    }//GEN-LAST:event_txtCedulaFocusLost

    private void txtCedulaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCedulaFocusGained

    }//GEN-LAST:event_txtCedulaFocusGained

    private void txtClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login(1).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JPasswordField txtClave;
    // End of variables declaration//GEN-END:variables
}
