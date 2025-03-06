import java.applet.*;
import java.awt.*;
import java.io.*;
import java.lang.*;
import java.util.*;

public class DiningPhilosopher extends Applet implements Runnable {
  public static final boolean applet = true;
  public static DiningTable myTable;
  public static Philosopher philosophers[] = new Philosopher[5];
  public static Graphics g;
  public static Font font;
  public static Random random = new Random();

  public void paint(Graphics g) {
    for (int i=0;i<5;i++) {
      if (philosophers[i].has_left) {
        myTable.DrawFork(i, 0);
      } else {
        myTable.ClearFork(i, 0);
      }
      if (philosophers[i].has_right) {
        myTable.DrawFork(i, 1);
      } else {
        myTable.ClearFork(i, 1);
      }
      myTable.DrawPhilosopher(i);
    }
    myTable.DrawTable();
  }

  public void init() {
    g = this.getGraphics();

    // Set the table

    myTable = new DiningTable(200, 200, 160, 135, 100);

    // Seat the philosophers

    philosophers[0] = new Philosopher(0);
    philosophers[1] = new Philosopher(1);
    philosophers[2] = new Philosopher(2);
    philosophers[3] = new Philosopher(3);
    philosophers[4] = new Philosopher(4);
  }
 
  private Thread animator_thread = null;
 
  public void stop() {
    if ((animator_thread == null)&&animator_thread.isAlive()) {
      animator_thread.stop();
    }
    animator_thread = null;
  }
  public void start() {
    if (animator_thread == null) {
      animator_thread = new Thread(this);
      animator_thread.start();
    }
  }
  public void run() {
    while (true) {
      try {animator_thread.sleep(10);}
      catch (InterruptedException E) {}
      g.drawOval(0, 0, 0, 0);
    }
  }
}
