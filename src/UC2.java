
abstract class Room {
    int beds;
    double price;
    int availableRooms;


    Room(int beds, double price, int availableRooms) {
        this.beds = beds;
        this.price = price;
        this.availableRooms = availableRooms;
    }


    abstract void displayDetails();
}


class SingleRoom extends Room {

    SingleRoom(int availableRooms) {
        super(1, 1000.0, availableRooms);
    }

    void displayDetails() {
        System.out.println("Room Type: Single Room");
        System.out.println("Beds: " + beds);
        System.out.println("Price: ₹" + price);
        System.out.println("Available Rooms: " + availableRooms);
        System.out.println("--------------------------");
    }
}


class DoubleRoom extends Room {

    DoubleRoom(int availableRooms) {
        super(2, 2000.0, availableRooms);
    }

    void displayDetails() {
        System.out.println("Room Type: Double Room");
        System.out.println("Beds: " + beds);
        System.out.println("Price: ₹" + price);
        System.out.println("Available Rooms: " + availableRooms);
        System.out.println("--------------------------");
    }
}

class SuiteRoom extends Room {

    SuiteRoom(int availableRooms) {
        super(3, 5000.0, availableRooms);
    }

    void displayDetails() {
        System.out.println("Room Type: Suite Room");
        System.out.println("Beds: " + beds);
        System.out.println("Price: ₹" + price);
        System.out.println("Available Rooms: " + availableRooms);
        System.out.println("--------------------------");
    }
}

public class UC2 {

    public static void main(String[] args) {

        Room single = new SingleRoom(5);
        Room dbl = new DoubleRoom(3);
        Room suite = new SuiteRoom(2);


        System.out.println("Welcome to BookMyStay - Room Availability\n");

        single.displayDetails();
        dbl.displayDetails();
        suite.displayDetails();

        System.out.println("Thank you for using BookMyStay!");
    }
}