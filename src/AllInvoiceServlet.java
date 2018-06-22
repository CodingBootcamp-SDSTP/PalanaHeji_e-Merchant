import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;

public class AllInvoiceServlet extends HttpServlet
{
	StoreManager sm;
	InvoiceCollection ic;

	public void init() throws ServletException {
		sm = StoreManager.createInstance();
		ic = sm.getInvoiceCollection();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		System.out.println("Connected to AllInvoiceServlet");
		StringBuilder sb = new StringBuilder();
		out.println(exportXML(sb));
	}

	public String exportXML(StringBuilder sb) {
		sb.append("<invoices>");
		for(Invoice i : ic.getArrayListOfInvoice()) {
			String[] details = i.getDetails().split("~");
			sb.append("<invoice><invoiceid>");
			sb.append(details[0]+ "</invoiceid><customerID>");
			sb.append(details[1] + "</customerID><cartID>");
			sb.append(details[2] + "</cartID><transcationDate>");
			sb.append(details[3] + "</transcationDate><dateOfPayment>");
			sb.append(details[4] + "</dateOfPayment><cartDetails>");
			sb.append(details[5] + "</cartDetails><destinationAddress>");
			sb.append(details[6] + "</destinationAddress><shippingCost>");
			sb.append(details[7] + "</shippingCost><totalBill>");
			sb.append(details[8] + "</totalBill><status>");
			sb.append(details[9] + "</status></invoice>");
		}
		sb.append("</invoices>");
		return(sb.toString());
	}

	public void destroy() {
		sm = null;
		ic = null;
	}
}
