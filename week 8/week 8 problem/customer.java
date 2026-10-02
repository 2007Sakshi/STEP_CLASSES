interface Vehicle {
    double calculateCharge(int days);
    String getType();
}

class Sedan implements Vehicle {
    public double calculateCharge(int days) {
        return days * 50;
    }

    public String getType() {
        return "Sedan";
    }
}

class SUV implements Vehicle {
    public double calculateCharge(int days) {
        return days * 80;
    }

    public String getType() {
        return "SUV";
    }
}

class Truck implements Vehicle {
    public double calculateCharge(int days) {
        return days * 100;
    }

    public String getType() {
        return "Truck";
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private int days;

    Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
    }

    void display() {
        System.out.println(vehicle.getType() + " rented successfully by "
                + customer.name + ".");
        System.out.printf("Rental charge: $%.2f%n",
                vehicle.calculateCharge(days));
    }
}

class VehicleItem {
    private String name;
    private Vehicle vehicle;
    private boolean available;

    VehicleItem(String name, Vehicle vehicle) {
        this.name = name;
        this.vehicle = vehicle;
        available = true;
    }

    boolean rent(Customer customer, int days) {
        if (!available) {
            System.out.println(name + " is currently unavailable.");
            return false;
        }

        available = false;
        Rental rental = new Rental(vehicle, customer, days);
        rental.display();
        return true;
    }

    void returnVehicle(Customer customer) {
        available = true;
        System.out.println(name + " returned by " + customer.name + ".");
    }
}

public class customer {
    public static void main(String[] args) {

        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");

        VehicleItem sedanA =
                new VehicleItem("Sedan A", new Sedan());

        VehicleItem suvB =
                new VehicleItem("SUV B", new SUV());

        sedanA.rent(c1, 3);

        sedanA.rent(c2, 2);

        sedanA.returnVehicle(c1);

        suvB.rent(c3, 5);
    }
}