import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client extends JFrame implements ActionListener {
    // Text field for receiving measurements
    private JTextField jtf1 = new JTextField();
    private JTextField jtf2 = new JTextField();

    // Text area to display contents
    private JTextArea jta = new JTextArea();

    // IO streams
    private DataOutputStream outputToServer;
    private DataInputStream inputFromServer;

    public static void main(String[] args) {
        new Client();
    }

    //This GUI is absolutely screwed up, but it works if you press enter on both text boxes after giving data
    //... for some reason
    //Also the text fields are on either side of the text area???? idk why
    //This text entering system is incredibly scuffed, but it works, so that's cool... Man i dislike using a GUI
    public Client() {
        JPanel w = new JPanel();
        JPanel h = new JPanel();

        w.setLayout(new BorderLayout());
        w.add(new JLabel("Enter Weight in Kilograms"), BorderLayout.BEFORE_FIRST_LINE);
        w.add(jtf1, BorderLayout.CENTER);

        h.setLayout(new BorderLayout());
        h.add(new JLabel("Enter Height in Meters"), BorderLayout.NORTH);
        h.add(jtf2, BorderLayout.CENTER);

        jtf1.setHorizontalAlignment(JTextField.RIGHT);
        jtf2.setHorizontalAlignment(JTextField.RIGHT);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(w, BorderLayout.BEFORE_FIRST_LINE);
        getContentPane().add(h, BorderLayout.LINE_START);
        getContentPane().add(new JScrollPane(jta), BorderLayout.CENTER);

        // Register listeners
        jtf1.addActionListener(this);
        jtf2.addActionListener(this);

        setTitle("Client");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); // It is necessary to show the frame here!

        try {
            // Create a socket to connect to the server
            Socket socket = new Socket("localhost", 8888);
            // Socket socket = new Socket("130.254.204.36", 8000);
            // Socket socket = new Socket("drake.Armstrong.edu", 8000);

            // Create an input stream to receive data from the server
            inputFromServer = new DataInputStream(
                    socket.getInputStream());

            // Create an output stream to send data to the server
            outputToServer =
                    new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException ex) {
            jta.append(ex.toString() + '\n');
        }
    }

    public void actionPerformed(ActionEvent e) {
        //String actionCommand = e.getActionCommand();
        if (e.getSource() instanceof JTextField) {
            try {
                // Get the measurements from the text field
                double weightInKilograms = Double.parseDouble(jtf1.getText().trim());
                double heightInMeters  = Double.parseDouble(jtf2.getText().trim());

                // Send the measurements to the server
                outputToServer.writeDouble(weightInKilograms);
                outputToServer.writeDouble(heightInMeters);
                outputToServer.flush();

                // Get area from the server
                double bmi  = inputFromServer.readDouble();

                // Display to the text area
                jta.append("Weight is " + weightInKilograms + "\n");
                jta.append("Height is " + heightInMeters + "\n");
                jta.append("BMI received from the server is "
                        + bmi + '\n');
            }
            catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
}