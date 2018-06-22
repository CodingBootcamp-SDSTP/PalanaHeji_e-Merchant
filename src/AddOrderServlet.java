import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.ArrayList;

public class AddOrderServlet extends HttpServlet
{
	StoreManager sm;

	public void init() throws ServletException {
		sm = StoreManager.createInstance();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/plain");
		System.out.println("AddOrderServlet IS CONNECTED TO TOMCAT");
		ArrayList<String> orders = new ArrayList<String>();
		orders.add(request.getParameter("clientid"));
		orders.add(request.getParameter("productid"));
		orders.add(request.getParameter("cartid"));
		orders.add(request.getParameter("quantity"));
		PrintWriter out = response.getWriter();
		System.out.println(orders);
		out.println(orders);
		sm.addOrder(orders);
		orders = null;
	}

	public void destroy() {
		sm =null;
	}
}
