import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchProductByPriceRangeServlet extends HttpServlet
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
		String floorPrice = request.getParameter("pricef");
		String ceilingPrice = request.getParameter("pricet");
		PrintWriter out = response.getWriter();
		System.out.println("Connected to servlet");
		StringBuilder sb = new StringBuilder();
		if(floorPrice != null || ceilingPrice != null) {
			out.println(searchByPriceRange(sb, floorPrice, ceilingPrice));
		}
	}

	public String searchByPriceRange(StringBuilder sb, String fp, String cp) {
		double a =  Double.valueOf(fp);
		double b = Double.valueOf(cp);
		ArrayList<Product> searchMatches = pc.searchByPriceRange(a, b);
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
