import java.util.*;

class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void display() {
        System.out.println("Guest: " + guestName + " | Room Type: " + roomType);
    }
}

class BookingQueue {
    private Queue<Reservation> queue;

    public BookingQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Request added for " + reservation.getGuestName());
    }

    public void displayQueue() {
        System.out.println("\nBooking Requests in Queue (FIFO Order):\n");

        if (queue.isEmpty()) {
            System.out.println("No booking requests.");
            return;
        }

        for (Reservation r : queue) {
            r.display();
        }
    }
}

public class UC5 {
    public static void main(String[] args) {
        System.out.println("Welcome to BookMyStay - Booking Request System\n");

        BookingQueue bookingQueue = new BookingQueue();

        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));
        bookingQueue.addRequest(new Reservation("David", "Single Room"));

        bookingQueue.displayQueue();

        System.out.println("\nAll requests stored. Waiting for allocation...");
    }
}
