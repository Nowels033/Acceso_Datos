package gestionDeUsuarios;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Arrays;


public class User {
    private String id_userName;
    byte[] passwordMD5 =null;
    String strPasswordMD5 =null;

    private String horaUltimoAccesoCorrecto;
    private String horaUltimoAccesoErroneo;
    private String typeUser;
    private boolean activo = true;

    public User(String id_userName, String password, String typeUser) {



        if (id_userName.length() >= 5 && password.length() >= 5) {
            byte[] bytesOfMessage = null;
            try {
                bytesOfMessage = password.getBytes("UTF-8");
                MessageDigest md = MessageDigest.getInstance("MD5");
                this.passwordMD5 = md.digest(bytesOfMessage);
                strPasswordMD5 = passwordMD5 + "";
                this.id_userName = id_userName;
               // this.passwordMD5 = password;
                this.typeUser = typeUser;
                System.out.println("Usuario creado correctamente.");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }




        } else {
            throw new IllegalArgumentException("El login y la contraseña deben tener al menos 5 caracteres.");
        }

    }

    public String getId_userName() {
        return id_userName;
    }

    public void setId_userName(String id_userName) {


        if ( id_userName.length() >= 5) {

            this.id_userName = id_userName;
            System.out.println("Nombre de usuario cambiado correctamente.");

        } else {
            throw new IllegalArgumentException("El nombre de Usuario deben tener al menos 5 caracteres.");
        }
    }

    public byte[] getPasswordMD5() {
        return passwordMD5;
    }



    public void setPasswordMD5(String password) {
        if ( password.length() >= 5) {

            byte[] bytesOfMessage = null;
            try {
                bytesOfMessage = password.getBytes("UTF-8");
                MessageDigest md = MessageDigest.getInstance("MD5");

                this.passwordMD5 = md.digest(bytesOfMessage);

                System.out.println("Contraseña cambiada correctamente.");

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }


        } else {
            throw new IllegalArgumentException("La contraseña deben tener al menos 5 caracteres.");
        }

    }

    public String getHoraUltimoAccesoCorrecto() {
        return horaUltimoAccesoCorrecto;
    }

    public void setHoraUltimoAccesoCorrecto(String horaUltimoAccesoCorrecto) {
        this.horaUltimoAccesoCorrecto = horaUltimoAccesoCorrecto;
    }

    public String getHoraUltimoAccesoErroneo() {
        return horaUltimoAccesoErroneo;
    }

    public void setHoraUltimoAccesoErroneo(String horaUltimoAccesoErroneo) {
        this.horaUltimoAccesoErroneo = horaUltimoAccesoErroneo;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id_userName='" + getId_userName() + '\'' +
                ", passwordMD5='" + Arrays.toString(getPasswordMD5()) + '\'' +
                ", strPasswordMD5='" + strPasswordMD5 + '\'' +

                ", Hora Ultimo AccesoCorrecto='" + getHoraUltimoAccesoCorrecto() + '\'' +
                ", Hora Ultimo AccesoErroneo='" + getHoraUltimoAccesoErroneo() + '\'' +
                ", typeUser='" + getTypeUser() + '\'' +
                ", activo=" + isActivo() +
                '}';
    }
}
