import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchProductByID extends HttpServlet
{
	StoreManager sm;
	ProductCollection pc;

	public void init() throws ServletException {
		sm = StoreManager.createInstance();
		pc = sm.getProductCollection();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/xml");
		String id = request.getParameter("product_id");
		PrintWriter out = response.getWriter();
		System.out.println("Connected to servlet");
		System.out.println(id);
		StringBuilder sb = new StringBuilder();
		out.println(id != null || !"".equals(id) ? searchProductByID(sb, id) : "<product></product>");
	}

	public String searchProductByID(StringBuilder sb, String id) {
		Product resultmatch = pc.getProductByID(id);
		sb.append("<product><productid>");
		sb.append(resultmatch.getID() + "</productid><brand>");
		sb.append(resultmatch.getBrand() + "</brand><name>");
		sb.append(resultmatch.getName() + "</name><unitPrice>");
		sb.append(resultmatch.getUnitPrice() + "</unitPrice><discount>");
		sb.append(resultmatch.getDiscount() + "</discount><numberOfSales>");
		sb.append(resultmatch.getNumberOfSales() + "</numberOfSales><vatInclusivePrice>");
		sb.append(resultmatch.getVatInclusivePrice() + "</vatInclusivePrice></product>");
		return(sb.toString());
	}

	public void destroy() {
		sm = null;
		pc = null;
	}
}
