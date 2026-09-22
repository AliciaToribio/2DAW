package prueba2;

import Otros.DatosNoCorrectosException;
import Otros.Empleado;
import Otros.Nomina;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FicheroEmpleado {
    public static ArrayList<Empleado> leerEmpleados(String ruta) throws IOException, DatosNoCorrectosException {
        ArrayList<Empleado> empleados = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                Empleado emp;

                if (partes.length == 5) {
                    String nombre = partes[0];
                    String dni = partes[1];
                    char sexo = partes[2].charAt(0);
                    int categoria = Integer.parseInt(partes[3]);
                    int anyos = Integer.parseInt(partes[4]);
                    emp = new Empleado(nombre, dni, sexo, categoria, anyos);

                } else if (partes.length == 3) {
                    String nombre = partes[0];
                    String dni = partes[1];
                    char sexo = partes[2].charAt(0);
                    emp = new Empleado(nombre, dni, sexo);

                } else {
                    // lanzar DatosNoCorrectosException
                    continue;
                }
                empleados.add(emp);
            }
        }
        return empleados;
    }

    public static void reescribirEmpleados(String rutaFichero, ArrayList<Empleado> empleados) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaFichero))) {
            for (Empleado emp : empleados) {
                String linea = emp.nombre + ";" + emp.dni + ";" + emp.sexo + ";" + emp.getCategoria() + ";" + emp.anyos;
                bw.write(linea);
                bw.newLine();
            }
        }
    }

    public static void escribirSueldo(String rutaFichero, ArrayList<Empleado> empleados) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaFichero))) {
            for (Empleado emp : empleados) {
                String linea = emp.dni + ";" + Nomina.sueldo(emp);
                bw.write(linea);
                bw.newLine();
            }
        }
    }
}
