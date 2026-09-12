class EventTicket {

    protected double basePrice;
    protected double amountPaid;

    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
        this.amountPaid = 0;
    }

    public void pay(double amount) {
        amountPaid += amount;
    }

    public double getBalanceDue() {
        return basePrice - amountPaid;
    }
}


class WorkshopTicket extends EventTicket {

    protected String track;

    public WorkshopTicket(double basePrice, String track) {

        super(basePrice);

        this.track = track;
    }
}


class PremiumWorkshopTicket extends WorkshopTicket {

    private double kitFee;

    public PremiumWorkshopTicket(double basePrice,
                                 String track,
                                 double kitFee) {

        super(basePrice, track);

        this.kitFee = kitFee;
    }

    public void display() {

        System.out.println("Track: " + track);
        System.out.println("Kit Fee: " + kitFee);
        System.out.println("Balance: " + getBalanceDue());
    }
}


public class MultilevelDemo {

    public static void main(String[] args) {

        PremiumWorkshopTicket p =
            new PremiumWorkshopTicket(
                2000,
                "Cloud Native",
                300
            );

        p.pay(1000);

        p.display();
    }
}