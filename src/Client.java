import java.util.Queue;
import java.util.Random;

public class Client extends Thread {

    Queue<Client> waitingRoom;
    static int numberOfSeats = 0;
    boolean finished = false;
    Random random = new Random();

    public Client(String s) {
        super(s);
    }

    public static void setNumberOfSeats(int numberOfSeats) {
        Client.numberOfSeats = numberOfSeats;
    }

    public void setWaitingRoom(Queue<Client> queue)
    {
        waitingRoom = queue;
    }
    @Override
    public void run()
    {
        try {
            var added = false;
            do {
                synchronized (this) {
                    synchronized (waitingRoom) {//get lock so seats noone sits while Im deciding to sit
                        if (waitingRoom.size() < numberOfSeats) {
                            waitingRoom.add(this);
                            added = true;
                            System.out.println(getName() + " sat in waiting room.");
                            System.out.println(" People in waiting room:  " + waitingRoom.size());
                        }
                    }//always release the lock so others can check waiting room
                    if(added) {
                        wait();
                    }
                    else {
                        System.out.println(getName() + "no room in waiting room, sleeping");
                        Thread.sleep(random.nextInt(2000));
                    }


                }
            }while(!added);
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
