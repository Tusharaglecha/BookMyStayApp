

import java.util.*;

// ---------------- ROOM DOMAIN MODEL ----------------
abstract class Room {
    String type;
    int beds;
    double price;

    Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    public void displayDetails(int availability) {
        System.out.println("Room Type: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Price: ₹" + price);
        System.out.println("Available Rooms: " + availability);
        System.out.println("---------------------------");
    }
}

class SingleRoom extends Room {
    SingleRoom() {
        super("Single Room", 1, 1000);
    }
}

class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double Room", 2, 2000);
    }
}

class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 3, 5000);
    }
}

// ---------------- INVENTORY (READ-ONLY ACCESS) ----------------
class RoomInventory {
    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 0); // Example: unavailable
    }

    // Read-only method
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public Set<String> getRoomTypes() {
        return inventory.keySet();
    }
}

// ---------------- SEARCH SERVICE ----------------
class SearchService {

    private RoomInventory inventory;
    private Map<String, Room> roomMap;

    SearchService(RoomInventory inventory) {
        this.inventory = inventory;

        // Room objects (domain model)
        roomMap = new HashMap<>();
        roomMap.put("Single Room", new SingleRoom());
        roomMap.put("Double Room", new DoubleRoom());
        roomMap.put("Suite Room", new SuiteRoom());
    }

    // Read-only search
    public void searchAvailableRooms() {
        System.out.println("Available Rooms:\n");

        for (String type : inventory.getRoomTypes()) {
            int available = inventory.getAvailability(type);

            // Filter unavailable rooms
            if (available > 0) {
                Room room = roomMap.get(type);

                // Defensive check
                if (room != null) {
                    room.displayDetails(available);
                }
            }
        }
    }
}

// ---------------- MAIN CLASS ----------------
public class UC4 {

    public static void main(String[] args) {

        System.out.println("Welcome to BookMyStay - Room Search\n");

        RoomInventory inventory = new RoomInventory();
        SearchService searchService = new SearchService(inventory);

        // Perform search (READ-ONLY)
        searchService.searchAvailableRooms();

        System.out.println("Search completed. System state unchanged.");
    }
}
