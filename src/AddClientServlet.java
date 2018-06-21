import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.ArrayList;
import java.math.BigDecimal;

public class AddClientServlet extends HttpServlet
{
	StoreManager sm;

	public void init() throws ServletException {
		sm = StoreManager.createInstance();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/plain");
		System.out.println("AddClientServlet IS CONNECTED TO TOMCAT");
		ArrayList<String> clientDetails = new ArrayList<String>();
		clientDetails.add(request.getParameter("id"));
		clientDetails.add(request.getParameter("firstname"));
		clientDetails.add(request.getParameter("lastname"));
		clientDetails.add(request.getParameter("birthdate"));
		clientDetails.add(request.getParameter("email"));
		clientDetails.add(request.getParameter("password"));
		clientDetails.add(request.getParameter("companyname"));
		clientDetails.add(request.getParameter("city"));
		clientDetails.add(request.getParameter("state"));
		clientDetails.add(request.getParameter("zipcode"));
		clientDetails.add(request.getParameter("country"));
		clientDetails.add(request.getParameter("address"));
		clientDetails.add(request.getParameter("contact"));
		PrintWriter out = response.getWriter();
		System.out.println(clientDetails);
		out.println(clientDetails);
		sm.addClient(clientDetails);
		clientDetails = null;
	}

	public void destroy() {
		sm =null;
	}
}
