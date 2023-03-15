package customServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class CustomServlet extends HttpServlet{
	private static final long serialVersionUID = -5628202942290866675L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("PATCH")){
           doPatch(request, response);
        } else {
            super.service(request, response);
        }
    }
     
    protected abstract void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
 
}