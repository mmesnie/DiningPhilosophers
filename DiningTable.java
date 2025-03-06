import java.io.*;


public class DiningTable {

  public DiningTable(int cx, int cy, int table_width, int chair_radius, int philosopher_width) {
    fork[0] = new Fork(0);
    fork[1] = new Fork(1);
    fork[2] = new Fork(2);
    fork[3] = new Fork(3);
    fork[4] = new Fork(4);
    this.cx = cx;
    this.cy = cy;
    this.table_width = table_width;
    this.philosopher_width = philosopher_width;
    this.chair_radius = chair_radius;
    System.out.println("DiningTable: constructed");
  }

  public void DrawTable() {
    if (DiningPhilosopher.applet == false) {
      System.out.printf("FIX: DrawTable()\n");
    } else {
      synchronized(DiningPhilosopher.g) {
        java.awt.Color myBrown = new java.awt.Color(110, 110, 35);
        DiningPhilosopher.g.setColor(myBrown);
        DiningPhilosopher.g.fillOval(cx-table_width/2, cy-table_width/2, table_width, table_width);
        String string = "Dining Philosophers!";
        int pix_width = DiningPhilosopher.g.getFontMetrics().stringWidth(string); 
        int pix_height = DiningPhilosopher.g.getFontMetrics().getHeight(); 
        DiningPhilosopher.g.setColor(java.awt.Color.white);
        DiningPhilosopher.g.drawString(string, cx-pix_width/2, cy+pix_height/2);
      }
    }
  }

  public void DrawFork(int id, int which) {
    double theta;
    int x, y;
    int x2, y2;
    double offset;


    if (which == 0) {
      offset = 0.30;
    } else {
      offset = -0.30; 
    }

    theta = id*(2*Math.PI/5) + 2*Math.PI/20 + offset;
    x = (int)(chair_radius * Math.cos(theta));
    y = (int)(chair_radius * Math.sin(theta));
    x2 = (int)((DiningPhilosopher.myTable.table_width/2+2) * Math.cos(theta));
    y2 = (int)((DiningPhilosopher.myTable.table_width/2+2) * Math.sin(theta));

    if (DiningPhilosopher.applet == false) {
      System.out.printf("FIX: DrawFork(%d, %d)\n", id, which);
    } else {
      synchronized(DiningPhilosopher.g) {
        DiningPhilosopher.g.setColor(java.awt.Color.yellow);
        DiningPhilosopher.g.drawLine(cx+x2, cy-y2, cx+x, cy-y);
      }
    }
  }

  public void ClearFork(int id, int which) {
    double theta;
    int x, y;
    int x2, y2;
    double offset;

    if (which == 0) {
      offset = .30;
    } else {
      offset = -.30; 
    }

    theta = id*(2*Math.PI/5) + 2*Math.PI/20 + offset;
    x = (int)(chair_radius * Math.cos(theta));
    y = (int)(chair_radius * Math.sin(theta));
    x2 = (int)((DiningPhilosopher.myTable.table_width/2+2) * Math.cos(theta));
    y2 = (int)((DiningPhilosopher.myTable.table_width/2+2) * Math.sin(theta));

    if (DiningPhilosopher.applet == false) {
      System.out.printf("FIX: ClearFork(%d, %d)\n", id, which);
    } else {
      synchronized(DiningPhilosopher.g) {
        DiningPhilosopher.g.setColor(java.awt.Color.lightGray);
        DiningPhilosopher.g.drawLine(cx+x2, cy-y2, cx+x, cy-y);
      }
    }
  }

  public void DrawPhilosopher(int id) {
    double theta = id*(2*Math.PI/5) + 2*Math.PI/20;
    int x = (int)(chair_radius * Math.cos(theta));
    int y = (int)(chair_radius * Math.sin(theta));
    String string = "hello";

    if (DiningPhilosopher.philosophers[id].eating) {
      string = "eating!";
    } else if (DiningPhilosopher.philosophers[id].waiting_left) {
      string = "waiting on left";
    } else if (DiningPhilosopher.philosophers[id].waiting_right) {
      string = "waiting on right";
    } else {
      string = "thinking";
    }

    if (DiningPhilosopher.applet == false) {
      System.out.printf("FIX: DrawPhilosopher(%d): %s\n", id, string);
    } else {
      int pix_width = DiningPhilosopher.g.getFontMetrics().stringWidth(string); 
      int pix_height = DiningPhilosopher.g.getFontMetrics().getHeight(); 

      synchronized(DiningPhilosopher.g) {
        if (DiningPhilosopher.philosophers[id].eating) {
          DiningPhilosopher.g.setColor(java.awt.Color.green);
        } else {
          DiningPhilosopher.g.setColor(java.awt.Color.white);
        }
        DiningPhilosopher.g.fillOval(cx+x-philosopher_width/2, 
                                            cy-y-philosopher_width/2, 
                                            philosopher_width, philosopher_width);
        DiningPhilosopher.g.setColor(java.awt.Color.black);
        DiningPhilosopher.g.drawString(string, cx+x-pix_width/2, cy-y+pix_height/2);
        //DiningPhilosopher.g.drawString("eaten: "+DiningPhilosopher.philosophers[id].eaten, 
        //                               cx+x-pix_width/2, cy-y+pix_height/2+10);
      }
    }
  }

  public Fork fork[] = new Fork[5];
  public int cx;
  public int cy;
  public int table_width;
  public int philosopher_width;
  public int chair_radius;
}

