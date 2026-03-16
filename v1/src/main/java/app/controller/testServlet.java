// package app.controller;

// public class testServlet {
    
// }

package app.controller;

import app.service.testService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
// delete this class later 
@WebServlet("/test")
public class testServlet extends HttpServlet {

    private testService service = new testService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String message = service.getMessage();

        resp.setContentType("text/plain");

        PrintWriter out = resp.getWriter();

        out.println(message);
    }
}