package employee;

public class CommissionEmployee extends Object {
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
    private double grossSales;
    private double commissionRate;

    // constructor
    public CommissionEmployee(String first, String last, String ssn,
                              double sales, double rate){
        // implicit call to Object constructor occurs here
        setFirstName(first);
        setLastName(last);
        setSocialSecurityNumber(ssn);
        setGrossSales(sales);
        setCommissionRate(rate);
    }
    // SETTERS
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setSocialSecurityNumber(String socialSecurityNumber){
        this.socialSecurityNumber = socialSecurityNumber;
    }
    public void setGrossSales(double grossSales){
        if(grossSales >= 0.0f)
            this.grossSales = grossSales;
        else
            throw new IllegalArgumentException(
                    "Gross sales must be >= 0.0f");
    }
    public void setCommissionRate(double commissionRate){
        if(commissionRate > 0.0f && commissionRate < 1.0f)
            this.commissionRate = commissionRate;
        else
            throw new IllegalArgumentException(
                    "Commission rate must be > 0.0f and < 1.0f");
    }
    // GETTERS
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getSocialSecurityNumber(){
        return this.socialSecurityNumber;
    }
    public double getGrossSales(){
        return this.grossSales;
    }
    public double getCommissionRate(){
        return this.commissionRate;
    }
    // calculate earnings
    public double earnings(){
        return this.commissionRate * this.grossSales;
    }
    // return string representation of CommissionEmployee object
    @Override // indicates this method overrides a superclass method
    public String toString(){
        return String.format("%s: %s %s\n%s: %s\n%s: %.2f\n%s: %.2f\n%s: %.2f",
                "employee", this.firstName, this.lastName,
                "social security number", this.socialSecurityNumber,
                "gross sales", this.grossSales,
                "commission rate", this.commissionRate,
                "earnings", this.earnings());
    }

    @Override
    public boolean equals(Object ce){
        return this.socialSecurityNumber == ((CommissionEmployee)ce).socialSecurityNumber;
    }

}
package employee;

public class BasePlusCommissionEmployee extends CommissionEmployee {
    private double basePayment;
    public double getBasePayment() {
        return basePayment;
    }

    public BasePlusCommissionEmployee(String first, String last, String ssn,
                                      double sales, double rate, double basePayment){
        super(first, last, ssn, sales, rate); //creating the parent
        this.basePayment=basePayment;
    }

    //
    @Override
    public double earnings(){
        return this.basePayment + super.earnings();
    }
    //
    @Override // indicates this method overrides a superclass method
    public String toString(){
        return String.format("%s\n%s\n%s: %.2f",
                "commission+base employee",
                super.toString(),
                "base payment", this.getBasePayment());
    }
}



3.
        ================================================================================================
        HourlyEmployee.java
public class HourlyEmployee extends employee{

    private double hours;
    private double wages;

    HourlyEmployee(String firstname, String lastname, int ssn, double hours, double wages){
        super();
        super.setFirstname(firstname);
        super.setLastname(lastname);
        super.setSsn(ssn);
        this.hours=hours;
        this.wages=wages;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        if(hours>=0 || hours <= 168) {
            this.hours = hours;
        }
    }

    public double getWages() {
        return wages;
    }

    public void setWages(double wages) {
        if(wages>=0) {
            this.wages = wages;
        }
    }

    public double totalEarning(){
        if(this.getHours()<=40){
            return (this.getHours() * this.getWages());
        }else{
            double hour = this.getHours() - 40;
            double total = (40 * this.getWages()) + hour * (wages * 1.5);
            return total;
        }
    }

    @Override
    public String toString() {
        return "Firstname: "+ super.getFirstname() + " Lastname: " + super.getLastname() + " Hours Worked: "+ this.getHours() + " Wages per hour: " + this.getWages() + " Total wages: " + this.totalEarning();
    }
}
main.java
        import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String firtsname,lastname;
        int ssn;
        double hours;
        double wages;

        System.out.println("Enter FirstName: ");
        firtsname = sc.next();
        System.out.println("Enter LastName: ");
        lastname = sc.next();
        System.out.println("Enter SSN: ");
        ssn = sc.nextInt();
        System.out.println("Enter Hours: ");
        hours = sc.nextDouble();
        System.out.println("Enter Wages: ");
        wages = sc.nextDouble();

        HourlyEmployee he = new HourlyEmployee(firtsname,lastname,ssn,hours,wages);

        System.out.println(he.toString());
    }
}