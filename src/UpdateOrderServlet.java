import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.ArrayList;

public class UpdateOrderServlet extends HttpServlet
{
	StoreManager sm;
	ArrayList<String> orders;

	public void init() throws ServletException {
		sm = StoreManager.createInstance();
		orders = new ArrayList<String>();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/plain");
		System.out.println("UpdateOrderServlet IS CONNECTED TO TOMCAT");
		orders.add(request.getParameter("productid"));
		orders.add(request.getParameter("quantity"));
		orders.add(request.getParameter("id"));
		PrintWriter out = response.getWriter();
		System.out.println(orders);
		sm.updateOrder(orders);
	}

	public void destroy() {
		sm =null;
		orders = null;
	}
}
