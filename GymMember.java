public abstract class GymMember {
    protected int id;
    protected String dobYear;
    protected String dobMonth;
    protected String dobDay;
    protected String name;
    protected String msYear;
    protected String msMonth;
    protected String msDay;
    protected String location;
    protected int attendance;
    protected String phone;
    protected double loyaltyPoints;
    protected String email;
    protected boolean activeStatus;
    protected String gender;

    //Constructor to initialize a gym member
    public GymMember(int id, String name, String location, String phone, String email, String gender, String dobYear, String dobMonth, String dobDay, 
    String msYear, String msMonth, String msDay) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.dobYear = dobYear;
        this.dobMonth = dobMonth;
        this.dobDay = dobDay;
        this.msYear = msYear;
        this.msMonth = msMonth;
        this.msDay = msDay;
        this.attendance = 0;
        this.loyaltyPoints = 0;
        this.activeStatus = false;
    }

    // Accessor methods(getter method)

    public int getId() { 
        return this.id; 
    }

    public String getName() { 
        return this.name; 
    }

    public String getLocation() { 
        return this.location; 
    }

    public String getPhone() { 
        return this.phone; 
    }

    public String getEmail() { 
        return this.email; 
    }

    public String getGender() { 
        return this.gender; 
    }

    public String getdobYear() {
        return this.dobYear; 
    }

    public String getdobMonth() {
        return this.dobMonth; 
    }

    public String getdobDay() {
        return this.dobDay; 
    }

    public String getmsYear() { 
        return this.msYear; 
    }

    public String getmsMonth() { 
        return this.msMonth; 
    }

    public String getmsDay() { 
        return this.msDay; 
    }

    public int getAttendance() {
        return this.attendance; 
    }

    public double getLoyaltyPoints() {
        return this.loyaltyPoints; 
    }

    public boolean isActive() {
        return this.activeStatus; 
    }

    //Abstract method to mark attendance

    public abstract void markAttendance();

    //Activates the membership

    public void activateMembership() {
        this.activeStatus = true;
    }

    /**
    Deactivates the membership
    Prints a message if the membership is already deactivate
     **/

    public void deactivateMembership() {
        if (this.activeStatus == true) {
            this.activeStatus = false;
        }
        else{
            System.out.println("Membership is already deactivated");
        }
    }

    /**
    Resets the membership details and 
    sets attendance and loyalty points to zero 
    and deactivates the membership
     **/

    public void resetMember() {
        this.activeStatus = false;
        this.attendance = 0;
        this.loyaltyPoints = 0;
    }

    public boolean isActiveStatus() {
        return this.activeStatus;
    }

    //Displays the information in a formatted string
    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Location: " + location + ", Phone: " + phone + ", Email: " + email + ", Gender: " 
            + gender + ", DOB: " + dobYear + dobMonth + dobDay +", Membership Start Date: " + msYear + msMonth + msDay +", Attendance: " + attendance + ", Loyalty Points: " 
            + loyaltyPoints + ", Active Status: " + activeStatus);
    }

}
