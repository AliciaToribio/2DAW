public class CalculaNominas {
    /**
     * Crea a los empleados James y Ada, se imprimen sus datos y sueldos, incrementa un año a Ada,
     * sube a James a la categoría 9 e imprime de nuevo los resultados
     * @param args argumentos de línea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        try {
            Empleado james = new Empleado("James Cosling", "32000032G", 'M', 4, 7);
            Empleado ada = new Empleado("Ada Lovelace", "32000031R", 'F');

            escribe(james, ada);

            ada.incrAnyo();
            james.setCategoria(9);

            escribe(james, ada);
        } catch (DatosNoCorrectosException e) {
            System.out.println(e);
        }
    }

    /**
     * Imprime por consola los datos y el sueldo de los dos empleados
     * @param james
     * @param ada
     */
    private static void escribe(Empleado james, Empleado ada) {
        System.out.println(james.Imprime() + " cuyo sueldo es " + Nomina.sueldo(james) + "€");

        System.out.println(ada.Imprime() + "cuyo sueldo es " + Nomina.sueldo(ada) + "€");
    }
}