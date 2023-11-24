import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Dimension;
import java.awt.Font;

import java.awt.Component;

public class AppClientPopup extends RegistrarUsuario {

    private static JFrame frame = new JFrame();
    static JPanel panel1 = new JPanel();
    static JPanel panel2 = new JPanel();
    static JButton name = new JButton();
    private boolean visible = false;
    AgregarUsuario agregarUsuario = new AgregarUsuario();

    public void borarUsuario() {
        crearbotonesBorrar();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        frame.remove(panel2);
        frame.add(panel1);

        frame.pack(); // Ajusta el tamaño del frame
        frame.setVisible(true);
    }

    public void crearbotonesBorrar() {
        panel1.removeAll();

        JLabel titu = new JLabel("Seleccione usuario a borrar.");
        titu.setFont(new Font(null, Font.BOLD, 20));
        titu.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(titu);

        JPanel buttonPanel = new JPanel(); // Crea un nuevo panel para los botones
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        ArrayList<String> listaDeUsuarios = conectar.seleccionarNombreDeUsarios();
        for (int i = 0; i < listaDeUsuarios.size(); i++) {

            JButton name = new JButton(listaDeUsuarios.get(i));
            name.setPreferredSize(new Dimension(300, 30)); // Establece el tamaño preferido del botón
            name.setFont(new Font(null, Font.BOLD, 25));
            name.setAlignmentX(Component.CENTER_ALIGNMENT);

            name.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton source = (JButton) e.getSource();
                    conectar.deleteUsuario(source.getText());
                    frame.setVisible(false);
                    app.refreshTable();
                }
            });
            buttonPanel.add(name); // Agrega el botón al panel de botones
        }

        JScrollPane scrollPane = new JScrollPane(buttonPanel); // Agrega el panel de botones a un JScrollPane
        panel1.add(scrollPane); // Agrega el JScrollPane al panel principal

        panel1.revalidate();
        panel1.repaint();

    }

    public void agregarUsuario() {
        if (visible == false) {

            agregarUsuario.inicio("Agrege un nuevo usuario");

            visible = true;
        } else if (visible) {

            agregarUsuario.setVisibility(visible);

        }

    }

    public void editarUsuario(ArrayList<String> listaDeUsuarios) {

        crearbotonesEditar(listaDeUsuarios);
        frame.setSize(400, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        frame.remove(panel2);
        frame.add(panel1);

        frame.setVisible(true);
    }

    public void crearbotonesEditar(ArrayList<String> listaDeUsuarios) {
        // Limpiar el panel antes de agregar nuevos componentes
        panel1.removeAll();

        // Crear y configurar la etiqueta del título
        JLabel titu = new JLabel("Seleccione usuario a Editar.");
        titu.setFont(new Font(null, Font.BOLD, 20));
        titu.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(titu);

        // Obtener la lista de usuarios

        // Crear un botón para cada usuario
        for (String usuario : listaDeUsuarios) {
            JButton name = new JButton(usuario);

            // Configurar el botón
            name.setPreferredSize(new Dimension(200, 20)); // Establece el tamaño preferido del botón
            name.setFont(new Font(null, Font.BOLD, 30));
            name.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Agregar un ActionListener al botón
            name.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    // Cambiar el panel visible
                    frame.setVisible(false);

                    frame.remove(panel1);
                    panel1.remove(name);
                    frame.add(panel2);

                    // Editar el usuario
                    editorUsuario(usuario);
                    System.out.println(usuario);
                }
            });

            // Agregar el botón al panel
            panel1.add(name);
        }

        // Actualizar el panel después de agregar los nuevos componentes
        panel1.revalidate();
        panel1.repaint();

    }

    public void editorUsuario(String usuarioElegido) {
        System.out.println(usuarioElegido);

        ArrayList<String> usuario = conectar.selecionaUsuarioEnEspesifico(usuarioElegido);
        System.out.println("run 2");

        panel2.setLayout(null);

        JLabel titulo = new JLabel("Editar Usuario");
        panel2.add(titulo);
        titulo.setBounds(140, 0, 370, 28);
        titulo.setFont(new Font(null, Font.BOLD, 15));

        // nombre de usuario
        JLabel nombreUsuarioLabel = new JLabel("Nombre de usuario");
        nombreUsuarioLabel.setBounds(20, 40, 120, 25);
        panel2.add(nombreUsuarioLabel);

        nombreUsuarioInputFild.setText(usuario.get(0));
        nombreUsuarioInputFild.setBounds(150, 40, 200, 29);
        panel2.add(nombreUsuarioInputFild);

        // nombre
        JLabel nombreLabel = new JLabel("Nombre");
        nombreLabel.setBounds(20, 80, 120, 25);
        panel2.add(nombreLabel);

        nombreInputFild.setText(usuario.get(1));
        nombreInputFild.setBounds(150, 80, 200, 29);
        panel2.add(nombreInputFild);
        // frame.setVisible(true);

        // apellido
        JLabel apellidoLabel = new JLabel("Apellido");
        apellidoLabel.setBounds(20, 120, 120, 25);
        panel2.add(apellidoLabel);

        apellidoInputFild.setText(usuario.get(2));
        apellidoInputFild.setBounds(150, 120, 200, 29);
        panel2.add(apellidoInputFild);

        // telefono
        JLabel telefonoLabel = new JLabel("Telefono");
        telefonoLabel.setBounds(20, 160, 120, 25);
        panel2.add(telefonoLabel);

        telefonoInputFild.setText(usuario.get(3));
        telefonoInputFild.setBounds(150, 160, 200, 29);
        panel2.add(telefonoInputFild);

        // correo
        JLabel correoLabel = new JLabel("Correo");
        correoLabel.setBounds(20, 200, 120, 25);
        panel2.add(correoLabel);

        correoInputFild.setText(usuario.get(4));
        correoInputFild.setBounds(150, 200, 200, 29);
        panel2.add(correoInputFild);

        JLabel contraseñaLabel = new JLabel("Contraseña");
        contraseñaLabel.setBounds(20, 240, 120, 25);
        panel2.add(contraseñaLabel);

        contraseñaInputFild.setText(usuario.get(5));
        contraseñaInputFild.setBounds(150, 240, 200, 29);
        panel2.add(contraseñaInputFild);

        // confirmar Contraseña
        JLabel confirmarContraseñaLabel = new JLabel("Confir contraseña");
        confirmarContraseñaLabel.setBounds(20, 280, 120, 25);
        panel2.add(confirmarContraseñaLabel);

        confirmarContraseñaInputFild.setText(usuario.get(5));
        confirmarContraseñaInputFild.setBounds(150, 280, 200, 29);
        panel2.add(confirmarContraseñaInputFild);

        JButton actualización = new JButton("Actualizar usuario");
        actualización.setBounds(80, 340, 200, 50);

        mensaje = new JLabel();
        mensaje.setBounds(10, 410, 350, 25);

        panel2.add(mensaje);

        id.setText(usuario.get(6));

        actualización.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = nombreUsuarioInputFild.getText();
                String nombre = nombreInputFild.getText();
                String apellido = apellidoInputFild.getText();
                String telefono = telefonoInputFild.getText();
                String correo = correoInputFild.getText();
                String contraseña = new String(contraseñaInputFild.getPassword());

                try {

                    if (!restriccionesDeRegistro()) {
                        return;
                    }
                    ConeccionBaseDeDatos.actualizarUsuario(usuario, nombre,
                            apellido, telefono, correo,
                            new String(contraseña),
                            Integer.parseInt(id.getText()));

                    app.refreshTable();

                    nombreUsuarioInputFild.setText("");
                    nombreInputFild.setText("");
                    apellidoInputFild.setText("");
                    telefonoInputFild.setText("");
                    correoInputFild.setText("");
                    contraseñaInputFild.setText("");
                    confirmarContraseñaInputFild.setText("");

                    frame.dispose();
                    frame.repaint();
                    frame.revalidate();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        panel2.add(actualización);
        frame.setVisible(true);
        frame.repaint();
        frame.revalidate();

    }

}
