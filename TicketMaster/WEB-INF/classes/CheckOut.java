import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

@WebServlet("/CheckOut")

//once the user clicks buy now button page is redirected to checkout page where user has to give checkout information

public class CheckOut extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	        Utilities Utility = new Utilities(request, pw);
		storeOrders(request, response);
	}
	
	protected void storeOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try
        {
        response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request,pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
        HttpSession session=request.getSession(); 

		//get the order product details	on clicking submit the form will be passed to submitorder page	
		
	    String userName = session.getAttribute("username").toString();
		String orderTotal = request.getParameter("orderTotal");
		String num_tickets = request.getParameter("tickets");
		String theatre = request.getParameter("theatre").toString();

		String row = request.getParameter("row");
		String cartCount = session.getAttribute("cartCount").toString();
		Integer count = Integer.parseInt(cartCount);
		//System.out.println(count);
		
		session.setAttribute("num_tickets",num_tickets);
		session.setAttribute("row",row);
		session.setAttribute("theatre", theatre);

		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<form name ='CheckOut' action='Payment' method='post'>");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table  class='gridtable'><tr><td>Customer Name:</td><td>");
		pw.print(userName);
		pw.print("</td></tr>");
		// for each order iterate and display the order name price
		int i = 1;
		for (OrderItem oi : utility.getCustomerOrders()) 
		{
			if (i == count){
			pw.print("<tr><td> Product Purchased</td><td>");
			pw.print(oi.getName()+"</td></tr><tr><td>");
			pw.print("<input type='hidden' name='orderPrice' value='"+oi.getPrice()+"'>");
			pw.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
			pw.print("Product Price</td><td>"+ oi.getPrice());
			pw.print("</td></tr>");
			pw.print("<tr><td>Number of tickets</td><td>" + num_tickets + "</td></tr>");
			pw.print("<tr><td>Theatre</td><td>" + theatre + "</td></tr>");
			pw.print("<tr><td>Row number</td><td>" + row + "</td></tr>");
			
			session.setAttribute("movie_name",oi.getName());
			}
			i++;
		}
		System.out.println("A");


		Double num1 = Double.parseDouble(num_tickets);
		System.out.println(theatre);

		Double num2 = Double.parseDouble(orderTotal);
	System.out.println("B");

		Double tot = num1 * num2;
		String tot1 = Double.toString(tot);

		System.out.println("BB");

		
		pw.print("<tr><td>");
        pw.print("Total Order Cost</td><td>"+tot1);
		pw.print("<input type='hidden' name='orderTotal' value='"+orderTotal+"'>");
		pw.print("</td></tr></table>");
		pw.print("<table><tr></tr><tr></tr>");	
		pw.print("<tr><td>");
		pw.print("</br>");
     	pw.print("Credit/accountNo</td>");
		pw.print("<td><input type='text' name='creditCardNo'>");
		pw.print("</td></tr>");
		pw.print("<tr><td>");
     	pw.print("Date</td>");
		pw.print("<td><input type='date' name='date_place'>");
		pw.print("</td></tr>");		
		pw.print("<tr><td>");
	    pw.print("Customer Address</td>");
		pw.print("<td><input type='text' name='userAddress'>");
        pw.print("</td></tr>");
		pw.print("<tr><td colspan='2'>");
		pw.print("<input type='submit' name='submit' class='btnbuy'>");
        pw.print("</td></tr></table></form>");
		pw.print("</div></div></div>");		
		utility.printHtml("Footer.html");
	    }
        catch(Exception e)
		{
         System.out.println(e.getMessage());
		}  			
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	    {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	    }
}
