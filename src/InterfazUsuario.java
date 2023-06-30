import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InterfazUsuario extends JFrame implements ActionListener {
    private crud crudObj;
    private JTextField docField;

    public InterfazUsuario(String archivo) throws IOException {
        crudObj = new crud(archivo);

        setTitle("Gestión de Contactos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(204, 204, 255));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel docLabel = new JLabel("Documento de identidad:");
        docField = new JTextField();
        JButton consultarButton = new JButton("Consultar");
        JButton agregarButton = new JButton("Agregar");
        JButton eliminarButton = new JButton("Eliminar");
        JButton editarButton = new JButton("Editar");
        JButton mostrarButton = new JButton("Mostrar");

        consultarButton.addActionListener(this);
        agregarButton.addActionListener(this);
        eliminarButton.addActionListener(this);
        editarButton.addActionListener(this);
        mostrarButton.addActionListener(this);

        mainPanel.add(docLabel);
        mainPanel.add(docField);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(consultarButton);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(agregarButton);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(eliminarButton);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(editarButton);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(mostrarButton);


        consultarButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        agregarButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        eliminarButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        editarButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        mostrarButton.setLayout(new FlowLayout(FlowLayout.LEFT));

        add(mainPanel, BorderLayout.CENTER);


        consultarButton.setBackground(new Color(51, 102, 255));
        agregarButton.setBackground(new Color(51, 102, 255));
        eliminarButton.setBackground(new Color(51, 102, 255));
        editarButton.setBackground(new Color(51, 102, 255));
        mostrarButton.setBackground(new Color(51, 102, 255));

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals("Consultar")) {
                String doc = docField.getText().strip();
                String repertorio = crudObj.Repertorio(doc);
                if (repertorio == null) {
                    JOptionPane.showMessageDialog(this, "No se encontró el contacto con documento: " + doc);
                } else {
                    JOptionPane.showMessageDialog(this, repertorio);
                }
            } else if (e.getActionCommand().equals("Agregar")) {
                crudObj.add();
            } else if (e.getActionCommand().equals("Eliminar")) {
                crudObj.delete();
            } else if (e.getActionCommand().equals("Editar")) {
                crudObj.edit();
            } else if (e.getActionCommand().equals("Mostrar")) {
                crudObj.mostrar();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            SwingUtilities.invokeLater(() -> {
                try {
                    new InterfazUsuario("ruta_del_archivo.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
