package Otros;

import prueba2.MetodosAltaEmpleado;
import prueba2.ConexionBD;
import prueba2.FicheroEmpleado;
import prueba2.MetodosBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CalculaNominas {
    /**
     * Crea a los empleados James y Ada, se imprimen sus datos y sueldos, incrementa un año a Ada,
     * sube a James a la categoría 9 e imprime de nuevo los resultados
     * @param args argumentos de línea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
//        try {
//            Empleado james = new Empleado("James Cosling", "32000032G", 'M', 4, 7);
//            Empleado ada = new Empleado("Ada Lovelace", "32000031R", 'F');
//
//            escribe(james, ada);
//
//            ada.incrAnyo();
//            james.setCategoria(9);
//
//            escribe(james, ada);
//
//        } catch (DatosNoCorrectosException e) {
//            System.out.println(e);
//        }

        MetodosBD mbd = new MetodosBD();
        MetodosAltaEmpleado altaBD = new MetodosAltaEmpleado();
        altaBD.altaEmpleados("Recursos\\empleadosNuevos.txt");
        ArrayList<Empleado> listaEmpleado = new FicheroEmpleado().leerEmpleados("Recursos\\empleados.txt");
        Empleado e1 = listaEmpleado.get(0);
        Empleado e2 = listaEmpleado.get(1);
        escribe(e1, e2);
        FicheroEmpleado gt = new FicheroEmpleado();
        gt.escribirSueldo("Recursos\\salarios.txt",listaEmpleado);
        Scanner sc = new Scanner(System.in);

        int opcion;
        do {

            System.out.println("---MENÚ---");
            System.out.println("0. Salir");
            System.out.println("1. Mostrar informacion de la bd");
            System.out.println("2. Mostrar salario por DNI de empleado");
            System.out.println("3. Modificacion datos empleados(submenu)");
            System.out.println("4. Recalcular y actualizar el sueldo de un empleado");
            System.out.println("5. Recalcular y actualizar los sueldos de todos los empleados");
            System.out.println("6. Crear copia de seguridad");
            System.out.println("--------------");
            System.out.println("Seleccione una opcion");
            opcion = sc.nextInt();
            String dni;
            switch (opcion) {
                case 0:
                    System.out.println("Saliendo");
                    break;
                case 1:
                    mbd.mostrarEmpleados();
                    break;
                case 2:
                    System.out.println("Escriba el DNI del empleado a mostrar");
                    sc.nextLine();
                    dni = sc.nextLine();
                    mbd.mostrarSalarioEmpleado(dni);
                    break;
                case 3:

                    break;
                case 4:
                    System.out.println("Escriba el DNI del empleado a mostrar");
                    sc.nextLine();
                    dni = sc.nextLine();

                    break;
                case 5:

                    break;
                case 6:

                    break  ;
                default:
                    System.out.println("La opcion debe ser entre 0 y 6");
                    break;
            }

        } while (opcion != 0);
        sc.close();

        //prueba de conexion con bd
        try (Connection con = ConexionBD.getConnection()) {
            System.out.println("Conexión establecida correctamente");
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
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