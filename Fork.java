public class Fork {

  public Fork(int x) {
    id = x;
    used = 0;
    by = -1;
  }

  private void delay(int value){
    try{
        Thread.sleep(value);
    }
    catch(InterruptedException e){
        System.out.println("Exception caught");
    }
  }

  public int Owner() {
      return by;
  }

  public synchronized boolean PickUp(int x) {
    if (used == 1) {
      return false;
    } else {
      used = 1;
      by = x;
      return true;
    }
  }

  public synchronized void PutDown(int x) {
    used = 0;
    by = -1;
  }
 
  private int id;
  private int used;
  private int by;
}
