class EventTicket {

    public void printTicket() {
        System.out.println("Standard Event Ticket");
    }
}


class WorkshopTicket extends EventTicket {

    @Override
    public void printTicket() {
        System.out.println("Workshop Ticket");
    }
}


class HackathonTicket extends EventTicket {

    @Override
    public void printTicket() {
        System.out.println("Hackathon Ticket");
    }
}


public class PolymorphismDemo {

    public static void main(String[] args) {

        EventTicket t1 =
            new EventTicket();

        EventTicket t2 =
            new WorkshopTicket();

        EventTicket t3 =
            new HackathonTicket();

        t1.printTicket();
        t2.printTicket();
        t3.printTicket();
    }
}