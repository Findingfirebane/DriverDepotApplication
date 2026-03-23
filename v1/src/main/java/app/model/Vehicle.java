package app.model;
// model, year, mileage, msrp, stock, details
public class Vehicle {
    private int id;
    private String make;
    private String model;
    private int year;
    private int mileage;
    private int msrp;
    private int stock;
    private String details;


    public Vehicle (){}
    
    public Vehicle(int id, String make, String model, int year, int mileage, int msrp, int stock, String details) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.msrp = msrp;
        this.stock = stock;
        this.details = details;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getMileage() {
        return mileage;
    }
    public void setMileage(int milage) {
        this.mileage = milage;
    }
    public int getMsrp() {
        return msrp;
    }
    public void setMsrp(int msrp) {
        this.msrp = msrp;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }

    
    

}
