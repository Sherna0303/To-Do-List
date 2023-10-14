package Controlador;

import javax.swing.*;

import Modelo.Modelo_List;
import Vista.View_List;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controlador_List {
    private View_List view;
    private Modelo_List model;

    public Controlador_List(View_List view, Modelo_List model) {
        this.view = view;
        this.model = model;
        
        // Modificando visualmente el JOptionPane
        UIManager.put("OptionPane.background", Color.WHITE);
 		UIManager.put("OptionPane.messageFont", new Font("Roboto", Font.PLAIN, 14));
 		UIManager.put("OptionPane.messageForeground", Color.BLACK);
 		UIManager.put("Panel.background", Color.WHITE);

        // ActionListener para el botón "Crear"
        view.getCreateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String task = JOptionPane.showInputDialog(view.frame, "Ingrese una tarea:");
                if (task != null && !task.isEmpty()) {
                    if (model != null) {
                        try {
							model.crearTarea(task);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                        refreshToDoList();
                    }
                }
            }
        });

        // ActionListener para el botón "Editar"
        view.getEditButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int selectedIndex = view.getToDoList().getSelectedIndex();
            	String textoId = view.getToDoList().getSelectedValue();
                if (selectedIndex >= 0) {
                    String editedTask = JOptionPane.showInputDialog(view.frame, "Editar tarea:", view.getToDoList().getSelectedValue());
                    if (editedTask != null && !editedTask.isEmpty()) {
                        if (model != null) {
                            try {
                                model.editarTarea(textoId, editedTask);
                                refreshToDoList();
                                JOptionPane.showMessageDialog(view.frame, "Tarea editada correctamente.");
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(view.frame, "Error al editar la tarea: " + ex.getMessage());
                            }
                        }
                    }
                }
            }
        });

        // ActionListener para el botón "Eliminar"
        view.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int selectedIndex = view.getToDoList().getSelectedIndex();
            	String textoId = view.getToDoList().getSelectedValue();
                if (selectedIndex >= 0) {
                    int confirmacion = JOptionPane.showConfirmDialog(view.frame, "¿Estás seguro de que deseas eliminar esta tarea?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        if (model != null) {
                            try {
                                model.eliminarTarea(textoId);
                                refreshToDoList();
                                JOptionPane.showMessageDialog(view.frame, "Tarea eliminada correctamente.");
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(view.frame, "Error al eliminar la tarea: " + ex.getMessage());
                            }
                        }
                    }
                }
            }
        });

        // Cargar tareas iniciales en la vista
        refreshToDoList();
    }
    
    // Metodo para actualizar la vista
    private void refreshToDoList() {
        try {
            ResultSet resultSet = model.mostrarTareas();
            DefaultListModel<String> listModel = new DefaultListModel<>();
            while (resultSet.next()) {
                String task = resultSet.getString("task");
                listModel.addElement(task);
            }
            view.getToDoList().setModel(listModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view.frame, "Error al obtener las tareas: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Modelo_List model = new Modelo_List();
                View_List view = new View_List();
                new Controlador_List(view, model);
            }
        });
    }
}
