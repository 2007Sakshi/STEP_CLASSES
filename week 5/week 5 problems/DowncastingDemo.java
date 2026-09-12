class EventTicket {

    public void show() {
        System.out.println("Event Ticket");
    }
}


class WorkshopTicket extends EventTicket {

    public void getTrack() {
        System.out.println("Track: AI/ML");
    }
}


public class DowncastingDemo {

    public static void main(String[] args) {

        EventTicket t =
            new WorkshopTicket();

        if (t instanceof WorkshopTicket) {

            WorkshopTicket w =
                (WorkshopTicket) t;

            w.getTrack();
        }
    }
}