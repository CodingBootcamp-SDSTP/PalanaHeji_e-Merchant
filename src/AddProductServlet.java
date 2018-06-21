import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.ArrayList;
import java.math.BigDecimal;

public class AddProductServlet extends HttpServlet
{
	StoreManager sm;

	public void init() throws ServletException {
		sm = StoreManager.createInstance();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/plain");
		System.out.println("AddProductServlet IS CONNECTED TO TOMCAT");
		ArrayList<String> prodDetails = new ArrayList<String>();
		prodDetails.add(request.getParameter("id"));
		prodDetails.add(request.getParameter("name"));
		prodDetails.add(request.getParameter("brand"));
		prodDetails.add(request.getParameter("unitPrice"));
		prodDetails.add(request.getParameter("discount"));
		prodDetails.add(request.getParameter("vat"));
		prodDetails.add(request.getParameter("numberOfSales"));
		BigDecimal unitPrice = new BigDecimal(request.getParameter("unitPrice"));
		BigDecimal vat = new BigDecimal(request.getParameter("vat"));
		BigDecimal hundred = new BigDecimal("100");
		BigDecimal one = new BigDecimal("1");
		hundred = vat.divide(hundred);
		one =  hundred.add(one);
		unitPrice = unitPrice.multiply(one);
		prodDetails.add(unitPrice.toString());
		System.out.println(prodDetails);
		sm.addProduct(prodDetails);
		prodDetails = null;
	}

	public void destroy() {
		sm =null;
	}
}