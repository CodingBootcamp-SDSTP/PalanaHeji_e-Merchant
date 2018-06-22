import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.ArrayList;

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
		Client c = new Client(clientDetails.get(0), clientDetails.get(1),
			clientDetails.get(2), clientDetails.get(3), clientDetails.get(4),
			clientDetails.get(5), clientDetails.get(6), clientDetails.get(7),
			clientDetails.get(8), clientDetails.get(9), clientDetails.get(10),
			clientDetails.get(11), clientDetails.get(12));
		sm.addClient(clientDetails);
		sm.getClientCollection().addClient(c);
		clientDetails = null;
	}

	public void destroy() {
		sm =null;
	}
}
