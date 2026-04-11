package app.controller;

import app.service.NotificationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/*
 * This is the controller that handles the customer inquiry form
 * It receives the POST from the form, calls the service and saves the inquiry to the db
 * @WebServlet("/submitInquiry")This annotation registers the servlet at the URL /submitInquiry
 * When the browser goes to localhost:8080/v1/submitInquiry, this handles it rather than using the web.xml file.
 * This more of a precaution against commits that could cause issue during the development phase
 */
@WebServlet("/submitInquiry")
public class SubmitInquiryServlet extends HttpServlet {

	/*
	 * connection to the notification service as highlighted before
	 */
	private static final long serialVersionUID = 1L;
	private NotificationService service = new NotificationService();

	/*
	 * doGet is getting the empty form from the jsp doPost send the data which then
	 * makes the inquiry, or returns an error if not.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("vehicle", "2024 BMW X5");
		req.getRequestDispatcher("/WEB-INF/view/submitInquiry.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {

	    String vehicle      = req.getParameter("vehicle");
	    String customerName  = req.getParameter("customerName");
	    String customerEmail = req.getParameter("customerEmail");
	    String message       = req.getParameter("message");

	    /*
	     * Sprint 4 addition: server-side input validation.
	     * The JSP form has 'required' on these fields which stops blank
	     * submissions in the browser, but 'required' is just HTML — anyone
	     * can send a raw HTTP POST directly to this URL and bypass it.
	     * This guard ensures the database is never called with empty data
	     * regardless of how the request arrives.
	     */
	    if (customerName == null || customerName.trim().isEmpty()
	            || customerEmail == null || customerEmail.trim().isEmpty()) {
	        req.setAttribute("error", "Name and email are required.");
	        req.setAttribute("vehicle", vehicle);
	        req.getRequestDispatcher("/WEB-INF/view/submitInquiry.jsp").forward(req, resp);
	        return;
	    }

	    try {
	        /*
	         * Sprint 4 addition: .trim() strips accidental leading/trailing
	         * whitespace from name and email before they are saved to the database.
	         * Message is intentionally not trimmed — whitespace in a longer
	         * message is deliberate.
	         */
	        service.createInquiry(vehicle, customerName.trim(), customerEmail.trim(), message);
	        resp.sendRedirect(req.getContextPath() + "/submitInquiry?success=true");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        req.setAttribute("error", "Something went wrong. Please try again.");
	        req.setAttribute("vehicle", vehicle);
	        req.getRequestDispatcher("/WEB-INF/view/submitInquiry.jsp").forward(req, resp);
	    }
	}
}