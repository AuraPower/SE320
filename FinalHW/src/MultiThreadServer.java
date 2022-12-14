import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class MultiThreadServer extends JFrame {
    // Text area for displaying contents
    private JTextArea jta = new JTextArea();

    public static void main(String[] args) {
        new MultiThreadServer();
    }

    public MultiThreadServer() {
        // Place text area on the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JScrollPane(jta), BorderLayout.CENTER);

        setTitle("MultiThreadServer");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); // It is necessary to show the frame here!

        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8888);
            jta.append("MultiThreadServer started at " + new Date() + '\n');

            // Number a client
            int clientNo = 1;

            while (true) {
                // Listen for a new connection request
                Socket socket = serverSocket.accept();

                // Display the client number
                jta.append("Starting thread for client " + clientNo +
                        " at " + new Date() + '\n');

                // Find the client's host name, and IP address
                InetAddress inetAddress = socket.getInetAddress();
                jta.append("Client " + clientNo + "'s host name is "
                        + inetAddress.getHostName() + "\n");
                jta.append("Client " + clientNo + "'s IP Address is "
                        + inetAddress.getHostAddress() + "\n");

                // Create a new thread for the connection
                HandleAClient thread = new HandleAClient(socket);

                // Start the new thread
                thread.start();

                // Increment clientNo
                clientNo++;
            }
        }
        catch(IOException ex) {
            System.err.println(ex);
        }
    }

    // Inner class
// Define the thread class for handling new connection
    class HandleAClient extends Thread {
        private Socket socket; // A connected socket

        /** Construct a thread */
        public HandleAClient(Socket socket) {
            this.socket = socket;
        }

        /** Run a thread */
        public void run() {
            try {
                // Create data input and output streams
                DataInputStream inputFromClient = new DataInputStream(
                        socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(
                        socket.getOutputStream());

                // Continuously serve the client
                while (true) {
                    // Receive measurements from the client
                    double weightInKilograms  = inputFromClient.readDouble();
                    double heightInMeters = inputFromClient.readDouble();

                    // Compute area
                    double bmi = weightInKilograms / (heightInMeters * heightInMeters);

                    // Send area back to the client
                    outputToClient.writeDouble(bmi);

                    jta.append("Weight received from client: " + weightInKilograms + '\n');
                    jta.append("Height received from client: " + heightInMeters + '\n');
                    jta.append("BMI found: " + bmi + '\n');
                }
            }
            catch(IOException e) {
                System.err.println(e);
            }
        }
    }
}