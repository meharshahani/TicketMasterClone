import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RomanticList")

public class RomanticList extends HttpServlet 
{

	/* TV Page Displays all the tvs and their Information in Best Deal */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
        

	
		/* Checks the Tablets type whether it is microsft or sony or nintendo */

		HashMap<String, Romantic> hm = new HashMap<String, Romantic>();
		if(CategoryName==null)
		{
			hm.putAll(SaxParserDataStore.roms);
			name = "";
		}
		else
		{
		   if(CategoryName.equals("love"))
		   {
			 for(Map.Entry<String,Romantic> entry : SaxParserDataStore.roms.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("love"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "P.S. I love you";
		   }
		   else if(CategoryName.equals("safe"))
		    {
			for(Map.Entry<String,Romantic> entry : SaxParserDataStore.roms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("safe"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "Safe haven";
			}
			else if(CategoryName.equals("star"))
			{
				for(Map.Entry<String,Romantic> entry : SaxParserDataStore.roms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("star"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Star is born";
			}
			else if(CategoryName.equals("angry"))
			{
				for(Map.Entry<String,Romantic> entry : SaxParserDataStore.roms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("angry"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "12 Angry Men";
			}
			else if(CategoryName.equals("minutes"))
			{
				for(Map.Entry<String,Romantic> entry : SaxParserDataStore.roms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("minutes"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "30 Minutes or Less";
			}
			else if(CategoryName.equals("time"))
			{
				for(Map.Entry<String,Romantic> entry : SaxParserDataStore.roms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("time"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "About Time";
			}
			else if(CategoryName.equals("cast"))
			{
				for(Map.Entry<String,Romantic> entry : SaxParserDataStore.roms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("cast"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Cast Away";
			}
			else if(CategoryName.equals("asians"))
			{
				for(Map.Entry<String,Romantic> entry : SaxParserDataStore.roms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("asians"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Crazy Rich Asians";
			}
			else if(CategoryName.equals("john"))
			{
				for(Map.Entry<String,Romantic> entry : SaxParserDataStore.roms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("john"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Dear John";
			}

			else if(CategoryName.equals("gump"))
			{
				for(Map.Entry<String,Romantic> entry : SaxParserDataStore.roms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("gump"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Forrest Gump";
			}
			else if(CategoryName.equals("lala"))
			{
				for(Map.Entry<String,Romantic> entry : SaxParserDataStore.roms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("lala"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "La La Land";
			}
			else if(CategoryName.equals("oneday"))
			{
				for(Map.Entry<String,Romantic> entry : SaxParserDataStore.roms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("oneday"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "One Day";
			}
			else if(CategoryName.equals("sick"))
			{
				for(Map.Entry<String,Romantic> entry : SaxParserDataStore.roms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("sick"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "The Big Sick";
			}
			else if(CategoryName.equals("blue"))
			{
				for(Map.Entry<String,Romantic> entry : SaxParserDataStore.roms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("blue"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "The Blue Valentine";
			}
			else if(CategoryName.equals("fault"))
			{
				for(Map.Entry<String,Romantic> entry : SaxParserDataStore.roms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("fault"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "The Fault In Our Stars";
			}
			else if(CategoryName.equals("god"))
			{
				for(Map.Entry<String,Romantic> entry : SaxParserDataStore.roms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("god"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "The Godfather";
			}
			else if(CategoryName.equals("matrix"))
			{
				for(Map.Entry<String,Romantic> entry : SaxParserDataStore.roms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("matrix"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "The Matrix";
			}
			else if(CategoryName.equals("shawshank"))
			{
				for(Map.Entry<String,Romantic> entry : SaxParserDataStore.roms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("shawshank"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "The Shawshank Redemption";
			}
			
		}
	


		
		/* Header, Left Navigation Bar are Printed.

		All the TV and tvs information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" </a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Romantic> entry : hm.entrySet())
		{
			Romantic rom = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+rom.getName()+"</h3>");
			pw.print("<strong>$"+rom.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/roms/"+rom.getImage()+"' alt='' /></li>");
			
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='roms'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Book Tickets'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='roms'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+rom.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='roms'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}	
		pw.print("</table></div></div></div>");
   
		utility.printHtml("Footer.html");
		
	}	
		
}
