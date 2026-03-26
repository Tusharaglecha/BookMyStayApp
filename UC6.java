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
}

class BookingQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.offer(r);
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void decrement(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}

class BookingService {
    private RoomInventory inventory;
    private Set<String> allocatedRoomIds = new HashSet<>();
    private HashMap<String, Set<String>> allocationMap = new HashMap<>();
    private int counter = 1;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void process(BookingQueue queue) {
        while (!queue.isEmpty()) {
            Reservation r = queue.getNextRequest();
            String type = r.getRoomType();

            if (inventory.getAvailability(type) > 0) {
                String roomId = generateRoomId(type);

                allocatedRoomIds.add(roomId);

                allocationMap.putIfAbsent(type, new HashSet<>());
                allocationMap.get(type).add(roomId);

                inventory.decrement(type);

                System.out.println("Booking Confirmed: " + r.getGuestName() + " -> " + roomId);
            } else {
                System.out.println("Booking Failed (No Availability): " + r.getGuestName() + " - " + type);
            }
        }
    }

    private String generateRoomId(String type) {
        String prefix = type.replace(" ", "").substring(0, 2).toUpperCase();
        String id;
        do {
            id = prefix + counter++;
        } while (allocatedRoomIds.contains(id));
        return id;
    }
}

public class UC6 {
    public static void main(String[] args) {
        BookingQueue queue = new BookingQueue();

        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Double Room"));
        queue.addRequest(new Reservation("Charlie", "Suite Room"));
        queue.addRequest(new Reservation("David", "Single Room"));
        queue.addRequest(new Reservation("Eve", "Single Room"));

        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService(inventory);

        service.process(queue);
    }
}
