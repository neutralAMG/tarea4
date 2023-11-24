import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Menu extends Gui {
    AppClientPopup popup = new AppClientPopup();

    protected JFrame frame = new JFrame();

    protected static JButton nuevo;
    protected static JButton actualizar;
    protected static JButton eliminar;
    protected static JButton cerrarSeccion;
    protected static JTable tablaDeUsuarios;

    public void inicio() {
        JPanel panel = new JPanel();

        frame.setSize(1400, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        componentes(panel);

    }

    private void componentes(JPanel panel) {
        panel.setLayout(new BorderLayout());
        titulo = new JLabel("Clientes registrados");
        titulo.setBounds(10, 20, 400, 400);
        titulo.setFont(new Font(null, Font.BOLD, 40));
        panel.add(titulo, BorderLayout.NORTH);

        String[][] data = conectar.seleccionarTodosLosClientes();

        String[] nombreDeColumna = { "Usuario", "Nombre", "Apellido", "telefono", "Correo" };

        tablaDeUsuarios = new JTable(data, nombreDeColumna);

        tablaDeUsuarios.setEnabled(false);
        tablaDeUsuarios.setFont(new Font(null, 0, 20));
        tablaDeUsuarios.setRowHeight(30);
        tablaDeUsuarios.getColumnModel().getColumn(0).setPreferredWidth(100);
        tablaDeUsuarios.getTableHeader().setFont(new Font(null, Font.BOLD, 18));

        JScrollPane scrollPane = new JScrollPane(tablaDeUsuarios);
        scrollPane.setPreferredSize(new Dimension(800, 600));

        panel.add(scrollPane, BorderLayout.CENTER);
        refreshTable();

        nuevo = new JButton("Nuevo");
        nuevo.setPreferredSize(new Dimension(100, 40));
        nuevo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                popup.agregarUsuario();
                refreshTable();

            }
        });

        actualizar = new JButton("Actualizar");
        actualizar.setPreferredSize(new Dimension(100, 40));
        actualizar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                ArrayList<String> listaDeUsuarios = conectar.seleccionarNombreDeUsarios();
                popup.editarUsuario(listaDeUsuarios);
            }
        });

        eliminar = new JButton("Eliminar");
        eliminar.setPreferredSize(new Dimension(100, 40));
        eliminar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                popup.borarUsuario();
                refreshTable();
            }
        });

        cerrarSeccion = new JButton("Cerrar Seccion");
        cerrarSeccion.setPreferredSize(new Dimension(140, 40));
        cerrarSeccion.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                login.setVisibility("", true);
                frame.setVisible(false);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(nuevo);
        buttonPanel.add(actualizar);
        buttonPanel.add(eliminar);
        buttonPanel.add(cerrarSeccion);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.add(panel);
    }

    public void refreshTable() {

        String[][] data = conectar.seleccionarTodosLosClientes();
        String[] nombreDeColumna = { "Usuario", "Nombre", "Apellido", "telefono", "Correo" };
        DefaultTableModel model = new DefaultTableModel(data, nombreDeColumna);
        model.fireTableDataChanged();

        tablaDeUsuarios.setModel(model);
        tablaDeUsuarios.revalidate();
        tablaDeUsuarios.repaint();
    }
}
