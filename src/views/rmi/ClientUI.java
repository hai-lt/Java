package views.rmi;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ClientUI extends JFrame {
    public ClientUI() {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Dia chi server: "), BorderLayout.WEST);
        panel.add(new JTextField(), BorderLayout.CENTER);
        add(panel, BorderLayout.NORTH);

        panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("File"), BorderLayout.WEST);
        panel.add(new JTextField(), BorderLayout.CENTER);
        panel.add(new JButton("Mo file"), BorderLayout.EAST);
        add(panel, BorderLayout.CENTER);

        panel = new JPanel(new BorderLayout());
        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(new JLabel("Ket qua: "), BorderLayout.WEST);
        panel1.add(new JTextField(), BorderLayout.CENTER);
        panel.add(panel1, BorderLayout.NORTH);
        panel1.add(new JButton("Gui"), BorderLayout.SOUTH);

        add(panel, BorderLayout.SOUTH);
        setSize(500, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ClientUI();
    }
}
