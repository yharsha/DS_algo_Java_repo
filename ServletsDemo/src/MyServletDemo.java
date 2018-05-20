import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServletDemo extends HttpServlet {

	private String mymsg;

	public void init() throws ServletException {
		mymsg = "Hello World by Servlets";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		 // Writing message to the web page
		PrintWriter out = response.getWriter();
		out.println("<h1>" + mymsg + "</h1>");
		out.print(this.getServletInfo()+ " info");
	}
	
	  public void destroy() {
	      /* leaving empty for now this can be
	       * used when we want to do something at the end
	       * of Servlet life cycle
	       */
	   }
}
