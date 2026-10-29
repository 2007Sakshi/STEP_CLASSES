
import java.util.*;

interface Chargeable {
    void charge();
}

abstract class Vehicle {
    String passNumber;
    String owner;

    Vehicle(String passNumber, String owner) {
        this.passNumber = passNumber;
        this.owner = owner;
    }

    abstract int passFee();

    void printPass(String type) {
        System.out.println(passNumber + " (" + type
                + ") pass fee " + passFee());
    }
}

class Bike extends Vehicle {
    Bike(String p, String o) {
        super(p, o);
    }

    int passFee() {
        return 300;
    }
}

class Car extends Vehicle {
    Car(String p, String o) {
        super(p, o);
    }

    int passFee() {
        return 1000;
    }
}

class EBike extends Vehicle implements Chargeable {
    EBike(String p, String o) {
        super(p, o);
    }

    int passFee() {
        return 300;
    }

    public void charge() {
        System.out.println(passNumber + " charging bay allotted");
    }
}

class ECar extends Vehicle implements Chargeable {
    ECar(String p, String o) {
        super(p, o);
    }

    int passFee() {
        return 1000;
    }

    public void charge() {
        System.out.println(passNumber + " charging bay allotted");
    }
}

public class vehicle {
    public static void main(String[] args) {
        Map<String, Vehicle> vehicles = new HashMap<>();

        Vehicle v1 = new Bike("KA01", "Asha");
        Vehicle v2 = new ECar("KA02", "Ravi");

        vehicles.put("KA01", v1);
        vehicles.put("KA02", v2);

        v1.printPass("Bike");
        v2.printPass("ECar");

        chargeVehicle(vehicles, "KA02");
        chargeVehicle(vehicles, "KA01");
    }

    static void chargeVehicle(Map<String, Vehicle> vehicles,
                              String id) {
        Vehicle v = vehicles.get(id);

        if (v == null) {
            System.out.println("Vehicle not found");
        } else if (v instanceof Chargeable) {
            ((Chargeable) v).charge();
        } else {
            System.out.println(
                id + " rejected: charging unsupported"
            );
        }
    }
}
