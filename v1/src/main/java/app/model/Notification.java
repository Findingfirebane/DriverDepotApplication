package app.model;
//This is the model class for a notification (i.e. a customer inquiry)
//One Notification object = one row in the notifications table
public class Notification {
	// Each field maps directly to a column in the notifications table
    private int id;
    private String vehicle;
    private String customerName;
    private String customerEmail;
    private String message;
    private String submittedAt;
    
    
    // Constructor: build a Notification object from a database row
    // We pass in all 6 fields at once when reading from the ResultSet in the repository
    // id=0 and submittedAt=null are used when inserting - MySQL generates those automatically
    public Notification(int id, String vehicle, String customerName, 
                        String customerEmail, String message, String submittedAt) {
        this.id = id;
        this.vehicle = vehicle;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.message = message;
        this.submittedAt = submittedAt;
    }
    
    // Getters - used by the JSP and service layer to read the data
    // No setters because once a notification is created from the database
    // we only need to read or delete it, never edit individual fields
    public int getId() { return id; }
    public String getVehicle() { return vehicle; }
    public String getCustomerName() { return customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public String getMessage() { return message; }
    public String getSubmittedAt() { return submittedAt; }
}