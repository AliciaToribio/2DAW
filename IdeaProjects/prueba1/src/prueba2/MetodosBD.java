package prueba2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MetodosBD {
    public void mostrarEmpleados() {
        try (Connection conn = ConexionBD.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT e.DNI,e.Nombre,e.SEXO,e.categoria,e.años,n.sueldo FROM empleados e JOIN nominas n ON e.DNI=n.empleado");) {

            while (rs.next()) {
                System.out.println("Nombre: " + rs.getString(1) + ", DNI: " + rs.getString(2) + ", Sexo: " + rs.getString(3)
                                + " ,Categoria: " + rs.getInt(4) + " ,Años: " + rs.getInt(5)+" ,Sueldo: "+rs.getDouble(6));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarSalarioEmpleado(String dni) {
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT sueldo,empleado FROM nominas where empleado = ?");) {

            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("sueldo: " + rs.getDouble(1) + " ,empleado: " + rs.getString(2));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
