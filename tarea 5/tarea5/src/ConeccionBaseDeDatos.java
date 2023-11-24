
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConeccionBaseDeDatos {

    private static String connectionUrl = "jdbc:mysql://localhost:3306/usuarios";
    private static String usuario = "root";
    private static String password = "Alejandro23@#";

    protected static Connection conectarBD() {

        System.out.println("Conectandose a la base de datos...");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionUrl, usuario, password);
            connection.setAutoCommit(true);
            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
            System.out.println("No se pudo conectar a la base de datos.");
            e.printStackTrace();
        }
        return connection;
    }

    public void registrarUsuario(String nombreUsuario, String nombre, String apellido,
            String telefono, String correo, String contraseña) throws SQLException {

        Connection connection = conectarBD();
        String insertBaseDeDatos = "Insert Into Usuarios (nombre_usuario, nombre, apellido, numero_telefono, correo, contraseña) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertBaseDeDatos)) {

            preparedStatement.setString(1, nombreUsuario);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, apellido);
            preparedStatement.setString(4, telefono);
            preparedStatement.setString(5, correo);
            preparedStatement.setString(6, contraseña);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("No pudo registrarse usuario");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public boolean IniciarSeccion(String usuario, String contraseña) throws SQLException {
        String selectBaseDeDatos = "Select nombre_usuario, contraseña from Usuarios WHERE nombre_usuario = ? AND contraseña = ?";
        Connection connection = conectarBD();
        try (PreparedStatement preparedStatement = conectarBD().prepareStatement(selectBaseDeDatos)) {
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, contraseña);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public String[][] seleccionarTodosLosClientes() {
        String[][] clientes = new String[0][];
        String usuario;
        String nombre;
        String apellido;
        String telefono;
        String correo;

        String selectTodosLosClientes = "SELECT * FROM usuarios";
        Connection connection = conectarBD();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectTodosLosClientes);) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                usuario = resultSet.getString("nombre_usuario");
                nombre = resultSet.getString("nombre");
                apellido = resultSet.getString("apellido");
                telefono = resultSet.getString("numero_telefono");
                correo = resultSet.getString("correo");

                String[] filaDeClientes = { usuario, nombre, apellido, telefono, correo, };
                String[][] nuevArraay = new String[clientes.length + 1][];
                for (int i = 0; i < clientes.length; i++) {
                    nuevArraay[i] = clientes[i].clone();
                }

                nuevArraay[clientes.length] = filaDeClientes;
                clientes = nuevArraay;
                System.out.println(clientes);

            }
            return clientes;
        } catch (Exception e) {

            System.out.println("Error al generar tabla");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return clientes;
    }

    public ArrayList<String> seleccionarNombreDeUsarios() {

        String selectNombreDeUsuario = "SELECT nombre_usuario FROM usuarios";
        ArrayList<String> list = new ArrayList<String>();
        String usuario;
        Connection connection = conectarBD();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectNombreDeUsuario);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                usuario = resultSet.getString("nombre_usuario");
                list.add(usuario);

            }

            return list;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return list;
    }

    public void deleteUsuario(String usuario) {

        String selectNombreDeUsuario = "DELETE FROM Usuarios WHERE nombre_usuario = ?";

        Connection connection = conectarBD();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectNombreDeUsuario);) {
            preparedStatement.setString(1, usuario);
            preparedStatement.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public ArrayList<String> selecionaUsuarioEnEspesifico(String usuarioEspesifico) {

        ArrayList<String> usuarioEditar = new ArrayList<String>();
        String usuario;
        String nombre;
        String apellido;
        String telefono;
        String correo;
        String contraseña;
        int id;

        String selectNombreDeUsuario = "SELECT * FROM Usuarios WHERE nombre_usuario = ? ";

        Connection connection = conectarBD();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectNombreDeUsuario);) {

            preparedStatement.setString(1, usuarioEspesifico);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                usuario = resultSet.getString("nombre_usuario");

                nombre = resultSet.getString("nombre");

                apellido = resultSet.getString("apellido");

                telefono = resultSet.getString("numero_telefono");

                correo = resultSet.getString("correo");
                contraseña = resultSet.getString("contraseña");
                id = resultSet.getInt("usuario_id");

                usuarioEditar.add(usuario);
                usuarioEditar.add(nombre);
                usuarioEditar.add(apellido);
                usuarioEditar.add(telefono);
                usuarioEditar.add(correo);
                usuarioEditar.add(contraseña);
                usuarioEditar.add(String.valueOf(id));

            }

            return usuarioEditar;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return usuarioEditar;
    }

    public static void actualizarUsuario(String nombreUsuario, String nombre, String apellido,
            String telefono, String correo, String contraseña, int usuarioId) {

        // Usar try-with-resources para manejar la conexión
        Connection connection = conectarBD();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Usuarios SET nombre_usuario = ?, nombre = ?, apellido = ?, numero_telefono = ?, correo = ?, contraseña = ? WHERE usuario_id = ?")) {

            // Configurar los parámetros del PreparedStatement
            preparedStatement.setString(1, nombreUsuario);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, apellido);
            preparedStatement.setString(4, telefono);
            preparedStatement.setString(5, correo);
            preparedStatement.setString(6, contraseña);
            preparedStatement.setInt(7, usuarioId);

            // Ejecutar la actualización
            System.out.println(preparedStatement);
            int filasAfectadas = preparedStatement.executeUpdate();

            // Verificar si la actualización fue exitosa
            if (filasAfectadas > 0) {
                System.out.println("Usuario actualizado exitosamente.");
            } else {
                System.out.println("No se pudo actualizar el usuario. Verifica los datos ingresados.");
            }

        } catch (SQLException e) {
            System.out.println("Ocurrió un error al actualizar el usuario.");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}