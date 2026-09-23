package prueba2;

import Otros.DatosNoCorrectosException;
import Otros.Empleado;
import Otros.Nomina;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MetodosAltaEmpleado {
    public void altaEmpleado(Empleado e) {
        Nomina n = new Nomina();
        String nombre = e.nombre;
        String dni = e.dni;
        char sexo = e.sexo;
        Integer categoria = e.getCategoria();
        Integer anyos = e.anyos;
        Double sueldo = n.sueldo(e);

        String insertEmpleados = "INSERT INTO empleados (dni,nombre, sexo, categoria, años) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertEmpleados);) {

            ps.setString(1, dni);
            ps.setString(2, nombre);
            ps.setString(3, String.valueOf(sexo));
            ps.setInt(4, categoria);
            ps.setInt(5, anyos);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error" + e);
        }

        String insertNominas = "INSERT INTO nominas (sueldo,empleado) VALUES (?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertNominas);) {

            ps.setDouble(1, sueldo);
            ps.setString(2, dni);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error" + e);

        }
    }

    public void altaEmpleados(String ruta) {
        String insertEmpleados = "INSERT INTO empleados (dni,nombre, sexo, categoria, años) VALUES (?, ?, ?, ?, ?)";
        String insertNominas = "INSERT INTO nominas (sueldo,empleado) VALUES (?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertEmpleados);
             BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            String linea;

            while ((linea = br.readLine()) != null) {
                String[] cadena = linea.split(";");

                String nombre = cadena[0].trim();
                String dni = cadena[1].trim();
                char sexo = cadena[2].charAt(0);
                Integer categoria = Integer.parseInt(cadena[3]);
                Integer anyos = Integer.parseInt(cadena[4]);

                Empleado e = new Empleado(dni, nombre, sexo, categoria, anyos);

                ps.setString(1, e.dni);
                ps.setString(2, e.nombre);
                ps.setString(3, String.valueOf(e.sexo));
                ps.setInt(4, e.getCategoria());
                ps.setInt(5, e.anyos);
                ps.executeUpdate();
                try (PreparedStatement pst = conn.prepareStatement(insertNominas)) {
                    Nomina n = new Nomina();
                    pst.setDouble(1, n.sueldo(e));
                    pst.setString(2, e.dni);
                    pst.executeUpdate();
                }
            }
        } catch (IOException e) {
            System.out.println("Error de ficheros " + e);
        } catch (SQLException e) {
            System.out.println("Error de base de datos " + e);
        } catch (DatosNoCorrectosException ex) {
            System.out.println("Error en los datos 0" + ex);
        }
    }
}

