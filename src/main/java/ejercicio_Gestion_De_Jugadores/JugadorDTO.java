package ejercicio_Gestion_De_Jugadores;

public class JugadorDTO {

    private int idJugador;
    private String nombre;
    private String apellidos;
    private int edad;
    private int dorsal;
    private String posicion;
    private String fNacimiento;
    private float estatura;
    private float peso;
    private boolean activo;

    public JugadorDTO(int idJugador,String nombre, String apellidos, int edad, int dorsal, String posicion, String fNacimiento, float estatura, float peso, boolean activo) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.dorsal = dorsal;
        this.posicion = posicion;
        this.fNacimiento = fNacimiento;
        this.estatura = estatura;
        this.peso = peso;
        this.activo = activo;
    }
    public JugadorDTO() {
        this.idJugador=JugadorDAO.getIDDesdeFicheroIDSYAumentarID();
        this.nombre = "pruebaNombre";
        this.apellidos = "pruebaApellidos";
        this.edad = 0;
        this.dorsal = 0;
        this.posicion = "pruebaPosicion";
        this.fNacimiento = "pruebaFNacimiento";
        this.estatura = 0;
        this.peso = 0;
        this.activo = true;

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getfNacimiento() {
        return fNacimiento;
    }

    public void setfNacimiento(String fNacimiento) {
        this.fNacimiento = fNacimiento;
    }

    public float getEstatura() {
        return estatura;
    }

    public void setEstatura(float estatura) {
        this.estatura = estatura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getIdJugador() {
        return idJugador;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "idJugador=" + idJugador +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", edad=" + edad +
                ", dorsal=" + dorsal +
                ", posicion='" + posicion + '\'' +
                ", fNacimiento='" + fNacimiento + '\'' +
                ", estatura=" + estatura +
                ", peso=" + peso +
                ", activo=" + activo +
                '}';
    }


}
