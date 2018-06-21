import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchClientByIDServlet extends HttpServlet
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
		String id = request.getParameter("client_id");
		PrintWriter out = response.getWriter();
		System.out.println("Connected to servlet");
		System.out.println(id);
		out.println(id != null || !"".equals(id) ? searchClientByID(new StringBuilder(), id) : "<client></client>");
	}

	public String searchClientByID(StringBuilder sb, String id) {
		Person resultmatch = cc.getClientByID(id);
		Client c = (Client)resultmatch;
		System.out.println(c);
		if(c != null) {
			String[] details = c.getDetails().split("~");
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
			return(sb.toString());
		}
		else {
			return("<client>null</client>");
		}
	}

	public void destroy() {
		sm = null;
		cc = null;
	}
}
