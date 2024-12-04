package gestionDeUsuarios;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
//import org.apache.commons.codec.binary.Hex;
import java.util.Arrays;


public class UserDTO {
    private String id_userName;
    byte[] passwordMD5 =null;
    private String strPasswordMD5 =null;
    private String horaUltimoAccesoCorrecto;
    private String horaUltimoAccesoErroneo;
    private String typeUser;
    private boolean activo = true;

    public UserDTO(String id_userName, String password, String typeUser) {



        if (id_userName.length() >= 5 && password.length() >= 5) {
            byte[] bytesOfMessage = null;
            try {
                bytesOfMessage = password.getBytes(StandardCharsets.UTF_8);
                MessageDigest md = MessageDigest.getInstance("MD5");
                this.passwordMD5 = md.digest(bytesOfMessage);
                String mD5 = HexTransform.bytesToHex(passwordMD5);
                System.out.println("HASH DE LA PASSWD ===>>>> " + mD5);
                this.strPasswordMD5 = mD5;
                this.id_userName = id_userName;
               // this.passwordMD5 = password;
                this.typeUser = typeUser;
                //System.out.println("Usuario creado correctamente.");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("El login y la contraseña deben tener al menos 5 caracteres.");
        }

    }
    //METODO PARA NO HASHEAR EL HASH MD5
    public UserDTO(String id_userName, String password, String typeUser,boolean noHash) {



        this.strPasswordMD5 = password;
        this.id_userName = id_userName;
        this.typeUser=typeUser;

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

    public void setPasswordMD5(byte[] passwordMD5) {
        this.passwordMD5 = passwordMD5;
    }

    public String getStrPasswordMD5() {
        return strPasswordMD5;
    }

    public void setStrPasswordMD5(String strPasswordMD5) {
        this.strPasswordMD5 = strPasswordMD5;
    }

    public void setPasswordMD5(String password) {
        if ( password.length() >= 5) {

            byte[] bytesOfMessage = null;
            try {
                bytesOfMessage = password.getBytes(StandardCharsets.UTF_8);
                MessageDigest md = MessageDigest.getInstance("MD5");

                this.passwordMD5 = md.digest(bytesOfMessage);
                strPasswordMD5 = HexTransform.bytesToHex(passwordMD5);
                System.out.println("Contraseña cambiada correctamente.");

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
        return "UserDTO{" +
                "id_userName='" + getId_userName() + '\'' +
                ", passwordMD5='" + Arrays.toString(getPasswordMD5()) + '\'' +
                ", strPasswordMD5='" + getStrPasswordMD5() + '\'' +
                ", Hora Ultimo AccesoCorrecto='" + getHoraUltimoAccesoCorrecto() + '\'' +
                ", Hora Ultimo AccesoErroneo='" + getHoraUltimoAccesoErroneo() + '\'' +
                ", typeUser='" + getTypeUser() + '\'' +
                ", activo=" + isActivo() +
                '}';
    }
}
