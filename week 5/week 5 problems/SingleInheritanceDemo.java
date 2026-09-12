class EventTicket {

    private String attendeeId;
    protected double basePrice;
    protected double amountPaid;

    public EventTicket(String attendeeId, double basePrice) {
        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
        this.amountPaid = 0;
    }

    public void pay(double amount) {

        if (amount <= 0) {
            System.out.println("Payment rejected: must be positive");
            return;
        }

        amountPaid += amount;
    }

    public double getBalanceDue() {
        return basePrice - amountPaid;
    }
}


class WorkshopTicket extends EventTicket {

    private String track;

    public WorkshopTicket(String attendeeId,
                          double basePrice,
                          String track) {

        super(attendeeId, basePrice);
        this.track = track;
    }

    public void reserveMaterialsKit(double amount) {
        pay(amount);
    }

    public String getTrack() {
        return track;
    }
}


public class SingleInheritanceDemo {

    public static void main(String[] args) {

        WorkshopTicket w =
            new WorkshopTicket("STU2311002", 1200, "AI/ML");

        w.pay(500);

        System.out.println(w.getBalanceDue());
        System.out.println(w.getTrack());
    }
}