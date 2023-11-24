import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarUsuario extends Gui {

    protected static JFrame frame = new JFrame();

    protected JTextField nombreUsuarioInputFild = new JTextField();

    protected JTextField nombreInputFild = new JTextField();

    protected JTextField apellidoInputFild = new JTextField();

    protected JTextField id = new JTextField();

    protected JTextField telefonoInputFild = new JTextField();

    protected JTextField correoInputFild = new JTextField();

    protected JPasswordField contraseñaInputFild = new JPasswordField();

    protected JPasswordField confirmarContraseñaInputFild = new JPasswordField();
    protected JButton registrarU;
    protected JLabel mensaje;

    public void inicio(String header) {
        JPanel panel = new JPanel();
        frame.revalidate();
        frame.repaint();

        frame.setSize(380, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        componentes(panel, header);

    }

    private void componentes(JPanel panel, String header) {

        panel.setLayout(null);
        nombreUsuarioInputFild.setText("");
        nombreInputFild.setText("");
        apellidoInputFild.setText("");
        telefonoInputFild.setText("");
        correoInputFild.setText("");
        contraseñaInputFild.setText("");
        confirmarContraseñaInputFild.setText("");

        JLabel titulo = new JLabel(header);
        panel.add(titulo);
        titulo.setBounds(120, 0, 370, 28);
        titulo.setFont(new Font(null, Font.BOLD, 15));

        // nombre de usuario
        JLabel nombreUsuarioLabel = new JLabel("Nombre de usuario");
        nombreUsuarioLabel.setBounds(20, 40, 120, 25);
        panel.add(nombreUsuarioLabel);

        nombreUsuarioInputFild.setBounds(150, 40, 200, 29);
        panel.add(nombreUsuarioInputFild);
        // frame.setVisible(true);

        // nombre
        JLabel nombreLabel = new JLabel("Nombre");
        nombreLabel.setBounds(20, 80, 120, 25);
        panel.add(nombreLabel);

        nombreInputFild.setBounds(150, 80, 200, 29);
        panel.add(nombreInputFild);
        // frame.setVisible(true);

        // apellido
        JLabel apellidoLabel = new JLabel("Apellido");
        apellidoLabel.setBounds(20, 120, 120, 25);
        panel.add(apellidoLabel);

        apellidoInputFild.setBounds(150, 120, 200, 29);
        panel.add(apellidoInputFild);
        // frame.setVisible(true);

        // telefono
        JLabel telefonoLabel = new JLabel("Telefono");
        telefonoLabel.setBounds(20, 160, 120, 25);
        panel.add(telefonoLabel);

        telefonoInputFild.setBounds(150, 160, 200, 29);
        panel.add(telefonoInputFild);

        // correo
        JLabel correoLabel = new JLabel("Correo");
        correoLabel.setBounds(20, 200, 120, 25);
        panel.add(correoLabel);

        correoInputFild.setBounds(150, 200, 200, 29);
        panel.add(correoInputFild);

        // contraseña
        JLabel contraseñaLabel = new JLabel("Contraseña");
        contraseñaLabel.setBounds(20, 240, 120, 25);
        panel.add(contraseñaLabel);

        contraseñaInputFild.setBounds(150, 240, 200, 29);
        panel.add(contraseñaInputFild);

        // confirmar Contraseña
        JLabel confirmarContraseñaLabel = new JLabel("Confir contraseña");
        confirmarContraseñaLabel.setBounds(20, 280, 120, 25);
        panel.add(confirmarContraseñaLabel);

        confirmarContraseñaInputFild.setBounds(150, 280, 200, 29);
        panel.add(confirmarContraseñaInputFild);

        registrarU = new JButton("Registrar usuario");
        registrarU.setBounds(80, 350, 200, 50);
        registrarU.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                evento();

            }
        });
        panel.add(registrarU);

        mensaje = new JLabel();
        mensaje.setBounds(10, 410, 350, 25);
        panel.add(mensaje);
        frame.setVisible(true);

    }

    public void evento() {
        String usuario = nombreUsuarioInputFild.getText();
        String nombre = nombreInputFild.getText();
        String apellido = apellidoInputFild.getText();
        String telefono = telefonoInputFild.getText();
        String correo = correoInputFild.getText();
        String contraseña = new String(contraseñaInputFild.getPassword());

        if (!restriccionesDeRegistro()) {
            return;
        }

        try {
            System.out.println("Usuario registrado monstro leyenda");
            conectar.registrarUsuario(usuario, nombre, apellido, telefono, correo, contraseña);
            login.setVisibility("Usuario registrado con exito", true);

            setVisibility(false);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    protected void setVisibility(boolean visible) {
        frame.setVisible(visible);
        frame.revalidate();
        frame.repaint();
        nombreUsuarioInputFild.setText("");
        nombreInputFild.setText("");
        apellidoInputFild.setText("");
        telefonoInputFild.setText("");
        correoInputFild.setText("");
        contraseñaInputFild.setText("");
        confirmarContraseñaInputFild.setText("");
        mensaje.setText("");
    }

    protected boolean restriccionesDeRegistro() {
        String usuario = nombreUsuarioInputFild.getText();
        String nombre = nombreInputFild.getText();
        String apellido = apellidoInputFild.getText();
        String telefono = telefonoInputFild.getText();
        String correo = correoInputFild.getText();
        String contraseña = new String(contraseñaInputFild.getPassword());
        String confirmarContraseña = new String(confirmarContraseñaInputFild.getPassword());

        String[] lista = { usuario, nombre, apellido, telefono, correo, contraseña, confirmarContraseña };

        for (int i = 0; i < lista.length; i++) {
            if (lista[i].isEmpty()) {
                mensaje.setText("Un campo esta vacio todos los campos son obligatorios");
                return false;
            }

        }

        if (!confirmarContraseña.equals(contraseña)) {
            mensaje.setText("La contraseña no coincide con el campo confirmar contraseña");
            return false;
        }

        return true;

    }

}
