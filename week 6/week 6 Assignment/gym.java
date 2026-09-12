class GymMember {

    protected String memberId;
    protected int monthlyFee;

    private int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {

        if (memberId == null ||
            memberId.trim().length() < 4) {

            throw new IllegalArgumentException(
                "Invalid member ID"
            );
        }

        if (monthlyFee <= 0) {
            throw new IllegalArgumentException(
                "Monthly fee must be positive"
            );
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public void displayInfo() {
        System.out.println(
            "Standard Member | Sessions: "
            + sessionsAttended
        );
    }

    public static String signUpBatch(
            String[] memberIds,
            int monthlyFee) {

        int signedUp = 0;
        int rejected = 0;

        for (String id : memberIds) {

            try {

                GymMember member =
                    new GymMember(id, monthlyFee);

                signedUp++;

            } catch (IllegalArgumentException e) {

                rejected++;
            }
        }

        return "Signed Up: " + signedUp
             + " | Rejected: " + rejected;
    }
}


class PremiumMember extends GymMember {

    private String trainerName;

    public PremiumMember(
            String memberId,
            int monthlyFee,
            String trainerName) {

        super(memberId, monthlyFee);

        this.trainerName = trainerName;
    }

    @Override
    public void displayInfo() {

        System.out.println(
            "Premium Member | Trainer: "
            + trainerName
            + " | Sessions: "
            + getSessionsAttended()
        );
    }
}


public class gym {

    public static void main(String[] args) {

        // Basic member
        GymMember m =
            new GymMember("MEM1", 1000);

        m.attendSession();
        m.attendSession();

        System.out.println(
            m.getSessionsAttended()
        );


        // Premium member
        PremiumMember p =
            new PremiumMember(
                "MEM01",
                2000,
                "Coach Riya"
            );

        p.attendSession();
        p.attendSession();

        System.out.println(
            p.getSessionsAttended()
        );


        // Batch signup
        String[] ids = {
            "MEM1",
            "GM1",
            "MEM2",
            " ",
            "MEM3"
        };

        System.out.println(
            GymMember.signUpBatch(ids, 1000)
        );
    }
}