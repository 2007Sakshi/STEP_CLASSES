class EventTicket {

    protected double basePrice;
    protected double amountPaid;

    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
    }

    public void pay(double amount) {
        amountPaid += amount;
    }

    public double getBalanceDue() {
        return basePrice - amountPaid;
    }
}


class WorkshopTicket extends EventTicket {

    public WorkshopTicket(double price) {
        super(price);
    }

    public void showWorkshop() {
        System.out.println("Workshop Ticket");
    }
}


class HackathonTicket extends EventTicket {

    public HackathonTicket(double price) {
        super(price);
    }

    public void showHackathon() {
        System.out.println("Hackathon Ticket");
    }
}


public class HierarchicalDemo {

    public static void main(String[] args) {

        WorkshopTicket w =
            new WorkshopTicket(1200);

        HackathonTicket h =
            new HackathonTicket(800);

        w.showWorkshop();
        h.showHackathon();
    }
}