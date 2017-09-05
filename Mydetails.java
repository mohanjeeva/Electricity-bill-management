package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zoomRx.eb.service.ConnectionService;

public class Mydetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Mydetails() {
        super()
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String op=request.getParameter("operation");
		if(op.equals("Register"))
		{
		int service=Integer.parseInt(request.getParameter("id"));
		int current=Integer.parseInt(request.getParameter("current"));
		int previous=Integer.parseInt(request.getParameter("previous"));
		String tmp=request.getParameter("typ");
		ConnectionService obj=new ConnectionService();
		String result=obj.generateBill(service,current, previous, tmp);
		String center="center";
		out.print("<!DOCTYPE html><html><head><title>Electricity Bill Management</title></head><body>");
		out.print("<h1 align="+center+">"+result+"<br><br><br><br>Thank You Customer ;)</h1>");
		out.print("</body></html>");
				
		}

	}

}
