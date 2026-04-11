package app.service;

import app.model.Notification;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Notification model — US.3 / US.5, Sprint 4.
 * Tests the constructor and all getters without needing a database.
 */
public class NotificationServiceTest {

    private Notification buildSample() {
        return new Notification(
                42,
                "2024 BMW X5",
                "Alice Smith",
                "alice@example.com",
                "Is this still available?",
                "2026-04-11 10:30:00"
        );
    }

    @Test
    public void testGetId_returnsCorrectId() {
        assertEquals(42, buildSample().getId());
    }

    @Test
    public void testGetVehicle_returnsCorrectVehicle() {
        assertEquals("2024 BMW X5", buildSample().getVehicle());
    }

    @Test
    public void testGetCustomerName_returnsCorrectName() {
        assertEquals("Alice Smith", buildSample().getCustomerName());
    }

    @Test
    public void testGetCustomerEmail_returnsCorrectEmail() {
        assertEquals("alice@example.com", buildSample().getCustomerEmail());
    }

    @Test
    public void testGetMessage_returnsCorrectMessage() {
        assertEquals("Is this still available?", buildSample().getMessage());
    }

    @Test
    public void testGetSubmittedAt_returnsCorrectTimestamp() {
        assertEquals("2026-04-11 10:30:00", buildSample().getSubmittedAt());
    }

    @Test
    public void testInsertPattern_nullTimestampAllowed() {
        // Mirrors how NotificationService.createInquiry() builds the object.
        // id=0 and submittedAt=null because MySQL generates both automatically.
        Notification n = new Notification(0, "2023 Toyota Camry",
                "Bob Jones", "bob@example.com", "Price?", null);
        assertEquals(0, n.getId());
        assertNull(n.getSubmittedAt());
    }

    @Test
    public void testOptionalMessage_nullRendersAsDash() {
        // Message is optional on the customer form.
        // The JSP renders "-" when message is null — this test mirrors that logic.
        Notification n = new Notification(5, "2022 Honda Civic",
                "Carol Lee", "carol@example.com", null, "2026-04-10 08:00:00");
        String displayed = n.getMessage() != null ? n.getMessage() : "-";
        assertEquals("-", displayed);
    }
}