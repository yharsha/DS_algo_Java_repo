import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ExampleGeneric extends GenericServlet {

	public void service(ServletRequest req,ServletResponse res)
			throws IOException,ServletException{
		   res.setContentType("text/html");
		   PrintWriter pwriter=res.getWriter();
		   pwriter.print("<html>");
		   pwriter.print("<body>");
		   pwriter.print("<h2>Generic Servlet Example</h2>");
		   pwriter.print("<p>Hello Readers!</p>");
		   pwriter.print(this.getServletName()+  " info1");
		   pwriter.print("</body>");
		   pwriter.print("</html>");
		 }
}
