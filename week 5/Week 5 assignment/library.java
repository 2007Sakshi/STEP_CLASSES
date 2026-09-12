import java.security.MessageDigest;

public class library{

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswerHash;


    // Public no-argument constructor
    public library() {
    }


    // Membership ID getter
    public String getMembershipId() {
        return membershipId;
    }


    // Membership ID setter
    // Works only first time
    public void setMembershipId(String id) {

        if (membershipId == null) {
            membershipId = id;
        }
    }


    // Name getter
    public String getName() {
        return name;
    }


    // Name setter
    public void setName(String name) {
        this.name = name;
    }


    // Premium getter
    public boolean isPremiumMember() {
        return premiumMember;
    }


    // Premium setter
    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }


    // Security answer setter
    // NO GETTER
    public void setSecurityAnswer(String answer) {

        try {

            MessageDigest md =
                MessageDigest.getInstance("SHA-256");

            byte[] hash =
                md.digest(answer.getBytes());

            StringBuilder result = new StringBuilder();

            for (byte b : hash) {
                result.append(
                    String.format("%02x", b)
                );
            }

            securityAnswerHash = result.toString();

        } catch (Exception e) {
            // Silently handle exception
        }
    }


    public static void main(String[] args) {

        Problem4 m = new Problem4();

        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);

        System.out.println(
            m.getMembershipId()
        );

        System.out.println(
            m.getName()
        );

        System.out.println(
            m.isPremiumMember()
        );


        // Second attempt is ignored
        m.setMembershipId("FAKE-0000");

        System.out.println(
            m.getMembershipId()
        );


        // Write-only security answer
        m.setSecurityAnswer("BlueMountain");

        System.out.println(
            "Security answer stored safely"
        );
    }
}