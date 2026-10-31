
public class PremiumMember extends GymMember {
    final double premiumCharge = 50000;
    String trainerName;
    boolean isFullPayment;
    double paidAmount;
    double discountAmount;

    // Constructor to initialize PremiumMember

    public PremiumMember(int id, String name, String location, String phone, String email, String gender, String dobYear, String dobMonth, String dobDay, 
    String msYear, String msMonth, String msDay, String trainerName) {
        super(id, name, location, phone, email, gender, dobYear, dobMonth, dobDay, msYear, msMonth, msDay);
        this.trainerName = trainerName;
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
    }

    // Accessor methods

    public double getPremiumCharge() 
    { 
        return this.premiumCharge; 
    }

    public String gettrainerName()
    { 
        return this.trainerName; 
    }

    public boolean getisFullPayment()
    { 
        return this.isFullPayment; 
    }

    public double getPaidAmount() 
    {
        return this.paidAmount; 
    }

    public double getDiscountAmount() 
    { 
        return this.discountAmount; 
    }

    // Overriding the markAttendance method to increase attendance and loyalty points for premium members

    @Override
    public void markAttendance() {
        this.attendance++;
        this.loyaltyPoints += 10;
    }

    // Method to handle the due amount
    public String payDueAmount(double amount) {
        if (this.isFullPayment==true) {
            return "Payment is already completed.";
        }

        if (this.paidAmount > this.premiumCharge) {
            return "More amount paid.";
        }

        this.paidAmount += amount;

        double remainingAmount = premiumCharge - this.paidAmount;

        if(this.paidAmount == premiumCharge){
            this.isFullPayment= true;
        }
        return "Payment successful. Remaining amount: " + remainingAmount + ".";
    }

    // Calculate the discount if full payment is made

    public double calculateDiscount() {
        if (this.isFullPayment== true) {
            this.discountAmount = 0.1 * premiumCharge;
            System.out.println("Discount is applicable.");
        }
        else{
            System.out.println("Discount is not applicable as full payment is not done.");
        }
        return this.discountAmount;
    }

    //reset membership details

    public void revertPremiumMember() {
        super.resetMember();
        this.trainerName = "";
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
    }

    //Show additional details for Premium members

    @Override
    public void display() {
        super.display();//Calls Display method
        System.out.println("Personal Trainer: " + trainerName + ", Paid Amount: " + paidAmount + ", Full Payment: " + isFullPayment);

        double remainingAmount = premiumCharge - this.paidAmount;
        System.out.println("Remaining Amount: " + remainingAmount);
        if (this.isFullPayment == true) {
            System.out.println("Discount Amount: " + discountAmount);
        }
    }

    @Override
    public String toString() {
        return "ID: " + id +
        "\nName: " + name +
        "\nLocation: " + location +
        "\nPhone: " + phone +
        "\nEmail: " + email +
        "\nGender: " + gender +
        "\nDate of Birth: " + dobYear + "-" + dobMonth + "-" + dobDay +
        "\nMembership Start Date: " + msYear + "-" + msMonth + "-" + msDay +
        "\nAttendance: " + attendance +
        "\nActive Status: " + activeStatus +
        "\nPersonal Trainer: " + trainerName +
        "\nPaid Amount: " + paidAmount +
        "\nFull Payment: " + isFullPayment +
        "\nRemaining Amount: " + (premiumCharge - paidAmount) +
        (isFullPayment ? ("\nDiscount Amount: " + discountAmount) : "");
    }

}