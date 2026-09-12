class GymMember {
    protected String memberId;
    protected int monthlyFee;

    GymMember(String memberId, int monthlyFee) {
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    public void attendSession() {
        System.out.println(memberId + " attended a session");
    }
}

class PremiumMember extends GymMember {

    PremiumMember(String memberId, int monthlyFee) {
        super(memberId, monthlyFee);
    }
}

class EliteMember extends PremiumMember {

    EliteMember(String memberId, int monthlyFee) {
        super(memberId, monthlyFee);
    }
}

class GroupClassMember extends GymMember {

    GroupClassMember(String memberId, int monthlyFee) {
        super(memberId, monthlyFee);
    }
}

public class member {

    static String classifyGeneration(GymMember member) {

        if (member instanceof EliteMember)
            return "Elite Member";

        if (member instanceof PremiumMember)
            return "Premium Member";

        if (member instanceof GroupClassMember)
            return "Group Class Member";

        return "Gym Member";
    }

    static int getTotalSessionsAttended(GymMember[] members) {

        int total = 0;

        for (GymMember member : members) {
            if (member != null) {
                member.attendSession();
                total++;
            }
        }

        return total;
    }

    public static void main(String[] args) {

        GymMember[] members = {
            new GymMember("G001", 1000),
            new PremiumMember("P001", 2000),
            new EliteMember("E001", 3000),
            new GroupClassMember("G002", 1500)
        };

        for (GymMember member : members) {
            System.out.println(classifyGeneration(member));
        }

        System.out.println("Total Sessions: "
                + getTotalSessionsAttended(members));
    }
}