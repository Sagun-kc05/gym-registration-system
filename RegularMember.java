
public class RegularMember extends GymMember {
    private final int attendanceLimit;
    private boolean isEligibleForUpgrade;
    private String removalReason;
    private String referralSource;
    private String plan;
    private double price;

    //Constructor to initialize a RegularMember

    public RegularMember(int id, String name, String location, String phone, String email, String gender, String dobYear, String dobMonth, String dobDay, 
    String msYear, String msMonth, String msDay, String referralSource) {
        super(id, name, location, phone, email, gender, dobYear, dobMonth, dobDay, msYear, msMonth, msDay );
        this.attendanceLimit = 30;
        this.isEligibleForUpgrade = false;
        this.removalReason = "";
        this.referralSource = referralSource;
        this.plan = "basic";
        this.price = 6500;
    }

    //getter method

    public int getattendanceLimit(){
        return this.attendanceLimit;
    }

    public boolean getisEligibleForUpgrade(){
        return this.isEligibleForUpgrade;
    }

    public String getremovalReason(){
        return this.removalReason;
    }

    public String getreferralSource(){
        return this.referralSource;
    }

    public String getplan(){
        return this.plan;
    }

    public double getprice(){
        return this.price;
    }

    /**
     * Marks attendance for the member, increments loyalty points, 
     * and checks if the member is eligible for an upgrade.
     */

    @Override
    public void markAttendance() {
        this.attendance++;
        this.loyaltyPoints += 5;
    }

    //Returns the price of a given membership plan

    public double getPlanPrice(String plan) {
        switch (plan) {
            case "basic": return 6500;
            case "standard": return 12500;
            case "deluxe": return 18500;
            default: return -1;
        }
    }

    //Upgrades the membership plan if the member is eligible
    public String upgradePlan(String plan) {
        double newPrice = getPlanPrice(plan);

        if (newPrice == -1) {
            return "Invalid plan.";
        }

        // Check if plan is already suscribed

        if (this.plan.equals(plan)) {
            return "Plan is already suscribed.";
        }

        if (getAttendance() >= attendanceLimit) {
            this.isEligibleForUpgrade = true;
        }

        // Upgrade plan if eligible

        if (this.isEligibleForUpgrade == true) {
            this.plan = plan;//current subscription updated to the new plan
            this.price = newPrice;
            return "Plan upgraded to " + plan + ".";
        }

        return "Not eligible for an upgrade yet.";
    }

    //Resets the RegularMember's details and records the reason for removal
    public void revertRegularMember(String removalReason) {
        super.resetMember();
        this.isEligibleForUpgrade = false;
        this.plan = "basic";
        this.price = 6500;
        this.removalReason = removalReason;
    }

    //Displays the RegularMember's details

    @Override
    public void display() {
        super.display();//Calls the display method from GymMember
        System.out.println("Plan: " + plan + ", Price: " + price);
        if (!removalReason.isEmpty()) {
            System.out.println("Removal Reason: " + removalReason);
        }
    }

    @Override
    public String toString() {
        return "Regular Member\n"
        + "ID: " + getId() + "\n"
        + "Name: " + getName() + "\n"
        + "Location: " + getLocation() + "\n"
        + "Phone: " + getPhone() + "\n"
        + "Email: " + getEmail() + "\n"
        + "Gender: " + getGender() + "\n"
        + "Active Status: " + activeStatus + "\n"
        + "Date of Birth: " + getdobYear() + "-" + getdobMonth() + "-" + getdobDay() + "\n"
        + "Membership Start Date: " + getmsYear() + "-" + getmsMonth() + "-" + getmsDay() +"\n"
        + "Referral Source: " + getreferralSource() + "\n"
        + "Plan: " + getplan() + "\n"
        + "Price: " + getprice();
    }

}