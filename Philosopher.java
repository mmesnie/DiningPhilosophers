
public class Philosopher extends Thread {
  public Philosopher(int id) {
    this.id = id;
    left = id%5;
    right = (id + 1)%5;
    eating = false; 
    waiting_left = false; 
    waiting_right = false; 
    has_left = false; 
    has_right = false; 
    this.start();
  }

  private int think_range = 2000;
  private int min_think = 2000;
  private int wait_range = 100;
  private int min_wait = 10;
  private int eat_range = 4000;
  private int min_eat = 4000;
  private int id;
  private int left;
  private int right;
  private int width;

  public boolean eating; 
  public boolean waiting_left; 
  public boolean waiting_right; 
  public boolean has_left; 
  public boolean has_right; 
  public int eaten = 0;

  private void delay(int value){
    try{
        sleep(value);
    }
    catch(InterruptedException e){
        System.out.println("Exception caught");
    }
  }

/*
  public void run_test() {
    int randomNumber;
    int first, second;

    while (true) {

      if (id%2>0) {
        // odd philosophers pick up left then right
        first = left;
        second = right;
      } else {
        // even philosophers pick up right then right
        first = right;
        second = left;
      }

      // pickup first
      while (!DiningPhilosopher.myTable.fork[first].PickUp(id)) {
        //delay(1);
      } 

      // pickup second
      while (!DiningPhilosopher.myTable.fork[second].PickUp(id)) {
        //delay(1);
      } 

      // eat!
      System.out.printf("Philosopher[%d]: eating\n", id);
      randomNumber = (int) (Math.random()*10);
      delay(randomNumber*1000);

      // sanity check
      if ((DiningPhilosopher.myTable.fork[first].Owner()!=id)||(DiningPhilosopher.myTable.fork[second].Owner()!=id)) {
          System.out.printf("*** CRITICAL CORRUPTION ***\n");
      }

      // put down first and second
      DiningPhilosopher.myTable.fork[first].PutDown(id);
      DiningPhilosopher.myTable.fork[second].PutDown(id);

      // sleep
      System.out.printf("Philosopher[%d]: sleeping\n", id);
      randomNumber = (int) (Math.random()*10);
      delay(randomNumber*1000);
    }
  }
*/

  public void run() {

    int eat_time;
    int think_time;
    int wait_time;
    double theta;
    int x, y;
    int r1 = 120;
    int r2 = 100;
    int tmp;

    theta = id*(2*Math.PI/5) + 2*Math.PI/20;
    y = (int)(r1 * Math.sin(theta));
    x = (int)(r1 * Math.cos(theta));

    // Odd philosophers pick up left first then right. Even's other way.

    if (id%2==1) {
    } else {
      tmp = left;
      left = right;
      right = tmp;
    } 

    think_time = Math.abs(DiningPhilosopher.random.nextInt()%think_range);
    try {currentThread().sleep(think_time+min_think);}
    catch (InterruptedException E) {}

    while (true) {

     
      // Pick up first fork

      if (!DiningPhilosopher.myTable.fork[left].PickUp(id)) {
        if (id%2==1) {
          waiting_left = true;
          DiningPhilosopher.myTable.DrawPhilosopher(id);
        } else {
          waiting_right = true;
          DiningPhilosopher.myTable.DrawPhilosopher(id);
        }          
        while (!DiningPhilosopher.myTable.fork[left].PickUp(id)) { 
          wait_time = Math.abs(DiningPhilosopher.random.nextInt()%wait_range);
          try {currentThread().sleep(wait_time+min_wait);}
          catch (InterruptedException E) {}
          //currentThread().yield();
        } 
        waiting_left = false;
        waiting_right = false;
      }
      if (id%2==1) {
        DiningPhilosopher.myTable.DrawFork(id, 1);
        has_right = true;
      } else {
        DiningPhilosopher.myTable.DrawFork(id, 0);
        has_left = true;
      }

      // Pick up second fork

      if (!DiningPhilosopher.myTable.fork[right].PickUp(id)) {
        if (id%2==1) {
          waiting_right = true;
          DiningPhilosopher.myTable.DrawPhilosopher(id);
        } else {
          waiting_left = true;
          DiningPhilosopher.myTable.DrawPhilosopher(id);
        }          
	while (!DiningPhilosopher.myTable.fork[right].PickUp(id)) {
          wait_time = Math.abs(DiningPhilosopher.random.nextInt()%wait_range);
          try {currentThread().sleep(wait_time+min_wait);}
          catch (InterruptedException E) {}
          //currentThread().yield();
	}
        waiting_left = false;
        waiting_right = false;
      }
      if (id%2==1) {
        DiningPhilosopher.myTable.DrawFork(id, 0);
        has_left = true;
      } else {
        DiningPhilosopher.myTable.DrawFork(id, 1);
        has_right = true;
      }

      // Eat 

      eating = true;
      DiningPhilosopher.myTable.DrawPhilosopher(id);
      eat_time = Math.abs(DiningPhilosopher.random.nextInt()%eat_range);
      try {currentThread().sleep(eat_time+min_eat);}
      catch (InterruptedException E) {}
      //currentThread().yield();
      eaten++;

      // Put down forks

      DiningPhilosopher.myTable.fork[left].PutDown(id);
      if (id%2==1) {
        DiningPhilosopher.myTable.ClearFork(id, 1);
      } else {
        DiningPhilosopher.myTable.ClearFork(id, 0);
      }
      DiningPhilosopher.myTable.fork[right].PutDown(id);
      if (id%2==1) {
        DiningPhilosopher.myTable.ClearFork(id, 0);
      } else {
        DiningPhilosopher.myTable.ClearFork(id, 1);
      }
      has_left = false;
      has_right = false;

      // Think

      eating = false;
      DiningPhilosopher.myTable.DrawPhilosopher(id);
      think_time = Math.abs(DiningPhilosopher.random.nextInt()%think_range);
      try {currentThread().sleep(think_time+min_think);}
      catch (InterruptedException E) {}
      //currentThread().yield();
    }
  }
}
