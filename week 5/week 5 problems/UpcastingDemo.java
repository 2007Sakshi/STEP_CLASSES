class EventTicket {

    public void show() {
        System.out.println("Event Ticket");
    }
}


class WorkshopTicket extends EventTicket {

    public void workshop() {
        System.out.println("Workshop Ticket");
    }
}


public class UpcastingDemo {

    public static void main(String[] args) {

        WorkshopTicket child =
            new WorkshopTicket();

        EventTicket parent = child;

        parent.show();
    }
}