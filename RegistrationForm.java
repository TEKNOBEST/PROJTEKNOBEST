import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class RegistrationForm extends JFrame implements ActionListener {

    private JTextField tfNombres;
    private JTextField tfApellidos;
    private JTextField tfCorreoElectronico;
    private JPasswordField pfContrasena;
    private JButton btnRegistrar;

    public RegistrationForm() {
        setTitle("Formulario de Registro");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(10, 25, 47)); // Azul oscuro

        // Crear un panel para el logo
        JPanel panelLogo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("C:\\Users\\HOMEPCTK\\Desktop\\Teknobest Soluciones Digitales\\html-Teknobest\\images/logore.png");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelLogo.setPreferredSize(new Dimension(400, 100)); // Ajustar tamaño según sea necesario

        // Crear un panel para el formulario
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));
        panelFormulario.setBackground(new Color(10, 25, 47)); // Azul oscuro

        // Labels and text fields
        JLabel lblNombres = new JLabel("Nombres:");
        lblNombres.setForeground(Color.WHITE);
        tfNombres = new JTextField();
        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setForeground(Color.WHITE);
        tfApellidos = new JTextField();
        JLabel lblCorreoElectronico = new JLabel("Correo Electrónico:");
        lblCorreoElectronico.setForeground(Color.WHITE);
        tfCorreoElectronico = new JTextField();
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setForeground(Color.WHITE);
        pfContrasena = new JPasswordField();

        // Register button
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBackground(Color.WHITE);
        btnRegistrar.setForeground(new Color(10, 25, 47)); // Azul oscuro
        btnRegistrar.addActionListener(this);

        // Add components to form panel
        panelFormulario.add(lblNombres);
        panelFormulario.add(tfNombres);
        panelFormulario.add(lblApellidos);
        panelFormulario.add(tfApellidos);
        panelFormulario.add(lblCorreoElectronico);
        panelFormulario.add(tfCorreoElectronico);
        panelFormulario.add(lblContrasena);
        panelFormulario.add(pfContrasena);
        panelFormulario.add(new JLabel()); // Empty cell
        panelFormulario.add(btnRegistrar);

        // Crear un panel superior para el logo y el botón de cerrar
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelLogo, BorderLayout.CENTER);

        // Crear un botón de cierre personalizado
        JButton btnCerrar = new JButton("X");
        btnCerrar.setBackground(Color.RED);
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCerrar.addActionListener(e -> dispose());

        // Crear un panel para el botón de cerrar y alinearlo a la derecha
        JPanel panelCerrar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelCerrar.setOpaque(false); // Hacer transparente el panel
        panelCerrar.add(btnCerrar);

        panelSuperior.add(panelCerrar, BorderLayout.NORTH);

        // Agregar los paneles al panel principal
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);

        // Agregar el panel principal al frame
        add(panelPrincipal);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrar) {
            String nombres = tfNombres.getText();
            String apellidos = tfApellidos.getText();
            String correoElectronico = tfCorreoElectronico.getText();
            String contrasena = new String(pfContrasena.getPassword());

            Connection connection = DatabaseConnection.conectarBaseDatos();
            if (connection != null) {
                InsertRecord.insertarRegistro(connection, nombres, apellidos, correoElectronico, contrasena);
                try {
                    connection.close();
                } catch (SQLException ex) {
                    System.out.println("Error al cerrar la conexión: " + ex.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        new RegistrationForm();
    }
}
