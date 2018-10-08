import java.util.Queue;

public class Barber extends Thread {
    Queue<Client> waitingRoom;
    private static int numberToDo;
    private static final String numberDoneLock = "lock";
    private static int numberDone;
    public static void setNumberOfClients(int n)
    {
        numberToDo = n;
    }

    public Barber(String s) {
        super(s);
    }

    public void setWaitingRoom(Queue<Client> queue)
    {
        waitingRoom = queue;
    }
    @Override
    public void run()
    {
        while(numberDone < numberToDo)
        {
            //System.out.println(getName() + "I'm still running " + numberDone + "and" + numberToDo);
            Client client = null;
            synchronized (waitingRoom) {
                if (waitingRoom.size() > 0) {
                    client = waitingRoom.remove();
                    System.out.println(getName()+ " cutting hair of " + client.getName());
                }
            }
            //dont think I need to check for null
            if(client != null)
            {
                synchronized (client)
                {
                    client.notify();
                }
                synchronized (numberDoneLock)
                {
                    numberDone++;

                }
            }

        }
        System.out.println(getName() + " has determined there are no more customers to wait for");



    }
}
