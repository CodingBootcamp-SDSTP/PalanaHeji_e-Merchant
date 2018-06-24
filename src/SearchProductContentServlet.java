import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchProductContentServlet extends HttpServlet
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
		System.out.println("Connected to servlet");
		String text = request.getParameter("text");
		PrintWriter out = response.getWriter();
		StringBuilder sb = new StringBuilder();
		out.println(text == null || "".equals(text) ? "<products></products>" : searchInProducts(sb, text));
	}

	public String searchInProducts(StringBuilder sb, String q) {
		ArrayList<Product> searchMatches = pc.searchProduct(q);
		sb.append("<products>");
		for(Product p : searchMatches) {
			String[] details = p.getDetails().split("~");
			System.out.println(Arrays.toString(details));
			sb.append("<product><productid>");
			sb.append(details[0]+ "</productid><brand>");
			sb.append(details[1] + "</brand><name>");
			sb.append(details[2] + "</name><unitPrice>");
			sb.append(details[3] + "</unitPrice><discount>");
			sb.append(details[4] + "</discount><numberOfSales>");
			sb.append(details[5] + "</numberOfSales><vatInclusivePrice>");
			sb.append(details[6] + "</vatInclusivePrice></product>");
		}
		sb.append("</products>");
		return(sb.toString());
	}

	public void destroy() {
		sm = null;
		pc = null;
	}
}
