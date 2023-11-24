
import javax.swing.JButton;

import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui implements ActionListener {
    protected static JLabel titulo;

    protected static JButton sumit;
    protected static JLabel mensaje;
    static ConeccionBaseDeDatos conectar = new ConeccionBaseDeDatos();
    static IniciarSeccion login = new IniciarSeccion();
    static RegistrarUsuario SignUp = new RegistrarUsuario();
    static Menu app = new Menu();

    public void main() throws Exception {

        login.inicio();

    }

    public void actionPerformed(ActionEvent e) {

    }

}
