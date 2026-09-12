class EventTicket {

    protected String attendeeId;
    protected double basePrice;
    protected double amountPaid;

    public EventTicket(String attendeeId,
                       double basePrice) {

        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
        this.amountPaid = 0;
    }


    public void pay(double amount) {

        if (amount <= 0) {
            System.out.println(
                "Payment rejected"
            );
            return;
        }

        amountPaid += amount;
    }


    public double getBalanceDue() {
        return basePrice - amountPaid;
    }


    public void printTicket() {

        System.out.println(
            "Standard Event Ticket | Balance Due: "
            + getBalanceDue()
        );
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


    public String getTrack() {
        return track;
    }


    @Override
    public void printTicket() {

        System.out.println(
            "Workshop Ticket | Track: "
            + track
            + " | Balance Due: "
            + getBalanceDue()
        );
    }
}


class PremiumWorkshopTicket
        extends WorkshopTicket {

    private double kitFee;


    public PremiumWorkshopTicket(
            String attendeeId,
            double basePrice,
            String track,
            double kitFee) {

        super(
            attendeeId,
            basePrice,
            track
        );

        this.kitFee = kitFee;
    }


    @Override
    public void printTicket() {

        System.out.println(
            "Premium Workshop Ticket | Track: "
            + getTrack()
            + " | Kit Fee: "
            + kitFee
            + " | Balance Due: "
            + getBalanceDue()
        );
    }
}


class HackathonTicket extends EventTicket {

    private String team;


    public HackathonTicket(String attendeeId,
                           double basePrice,
                           String team) {

        super(attendeeId, basePrice);

        this.team = team;
    }


    @Override
    public void printTicket() {

        System.out.println(
            "Hackathon Ticket | Team: "
            + team
            + " | Balance Due: "
            + getBalanceDue()
        );
    }
}


public class tickets
 {

    public static void main(String[] args) {

        EventTicket[] tickets = {

            new EventTicket(
                "STU2311001",
                500
            ),

            new WorkshopTicket(
                "STU2311002",
                1200,
                "AI/ML"
            ),

            new PremiumWorkshopTicket(
                "STU2311003",
                2000,
                "Cloud Native",
                300
            ),

            new HackathonTicket(
                "STU2311004",
                800,
                "Byte Force"
            )
        };


        for (EventTicket t : tickets) {

            // Runtime polymorphism
            t.printTicket();


            // Downcasting
            if (t instanceof WorkshopTicket) {

                WorkshopTicket w =
                    (WorkshopTicket) t;

                System.out.println(
                    "Track: "
                    + w.getTrack()
                );
            }
        }
    }
}