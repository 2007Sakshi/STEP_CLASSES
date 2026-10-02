import java.time.LocalDate;
import java.util.*;

interface Room {
    double calculatePrice(long days);
    String getType();
}

class StandardRoom implements Room {

    public double calculatePrice(long days) {
        return days * 100;
    }

    public String getType() {
        return "Standard";
    }
}

class DeluxeRoom implements Room {

    public double calculatePrice(long days) {
        return days * 180;
    }

    public String getType() {
        return "Deluxe";
    }
}

class Suite implements Room {

    public double calculatePrice(long days) {
        return days * 300;
    }

    public String getType() {
        return "Suite";
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Reservation {

    Customer customer;
    Room room;
    String roomNumber;
    LocalDate startDate;
    LocalDate endDate;
    LocalDate cancellationDeadline;
    boolean cancelled = false;

    Reservation(Customer customer, Room room,
                String roomNumber,
                LocalDate startDate,
                LocalDate endDate,
                LocalDate cancellationDeadline) {

        this.customer = customer;
        this.room = room;
        this.roomNumber = roomNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cancellationDeadline = cancellationDeadline;
    }

    boolean overlaps(LocalDate start, LocalDate end) {

        return start.isBefore(endDate) &&
               end.isAfter(startDate);
    }

    void display() {

        long days =
                java.time.temporal.ChronoUnit.DAYS
                .between(startDate, endDate);

        System.out.println(
                "Reservation confirmed for " +
                customer.name + ", " +
                room.getType() + " Room " +
                roomNumber + " (" +
                startDate + "-" + endDate + ").");

        System.out.printf("Price: $%.2f%n",
                room.calculatePrice(days));
    }

    void cancel(LocalDate currentDate) {

        if (currentDate.isAfter(cancellationDeadline)) {
            System.out.println(
                    "Cancellation deadline has passed.");
            return;
        }

        cancelled = true;

        System.out.println(
                "Reservation for " +
                customer.name + ", " +
                room.getType() + " Room " +
                roomNumber +
                " cancelled successfully.");
    }
}

class Hotel {

    List<Reservation> reservations = new ArrayList<>();

    boolean isAvailable(String roomNumber,
                        LocalDate start,
                        LocalDate end) {

        for (Reservation r : reservations) {

            if (r.roomNumber.equals(roomNumber) &&
                !r.cancelled &&
                r.overlaps(start, end)) {

                return false;
            }
        }

        return true;
    }

    void showAvailability(String roomNumber,
                           LocalDate start,
                           LocalDate end) {

        if (isAvailable(roomNumber, start, end)) {
            System.out.println(
                    "Standard Room " + roomNumber +
                    " is available from " +
                    start + " to " + end + ".");
        } else {
            System.out.println(
                    "Standard Room " + roomNumber +
                    " is not available from " +
                    start + " to " + end + ".");
        }
    }

    void book(Customer customer,
              Room room,
              String roomNumber,
              LocalDate start,
              LocalDate end) {

        if (!isAvailable(roomNumber, start, end)) {
            System.out.println(
                    room.getType() + " Room " +
                    roomNumber +
                    " is not available.");
            return;
        }

        LocalDate deadline = start.minusDays(1);

        Reservation r = new Reservation(
                customer, room, roomNumber,
                start, end, deadline);

        reservations.add(r);

        r.display();
    }
}

public class hotel {

    public static void main(String[] args) {

        Hotel hotel = new Hotel();

        Customer a = new Customer("Customer A");
        Customer b = new Customer("Customer B");
        Customer c = new Customer("Customer C");

        LocalDate jan1 =
                LocalDate.of(2026, 1, 1);

        LocalDate jan5 =
                LocalDate.of(2026, 1, 5);

        LocalDate jan3 =
                LocalDate.of(2026, 1, 3);

        LocalDate jan7 =
                LocalDate.of(2026, 1, 7);

        hotel.showAvailability(
                "101", jan1, jan5);

        hotel.book(
                a,
                new StandardRoom(),
                "101",
                jan1,
                jan5);

        hotel.book(
                b,
                new StandardRoom(),
                "101",
                jan3,
                jan7);

        Reservation first =
                hotel.reservations.get(0);

        first.cancel(
                LocalDate.of(2025, 12, 30));

        hotel.book(
                c,
                new DeluxeRoom(),
                "201",
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 12));
    }
}