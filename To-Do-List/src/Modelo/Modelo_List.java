package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bd_Connect.Conexion;

public class Modelo_List {
	private Conexion conexion;
	
	// Se crea la conexion
    public Modelo_List() {
        conexion = new Conexion();
    }

    // Metodo para crear tareas
    public void crearTarea(String task) throws SQLException {
        String query = "INSERT INTO list (task) VALUES (?)";
        try (Connection connection = conexion.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, task);
            preparedStatement.executeUpdate();
        }
    }
    
    // Metodo para editar tareas
    public void editarTarea(String taskId, String task) throws SQLException {
        String query = "UPDATE list SET task = ? WHERE task = ?";
        try (Connection connection = conexion.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, task);
            preparedStatement.setString(2, taskId);
            preparedStatement.executeUpdate();
        }
    }

    // Metodo para eliminar tareas
    public void eliminarTarea(String taskId) throws SQLException {
        String query = "DELETE FROM list WHERE task = ?";
        try (Connection connection = conexion.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, taskId);
            preparedStatement.executeUpdate();
        }
    }

    // Metodo para mostrar tareas existentes
    public ResultSet mostrarTareas() throws SQLException {
        String query = "SELECT task FROM list";
        Connection connection = conexion.conectar();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeQuery();
    }
}

