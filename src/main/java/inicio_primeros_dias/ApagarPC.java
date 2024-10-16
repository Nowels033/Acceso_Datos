package inicio_primeros_dias;

import java.io.IOException;

public class ApagarPC {
    public static void main(String[] args) {
        try {
            String command = "shutdown -s -t 0";
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}