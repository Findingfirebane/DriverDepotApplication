package app.dto;

import java.io.Serializable; 
public class VehicleDTO implements Serializable{

    private int id;
    private String make;
    private String model;
    private int year;
    private int milage;
    private int msrp;
    private int stock;
    private String details;

    
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
    public int getMilage() {
        return milage;
    }
    public void setMilage(int milage) {
        this.milage = milage;
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
