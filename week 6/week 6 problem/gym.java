class GymMember {
    protected String memberId;
    protected int monthlyFee;
    private int sessionsAttended;

    GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.length() < 4)
            throw new IllegalArgumentException("Invalid Member ID");

        if (monthlyFee <= 0)
            throw new IllegalArgumentException("Invalid Monthly Fee");

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        sessionsAttended = 0;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public void displayInfo() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Monthly Fee: " + monthlyFee);
        System.out.println("Sessions: " + sessionsAttended);
    }
}

class PremiumMember extends GymMember {
    private String trainerName;

    PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Trainer: " + trainerName);
    }
}

public class gym {

    static String signUpBatch(String[] memberIds, int monthlyFee) {
        int signedUp = 0;
        int rejected = 0;

        for (String id : memberIds) {
            try {
                new GymMember(id, monthlyFee);
                signedUp++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Signed Up: " + signedUp + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {

        String[] ids = {"GYM1", "A12", "GYM002", "XYZ", "MEMBER5"};

        System.out.println(signUpBatch(ids, 2000));
    }
}