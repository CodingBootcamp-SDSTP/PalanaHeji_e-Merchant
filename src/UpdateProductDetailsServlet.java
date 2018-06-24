import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.ArrayList;

public class UpdateProductDetailsServlet extends HttpServlet
{
	StoreManager sm;
	ArrayList<String> details;

	public void init() throws ServletException {
		sm = StoreManager.createInstance();
		details = new ArrayList<String>();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/plain");
		System.out.println("UpdateProductDetailsServlet IS CONNECTED TO TOMCAT");
		details.add(request.getParameter("productid"));
		details.add(request.getParameter("productname"));
		details.add(request.getParameter("brand"));
		details.add(request.getParameter("numberOfSales"));
		details.add(request.getParameter("unitPrice"));
		details.add(request.getParameter("discount"));
		details.add(request.getParameter("vat"));
		details.add(request.getParameter("vatInclusivePrice"));
		details.add(request.getParameter("id"));
		System.out.println(details);
		sm.updateProductDetails(details);
	}

	public void destroy() {
		sm =null;
		details = null;
	}
}
