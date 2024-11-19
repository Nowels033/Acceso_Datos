package gestionDeUsuarios;

public class Actividad {

//    CREATE TABLE all_candy
//            (candy_num SERIAL PRIMARY KEY,
//             candy_maker CHAR(25));
//
//    CREATE TABLE sugus
//            (candy_num INT,
//             candy_flavor CHAR(20),
//    FOREIGN KEY (candy_num) REFERENCES all_candy
//    ON DELETE CASCADE);
//
//    Comando para generar volcados (copias de respaldo, esto es, backups) de una BD dada:
//
//    $ mysqldump --user=USUARIO_BASE_DATOS --password=PASSWORD_BASE_DATOS NOMBRE_BASE_DATOS > nombredelacopiadeseguridad.sql
//
//    Uso del algoritmo MD5 para generar el hash code de un mensaje.
//
//            import java.security.*;
//
//..
//
//    byte[] bytesOfMessage = yourString.getBytes("UTF-8");
//
//    MessageDigest md = MessageDigest.getInstance("MD5");
//    byte[] theMD5digest = md.digest(bytesOfMessage);
//
//
//
//    Actividad: Subsistema de Gestión de Usuarios
//
//🧑‍💻 Recuerda que somos analistas-programadores
//
//    Crea una nueva tabla en la base de datos para gestionar los accesos a la aplicación de los usuarios:
//
//    Cada usuario tendrá:
//    ID (numérico) autoincremental
//    id_usuario
//            contraseña
//    Hora y fecha del último acceso correcto
//    Hora y fecha del último acceso erróneo
//    Tipo de usuario: admin (puede hacer todo en el sistema) y usuario_consulta
//    Activo/inactivo
//    activo boolean not null default 1
//
//    Al dar de alta un nuevo usuario, la aplicación validará que el login y la contraseña tienen al menos 5 caracteres. El cambio de la contraseña validará también que la longitud es >= 5 caracteres.
//
//    La aplicación almacenará el hash MD5 de la contraseña en la DB.
//
//    Al acceder el usuario a la aplicación se le mostrará un menú que liste sólo las opciones asociadas a su perfil de usuario.
//
//    Sólo los usuarios de tipo admin podrán dar de alta a nuevos usuarios.
//
//    Si el usuario está inactivo o no existe, la aplicación informará al usuario.
//
//    Genera los diagramas de casos de uso y la realización de los casos de uso (diagramas de secuencia de los UCs CRUD).



}
