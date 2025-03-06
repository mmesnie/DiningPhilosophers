import java.awt.*; // for Graphics (even though we don't use it)
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.util.Random;

public class DiningPhilosopher {
    public static final boolean applet = false;
    public static DiningTable myTable;
    public static Philosopher philosophers[] = new Philosopher[5];
    public static Random random = new Random();
    public static Graphics g;

    public static void main(String[] args) {

        // Set the table
	myTable = new DiningTable(200, 200, 160, 135, 100);
	myTable.DrawTable();

        // Seat the philosophers
	philosophers[0] = new Philosopher(0);
	philosophers[1] = new Philosopher(1);
	philosophers[2] = new Philosopher(2);
	philosophers[3] = new Philosopher(3);
	philosophers[4] = new Philosopher(4);

        // Create a new JFrame (window)
        JFrame frame = new JFrame("Hello, World!");

        // Set the frame's size
        frame.setSize(300, 150);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Set default close operation (exit the application when the window is closed)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JLabel with the "Hello, World!" text
        JLabel label = new JLabel("Hello, World!", SwingConstants.CENTER);

        // Add the label to the frame's content pane
        frame.getContentPane().add(label);

        // Set the frame to be visible
        frame.setVisible(true);
    }
}
