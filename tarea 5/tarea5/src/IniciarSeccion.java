import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IniciarSeccion extends Gui {

    protected static JFrame frame = new JFrame();

    protected static JLabel userLabel;
    protected static JTextField userInputFild;
    protected static JLabel contraseñaLabel;
    protected static JPasswordField contraseñaInputFild;
    protected static JButton registrar;
    private boolean visible = false;

    public void inicio() {
        JPanel panel = new JPanel();

        frame.setSize(380, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel);
        componenetes(panel);

    }

    private void componenetes(JPanel panel) {
        panel.setLayout(null);

        titulo = new JLabel("Inicio de sección");
        panel.add(titulo);
        titulo.setBounds(120, 20, 370, 28);
        JLabel userLabel = new JLabel("Usuario");

        userLabel.setBounds(20, 60, 80, 25);
        panel.add(userLabel);

        userInputFild = new JTextField(80);
        userInputFild.setBounds(120, 60, 200, 29);
        panel.add(userInputFild);
        frame.setVisible(true);
        frame.add(panel);

        contraseñaLabel = new JLabel("Contraseña");
        contraseñaLabel.setBounds(20, 95, 80, 25);
        panel.add(contraseñaLabel);

        contraseñaInputFild = new JPasswordField(80);
        contraseñaInputFild.setBounds(120, 95, 200, 29);
        panel.add(contraseñaInputFild);

        sumit = new JButton("Iniciar sección");

        sumit.setBounds(80, 150, 200, 40);

        sumit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String usuario = userInputFild.getText();
                String contraseña = new String(contraseñaInputFild.getPassword());

                if (usuario.isEmpty() || contraseña.isEmpty()) {
                    mensaje.setText("“Debe ingresar su usuario y contraseña, si no está registrado debe registrarse");
                    return;
                }

                try {
                    if (conectar.IniciarSeccion(usuario, contraseña) == true) {
                        app.inicio();
                        frame.setVisible(false);
                    } else {
                        mensaje.setText("No se encontro ese usuario");
                        return;
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        panel.add(sumit);

        registrar = new JButton("Registrar Usuario");
        registrar.setBounds(80, 200, 200, 20);
        registrar.addActionListener(new IniciarSeccion());
        panel.add(registrar);

        mensaje = new JLabel("");
        mensaje.setBounds(10, 250, 350, 25);

        panel.add(mensaje);

        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (visible == false) {
            SignUp.inicio("Registrar Usuario");
            visible = true;
        } else if (visible) {
            SignUp.setVisibility(visible);

        }
        frame.setVisible(false);

    }

    protected void setVisibility(String mensajeResivido, boolean visible) {
        frame.setVisible(visible);
        userInputFild.setText("");
        contraseñaInputFild.setText("");
        mensaje.setText(mensajeResivido);
    }

}
