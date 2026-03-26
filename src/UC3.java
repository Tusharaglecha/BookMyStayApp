

import java.util.HashMap;


class RoomInventory {

    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int count) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, count);
        } else {
            System.out.println("Room type not found!");
        }
    }

    public void displayInventory() {
        System.out.println("Current Room Availability:\n");

        for (String roomType : inventory.keySet()) {
            System.out.println(roomType + ": " + inventory.get(roomType));
        }
    }
}

// Main Class
public class UC3 {

    public static void main(String[] args) {


        RoomInventory inventory = new RoomInventory();

        System.out.println("Welcome to BookMyStay - Inventory System\n");


        inventory.displayInventory();

        System.out.println("\nUpdating Double Room availability...\n");
        inventory.updateAvailability("Double Room", 4);


        inventory.displayInventory();

        System.out.println("\nSystem Ended.");
    }
}
