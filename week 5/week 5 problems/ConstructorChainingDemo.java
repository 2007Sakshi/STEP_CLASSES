class EventTicket {

    EventTicket() {
        System.out.println("EventTicket constructor");
    }
}


class WorkshopTicket extends EventTicket {

    WorkshopTicket() {

        super();

        System.out.println("WorkshopTicket constructor");
    }
}


class PremiumWorkshopTicket extends WorkshopTicket {

    PremiumWorkshopTicket() {

        super();

        System.out.println(
            "PremiumWorkshopTicket constructor"
        );
    }
}


public class ConstructorChainingDemo {

    public static void main(String[] args) {

        PremiumWorkshopTicket p =
            new PremiumWorkshopTicket();
    }
}