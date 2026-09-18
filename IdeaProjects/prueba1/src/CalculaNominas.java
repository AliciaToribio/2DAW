

public class CalculaNominas {
    public static void main(String[] args) throws DatosNoCorrectosException {

            Empleado james = new Empleado("James Cosling", "32000032G", 'M', 4, 7);
            Empleado ada = new Empleado("Ada Lovelace", "32000031R", 'F');

            escribe(james, ada);

            ada.incrAnyo();
            james.setCategoria(9);

            escribe(james, ada);

    }

    private static void escribe(Empleado james, Empleado ada) {
        System.out.println(james.Imprime() + " cuyo sueldo es " + Nomina.sueldo(james) + "€");

        System.out.println(ada.Imprime() + "cuyo sueldo es " + Nomina.sueldo(ada) + "€");
    }
}