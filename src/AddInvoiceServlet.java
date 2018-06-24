import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.ArrayList;

public class AddInvoiceServlet extends HttpServlet
{
	StoreManager sm;
	ArrayList<String> invoiceDetails;

	public void init() throws ServletException {
		sm = StoreManager.createInstance();
		invoiceDetails = new ArrayList<String>();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/plain");
		System.out.println("AddInvoiceServlet IS CONNECTED TO TOMCAT");
		invoiceDetails.add(request.getParameter("invoiceid"));
		invoiceDetails.add(request.getParameter("cartid"));
		invoiceDetails.add(request.getParameter("transDate"));
		invoiceDetails.add(request.getParameter("dateOfPayment"));
		invoiceDetails.add(request.getParameter("destAddress"));
		invoiceDetails.add(request.getParameter("shippingCost"));
		invoiceDetails.add(request.getParameter("totalBill"));
		System.out.println(invoiceDetails);
		sm.addInvoice(invoiceDetails);
		invoiceDetails = null;
	}

	public void destroy() {
		sm =null;
	}
}
