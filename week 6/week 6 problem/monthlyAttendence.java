class GymMember {

    protected String memberId;

    GymMember(String memberId) {
        this.memberId = memberId;
    }

    public void displayInfo() {
        System.out.println("Member ID: " + memberId);
    }
}

class PremiumMember extends GymMember {

    private String trainerName;

    PremiumMember(String memberId, String trainerName) {
        super(memberId);
        this.trainerName = trainerName;
    }

    @Override
    public void displayInfo() {
        System.out.println("Premium Member: " + memberId);
        System.out.println("Trainer: " + trainerName);
    }

    public String getTrainerName() {
        return trainerName;
    }
}

public class monthlyAttendence {

    static void batchPrint(GymMember[] members) {

        for (GymMember member : members) {

            if (member == null)
                continue;

            // Runtime polymorphism
            member.displayInfo();

            // Safe downcasting
            if (member instanceof PremiumMember) {

                PremiumMember premium =
                        (PremiumMember) member;

                System.out.println(
                    "Assigned Trainer: "
                    + premium.getTrainerName()
                );
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        GymMember[] members = {
            new GymMember("G001"),
            new PremiumMember("P001", "Rahul"),
            new GymMember("G002"),
            new PremiumMember("P002", "Aman")
        };

        batchPrint(members);
    }
}