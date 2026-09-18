import Laboral.Persona;

public class Empleado extends Persona {
    private int categoria;
    public int anyos;

    public Empleado(String nombre, String dni, char sexo) {
        super(nombre, dni, sexo);
        this.categoria = 1;
        this.anyos = 0;
    }

    public Empleado(String nombre, String dni, char sexo, int categoria, int anyos) throws DatosNoCorrectosException {
        super(nombre, dni, sexo);
        if(!(categoria >= 1 && categoria <= 10)){
            throw new DatosNoCorrectosException("Datos no correctos");
        }
        this.categoria = categoria;
         if (!(anyos >=0)) {
             throw new DatosNoCorrectosException("Datos no correctos");
         }
        this.anyos = anyos;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) throws DatosNoCorrectosException {
        if(!(categoria >= 1 && categoria <= 10)){
            throw new DatosNoCorrectosException("Datos no correctos");
        }
        this.categoria = categoria;
    }

    public void incrAnyo() {
        this.anyos++;
    }

    @Override
    public String Imprime() {
        return
                "categoria=" + categoria +
                ", anyos=" + anyos +
                ", nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", sexo=" + sexo +
                '}';
    }
}
