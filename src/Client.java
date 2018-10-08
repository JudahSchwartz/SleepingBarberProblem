import java.util.Queue;

public class Client extends Thread {

    Queue<Client> waitingRoom;
    boolean finished = false;

    public Client(String s) {
        super(s);
    }

    public void setWaitingRoom(Queue<Client> queue)
    {
        waitingRoom = queue;
    }
    @Override
    public void run()
    {
        try {
            Thread.sleep((long) (Math.random()*5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            synchronized (this) {
                waitingRoom.add(this);
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getName() + " just woke up");
        getHairCut();
        System.out.println(getName()+ " exiting");
    }

    private void getHairCut() {
        System.out.printf("I, %s, have gotten my haircut.\n",getName() );
        finished = true;
    }
    public boolean isFinished()
    {
        return finished;
    }
}
