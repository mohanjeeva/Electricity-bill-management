package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zoomRx.eb.db.util;


public class dis extends HttpServlet {
	private static final long serialVersionUID = 1L
    public dis() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		String op=request.getParameter("operation");
		if(op.equals("Register"))
		{
		int service=Integer.parseInt(request.getParameter("id"));
		Connection conn=util.getDBConnection();
		try
		{
			PreparedStatement ps=conn.prepareStatement("select * from eb where id=?");
			ps.setInt(1,service);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				out.println("Current Reading:"+rs.getInt(2)+"<br><br>Previous Reading:"+rs.getInt(3)+"<br><br>Amount Paid:"+rs.getInt(4));
			}
		}
		catch(SQLException e)
		{
			e.toString();
		}
		}
		
	}
}
	


