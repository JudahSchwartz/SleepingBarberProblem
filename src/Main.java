import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    static final int NUMBER_OF_CLIENTS = 100;
    static final int NUMBER_OF_SEATS = 3;
    public static void main(String[] args) {
        Barber.setNumberOfClients(NUMBER_OF_CLIENTS);
        Client.setNumberOfSeats(NUMBER_OF_SEATS);
        System.out.println("Opening barber shop for the day.");
        Queue<Client> waitingRoom = new ConcurrentLinkedQueue<>();
        Barber barberOne = new Barber("Barber #1");
        barberOne.setWaitingRoom(waitingRoom);
        barberOne.start();
        Barber barberTwo = new Barber("Barber #2");
        barberTwo.setWaitingRoom(waitingRoom);
        barberTwo.start();
        for (int i = 0; i < NUMBER_OF_CLIENTS; i++)
        {
            var client = new Client("Client #" + i);
            client.setWaitingRoom(waitingRoom);
            client.start();
        }








    }
}
