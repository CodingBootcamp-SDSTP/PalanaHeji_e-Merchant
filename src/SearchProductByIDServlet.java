import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;

public class SearchProductByIDServlet extends HttpServlet
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
		out.println(id != null || !"".equals(id) ? searchProductByID(new StringBuilder(), id) : "<product></product>");
	}

	public String searchProductByID(StringBuilder sb, String id) {
		Product resultmatch = pc.getProductByID(id);
		if(resultmatch != null) {
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
		else {
			return("<client>null</client>");
		}
	}

	public void destroy() {
		sm = null;
		pc = null;
	}
}
