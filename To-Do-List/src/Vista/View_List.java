package Vista;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View_List {
    public JFrame frame;
    private DefaultListModel<String> toDoListModel;
    private JList<String> toDoList;
    private JButton createButton;
    private JButton deleteButton;
    private JButton editButton;
    private int xMouse, yMouse;
    

    public View_List() {
        // Crear la ventana principal
        frame = new JFrame();
        frame.setUndecorated(true); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setBackground(Color.WHITE);
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });
        frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                frame.setLocation(x - xMouse, y - yMouse);
            }
        });

        // Crear un panel "header" para mover la ventana
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(SystemColor.activeCaption);
        headerPanel.setPreferredSize(new Dimension(frame.getWidth(), 35));
        frame.getContentPane().add(headerPanel, BorderLayout.NORTH);
        headerPanel.setLayout(null);

        JPanel closeButtonPanel = new JPanel();
        closeButtonPanel.setBounds(365, 0, 35, 35);
        closeButtonPanel.setBackground(SystemColor.activeCaption);
        closeButtonPanel.setPreferredSize(new Dimension(35, 35));
        

        headerPanel.add(closeButtonPanel);
        closeButtonPanel.setLayout(null);
                
                        // Crear una etiqueta "X" para cerrar la aplicación
                        JLabel closeButton = new JLabel("x");
                        closeButton.setFont(new Font("Roboto", Font.PLAIN, 22));
                        closeButton.setBounds(0, 0, 35, 35);
                        closeButtonPanel.add(closeButton);
                        closeButton.setForeground(Color.BLACK);
                        closeButton.setHorizontalAlignment(SwingConstants.CENTER);
                        closeButton.setPreferredSize(new Dimension(35, 35));

        // Eventos del boton cerrar
        closeButtonPanel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		System.exit(0);
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		closeButtonPanel.setBackground(Color.RED);
        		closeButton.setForeground(Color.WHITE);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		closeButtonPanel.setBackground(SystemColor.activeCaption);
        		closeButton.setForeground(Color.BLACK);
        	}
        });
        
        // Crear un modelo de lista y una lista para mostrar las tareas                      
        toDoListModel = new DefaultListModel<>();
        toDoList = new JList<>(toDoListModel);
        toDoList.setFont(new Font("Roboto", Font.PLAIN, 14));
        toDoList.setFixedCellHeight(30);
        JScrollPane scrollPane = new JScrollPane(toDoList);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);


        // Crear botones para "Crear," "Eliminar," y "Editar"
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        createButton = createButton("Crear");
        deleteButton = createButton("Eliminar");
        editButton = createButton("Editar");
        buttonPanel.add(createButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
    
    // Funcion para crear botones
    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.setBackground(SystemColor.activeCaption);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Roboto", Font.PLAIN, 14));
        button.setPreferredSize(new Dimension(124,30));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(SystemColor.activeCaption.brighter());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(SystemColor.activeCaption);
            }
        });
        
        return button;
    }
    
    // Retornar el boton Crear
    public JButton getCreateButton() {
        return createButton;
    }

    // Retornar el boton Editar
    public JButton getEditButton() {
        return editButton;
    }

    // Retornar el boton Eliminar
    public JButton getDeleteButton() {
        return deleteButton;
    }

    // Retornar la lista de tareas
    public JList<String> getToDoList() {
        return toDoList;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new View_List();
            }
        });
    }
}

