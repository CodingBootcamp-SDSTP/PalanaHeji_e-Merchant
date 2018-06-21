import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.ArrayList;
import java.util.Arrays;

public class AllClientServlet extends HttpServlet
{
	StoreManager sm;
	ClientCollection cc;

	public void init() throws ServletException {
		sm = StoreManager.createInstance();
		cc = sm.getClientCollection();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		System.out.println("Connected to servlet");
		StringBuilder sb = new StringBuilder();
		out.println(exportXML(sb));
	}

	public String exportXML(StringBuilder sb) {
		sb.append("<clients>");
		for(Person c : cc.getArrayListOfClients()) {
			String[] details = c.getDetails().split("~");
			System.out.println(Arrays.toString(details));
			sb.append("<client><clientid>");
			sb.append(details[3]+ "</clientid><firstName>");
			sb.append(details[0] + "</firstName><lastName>");
			sb.append(details[1] + "</lastName><birthDate>");
			sb.append(details[2] + "</birthDate><email>");
			sb.append(details[4] + "</email><contact>");
			sb.append(details[5] + "</contact><companyName>");
			sb.append(details[6] + "</companyName><city>");
			sb.append(details[8] + "</city><state>");
			sb.append(details[9] + "</state><zipcode>");
			sb.append(details[10] + "</zipcode><country>");
			sb.append(details[11] + "</country><address>");
			sb.append(details[12] + "</address></client>");
		}
		sb.append("</clients>");
		return(sb.toString());
	}

	public void destroy() {
		sm = null;
		cc = null;
	}
}
