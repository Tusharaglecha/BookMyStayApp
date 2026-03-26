import java.util.*;

class AddOnService {
    private String name;
    private double cost;

    public AddOnService(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }
}

class AddOnServiceManager {
    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    public void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    public void displayServices(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services for " + reservationId);
            return;
        }

        System.out.println("Services for " + reservationId + ":");
        for (AddOnService s : services) {
            System.out.println("- " + s.getName() + " : ₹" + s.getCost());
        }
    }

    public double calculateTotalCost(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);
        double total = 0;

        if (services != null) {
            for (AddOnService s : services) {
                total += s.getCost();
            }
        }
        return total;
    }
}

public class UC7 {
    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String res1 = "SR1";
        String res2 = "DR1";

        manager.addService(res1, new AddOnService("Breakfast", 200));
        manager.addService(res1, new AddOnService("Airport Pickup", 500));
        manager.addService(res2, new AddOnService("Extra Bed", 300));

        manager.displayServices(res1);
        System.out.println("Total Add-on Cost: ₹" + manager.calculateTotalCost(res1));

        System.out.println();

        manager.displayServices(res2);
        System.out.println("Total Add-on Cost: ₹" + manager.calculateTotalCost(res2));
    }
}
