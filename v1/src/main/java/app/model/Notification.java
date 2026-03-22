package app.model;

public class Notification {

    private int id;
    private String vehicle;
    private String customerName;
    private String customerEmail;
    private String message;
    private String submittedAt;

    public Notification(int id, String vehicle, String customerName, 
                        String customerEmail, String message, String submittedAt) {
        this.id = id;
        this.vehicle = vehicle;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.message = message;
        this.submittedAt = submittedAt;
    }

    public int getId() { return id; }
    public String getVehicle() { return vehicle; }
    public String getCustomerName() { return customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public String getMessage() { return message; }
    public String getSubmittedAt() { return submittedAt; }
}