package modelo;

import java.util.Date;

/**
 * @web http://www.diegoacuario.blogspot.com
 * @author diegoacuario
 */
public class Usuarios {

    private String cedula;
    private String clave;
    private String nombres;
    private String apellidos;
    private String correo;
    private String celular;
    private RolUsuarios idRolUsuario;
    private Integer idUsuario;
    private int bloqueado;
    private String codigo;
    private String fechaHoraRegistro;
    private String fechaHoraModificacion;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public RolUsuarios getIdRolUsuario() {
        return idRolUsuario;
    }

    public void setIdRolUsuario(RolUsuarios idRolUsuario) {
        this.idRolUsuario = idRolUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(int bloqueado) {
        this.bloqueado = bloqueado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFechaHoraRegistro() {
        return fechaHoraRegistro;
    }

    public void setFechaHoraRegistro(String fechaHoraRegistro) {
        this.fechaHoraRegistro = fechaHoraRegistro;
    }

    public String getFechaHoraModificacion() {
        return fechaHoraModificacion;
    }

    public void setFechaHoraModificacion(String fechaHoraModificacion) {
        this.fechaHoraModificacion = fechaHoraModificacion;
    }

   
}
