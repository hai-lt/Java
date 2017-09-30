package views.rmi;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.rmi.RemoteException;

public class ClientUI extends JFrame {
    public JTextField hostname, filename, result;
    public JButton btConnect, btSend, btOpenFile;
    public AtClient client;
    public ClientUI() {
        hostname = new JTextField("localhost");
        filename = new JTextField();
        result = new JTextField();
        btConnect = new JButton("Ket Noi");
        btSend = new JButton("Send");
        btOpenFile = new JButton("OpenFile");

        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Dia chi server: "), BorderLayout.WEST);
        panel.add(hostname, BorderLayout.CENTER);
        panel.add(btConnect, BorderLayout.EAST);
        add(panel, BorderLayout.NORTH);

        panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("File"), BorderLayout.WEST);
        panel.add(filename, BorderLayout.CENTER);
        panel.add(btOpenFile, BorderLayout.EAST);
        add(panel, BorderLayout.CENTER);

        panel = new JPanel(new BorderLayout());
        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(new JLabel("Ket qua: "), BorderLayout.WEST);
        panel1.add(result, BorderLayout.CENTER);
        panel.add(panel1, BorderLayout.NORTH);
        panel1.add(btSend, BorderLayout.SOUTH);

        add(panel, BorderLayout.SOUTH);
        setSize(500, 200);

        setEvent();
        setVisible(true);
    }

    private void setEvent() {
        btConnect.addActionListener(connectServerEvent());

        btSend.addActionListener(getSendingEvent());

        btOpenFile.addActionListener(getOpenFileEvent());
    }

    private ActionListener getOpenFileEvent() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    filename.setText(file.getAbsolutePath());
                }
            }
        };
    }

    private ActionListener getSendingEvent() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArraySerializable arraySerializable = readFile();
                try {
                    int response = client.clientAdd(arraySerializable);
                    result.setText(response + "");
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                    result.setText("Connection Failed");
                }
            }
        };
    }

    private ActionListener connectServerEvent() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client = new ClientImplement(hostname.getText());
                try {
                    client.connect();
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
        };
    }

    private ArraySerializable readFile(){
        return  null;
    }

    public static void main(String[] args) {
        new ClientUI();
    }
}
