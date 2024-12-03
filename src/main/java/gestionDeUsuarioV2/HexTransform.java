package gestionDeUsuarioV2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HexTransform {
    public static String generarMD5(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(texto.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al generar MD5", e);
        }
    }
}
