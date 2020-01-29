import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/Cart")

public class Cart extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();


		/* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/

		Utilities utility = new Utilities(request, pw);
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String maker = request.getParameter("maker");
		String access = request.getParameter("access");
		System.out.print("name" + name + "type" + type + "maker" + maker + "accesee" + access);

		/* StoreProduct Function stores the Purchased product in Orders HashMap.*/	
		
		utility.storeProduct(name, type, maker, access);
		displayCart(request, response);
	}
	

/* displayCart Function shows the products that users has bought, these products will be displayed with Total Amount.*/

	protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
		
		Carousel carousel = new Carousel();
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
		HttpSession session = request.getSession(true);				
		session.setAttribute("cartCount", utility.CartCount());
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Cart</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<form name ='Cart' action='CheckOut' method='post'>");
		if(utility.CartCount()>0)
		{
			pw.print("<table  class='gridtable'>");
			int i = 1;
			double total = 0;
			for (OrderItem oi : utility.getCustomerOrders()) 
			{
				if(i == utility.CartCount()){

				pw.print("<tr>");
				pw.print("<td>1.</td><td>"+oi.getName()+"</td><td>: "+oi.getPrice()+"</td>");
				pw.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
				pw.print("<input type='hidden' name='orderPrice' value='"+oi.getPrice()+"'>");
				pw.print("</tr>");
				

				pw.print("<tr>");
				pw.print("<td>2.</td><td>"+"Runtime"+"</td><td>: "+oi.getRuntime()+"</td>");
				pw.print("</tr>");

				//System.out.println(oi.getProductType());

				pw.print("<tr>");
				pw.print("<td>3.</td><td>"+"Summary"+"</td><td>: "+oi.getSummary()+"</td>");
				pw.print("</tr>");

				
				pw.print("<tr>");
				pw.print("<center><li id='item'><img src='images/" + oi.getProductType()+ "/"+oi.getImage()+"' alt='' /></li></center>");
				pw.print("</tr>");

				// pw.print("<tr>");
				// pw.print("<input type='hidden' name='runtime' value='"+oi.getRuntime()+"'>");
				// pw.print("</tr>");

				total = total +oi.getPrice();
				}
				i++;	
			}

			//pw.print("<tr><th>3.</th><th>Runtime</th><th>"+"2h 55m"+"</th>");



			pw.print("<tr>");
			pw.print("<tr><th>4.</th><th>Tickets</th>");
			pw.print("<td> <input type='number' name='tickets'> </td>");
					

			//pw.print("<tr><th>5.</th><th>Theatres</th><th>"+"ABC"+" "+"XYZ"+"</th>");
			pw.print("<tr><th>5.</th><th>Theatre</th>");
			pw.print("<td><select name='theatre'>");
			pw.print(" <option value='AMC' selected>AMC</option>");
			pw.print(" <option value='Showplace ICON'>Showplace ICON</option>");
			pw.print(" <option value='Cinema Chicago'>Cinema Chicago</option>");

			//show timing
			pw.print("<tr><th>6.</th><th>Showtime</th>");
			pw.print("<td><input type='radio' name='showtime' value='12:30' checked>12:30<br>");
			pw.print("<input type='radio' name='showtime' value='5:40'>5:40<br>");
			pw.print("<input type='radio' name='showtime' value='9:15'>9:15<br>");

			//pw.print("<tr>");
			pw.print("<tr><th>6.</th><th>Row</th>");
			pw.print("<td><select name='row'>");
			pw.print(" <option value='1' selected>1</option>");
			pw.print(" <option value='2'>2</option>");
			pw.print(" <option value='3'>3</option>");



			pw.print("</td></tr>");

			pw.print("<input type='hidden' name='orderTotal' value='"+total+"'>");	
			//pw.print("<tr><th></th><th>Total</th><th>"+total+"</th>");
			 pw.print("<tr><td></td><td></td><td><input type='submit' name='CheckOut' value='CheckOut' class='btnbuy' /></td>");
			
			pw.print("</table></form>");


			/* This code is calling Carousel.java code to implement carousel feature*/
			pw.print(carousel.carouselfeature(utility));
		}
		else
		{
			pw.print("<h4 style='color:red'>Your Cart is empty</h4>");
		}
		pw.print("</div></div></div>");		
		utility.printHtml("Footer.html");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		displayCart(request, response);
	}
}
