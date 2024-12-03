package gestionDeUsuarioV2;

public class UsuarioDTO {
    private String idUsuario;
    private String passwordMD5;
    private String tipoUsuario;
    private boolean activo;

    public UsuarioDTO(String idUsuario, String passwordMD5, String tipoUsuario, boolean activo) {
    	if(idUsuario.length()>=5 && passwordMD5.length()>=5) {
    		this.idUsuario = idUsuario;
            this.passwordMD5 = passwordMD5;
            this.tipoUsuario = tipoUsuario;
            this.activo = activo;
    	}
    	else {
    	System.out.println("LA CONTRASEÑA O PASSWORD DEBE SER MAYOR O IGUAL DE 5 CARACTERES");
    	}
        
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPasswordMD5() {
        return passwordMD5;
    }

    public void setPasswordMD5(String passwordMD5) {
        this.passwordMD5 = passwordMD5;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "idUsuario='" + idUsuario + '\'' +
                ", tipoUsuario='" + tipoUsuario + '\'' +
                ", activo=" + activo +
                '}';
    }
}