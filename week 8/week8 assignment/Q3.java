import java.util.*;

interface Seat {
    String getId();
    double getPrice();
}

class RegularSeat implements Seat {
    private String id;

    RegularSeat(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return 150;
    }
}

class PremiumSeat implements Seat {
    private String id;

    PremiumSeat(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return 250;
    }
}

class ReclinerSeat implements Seat {
    private String id;

    ReclinerSeat(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return 400;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Show {
    private String time;
    private boolean started = false;
    private Set<String> bookedSeats = new HashSet<>();

    Show(String time) {
        this.time = time;
    }

    public boolean isAvailable(Seat seat) {
        return !bookedSeats.contains(seat.getId());
    }

    public boolean bookSeat(Seat seat) {
        if (!isAvailable(seat))
            return false;

        bookedSeats.add(seat.getId());
        return true;
    }

    public void releaseSeat(Seat seat) {
        bookedSeats.remove(seat.getId());
    }

    public boolean hasStarted() {
        return started;
    }

    public void startShow() {
        started = true;
    }
}

class Booking {
    private Customer customer;
    private Show show;
    private List<Seat> seats = new ArrayList<>();
    private boolean cancelled = false;

    Booking(Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;

        if (seats.size() > 6) {
            System.out.println("Maximum 6 seats allowed.");
            return;
        }

        for (Seat seat : seats) {
            if (!show.bookSeat(seat)) {
                System.out.println("Seat " + seat.getId() +
                        " is already booked for this show.");
                for (Seat booked : this.seats)
                    show.releaseSeat(booked);
                this.seats.clear();
                return;
            }
            this.seats.add(seat);
        }

        System.out.print("Booking confirmed for " +
                customer.name + ": ");

        for (Seat seat : seats)
            System.out.print(seat.getId() + " ");

        System.out.printf("%nTotal: ₹%.2f%n", getTotal());
    }

    public double getTotal() {
        double total = 0;

        for (Seat seat : seats)
            total += seat.getPrice();

        return total;
    }

    public void cancel() {
        if (show.hasStarted()) {
            System.out.println("Cannot cancel: show has already started.");
            return;
        }

        if (!cancelled) {
            for (Seat seat : seats)
                show.releaseSeat(seat);

            cancelled = true;

            System.out.println(customer.name +
                    "'s booking cancelled.");

            System.out.print("Seats released: ");

            for (Seat seat : seats)
                System.out.print(seat.getId() + " ");

            System.out.println();
        }
    }
}

public class Q3 {
    public static void main(String[] args) {

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Show show = new Show("7 PM");

        Seat a1 = new RegularSeat("A1");
        Seat a2 = new RegularSeat("A2");
        Seat f5 = new PremiumSeat("F5");
        Seat r1 = new ReclinerSeat("R1");

        List<Seat> ashaSeats =
                Arrays.asList(a1, a2, f5);

        Booking b1 = new Booking(asha, show, ashaSeats);

        List<Seat> raviSeats =
                Arrays.asList(a2);

        Booking b2 = new Booking(ravi, show, raviSeats);

        List<Seat> raviSeats2 =
                Arrays.asList(r1);

        Booking b3 = new Booking(ravi, show, raviSeats2);

        b1.cancel();

        List<Seat> nehaSeats =
                Arrays.asList(a2);

        Booking b4 = new Booking(neha, show, nehaSeats);
    }
}