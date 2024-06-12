import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertRecord {

    public static void insertarRegistro(Connection connection, String nombres, String apellidos, String correoElectronico, String contrasena) {
        String sql = "INSERT INTO registrotk (Nombres, Apellidos, CorreoElectronico, Contrasena) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombres);
            statement.setString(2, apellidos);
            statement.setString(3, correoElectronico);
            statement.setString(4, contrasena);
            statement.executeUpdate();
            System.out.println("Registro insertado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar el registro: " + e.getMessage());
        }
    }
}
