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

@WebServlet("/ComedyList")

public class ComedyList extends HttpServlet 
{

	/* TV Page Displays all the tvs and their Information in Best Deal */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
        

	
		/* Checks the Tablets type whether it is microsft or sony or nintendo */

		HashMap<String, Comedy> hm = new HashMap<String, Comedy>();
		if(CategoryName==null)
		{
			hm.putAll(SaxParserDataStore.coms);
			name = "";
		}
		else
		{
		   if(CategoryName.equals("millers"))
		   {
			 for(Map.Entry<String,Comedy> entry : SaxParserDataStore.coms.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("millers"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Were the Millers";
		   }
		   else if(CategoryName.equals("bridesmaids"))
		    {
			for(Map.Entry<String,Comedy> entry : SaxParserDataStore.coms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("bridesmaids"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "Bridesmaids";
			}
			else if(CategoryName.equals("rebound"))
			{
				for(Map.Entry<String,Comedy> entry : SaxParserDataStore.coms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("rebound"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "The Rebound";
			}
			else if(CategoryName.equals("hangover"))
			{
				for(Map.Entry<String,Comedy> entry : SaxParserDataStore.coms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("hangover"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Hangover";
			}
			else if(CategoryName.equals("dates"))
			{
				for(Map.Entry<String,Comedy> entry : SaxParserDataStore.coms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("dates"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "50 First Dates";
			}
			else if(CategoryName.equals("airplane"))
			{
				for(Map.Entry<String,Comedy> entry : SaxParserDataStore.coms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("airplane"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Airplane";
			}
			else if(CategoryName.equals("dumb"))
			{
				for(Map.Entry<String,Comedy> entry : SaxParserDataStore.coms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("dumb"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Dumb and Dumber";
			}
			else if(CategoryName.equals("sunday"))
			{
				for(Map.Entry<String,Comedy> entry : SaxParserDataStore.coms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("sunday"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "First Sunday";
			}
			else if(CategoryName.equals("good"))
			{
				for(Map.Entry<String,Comedy> entry : SaxParserDataStore.coms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("good"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Good Boys";
			}
			// else if(CategoryName.equals("hotfuzz"))
			// {
			// 	for(Map.Entry<String,Comedy> entry : SaxParserDataStore.coms.entrySet())
			// 	{
			// 	 if(entry.getValue().getRetailer().equals("hotfuzz"))
			// 	 {
			// 		 hm.put(entry.getValue().getId(),entry.getValue());
			// 	 }
			// 	}
			//    	 name = "Hotfuzz";
			// }
			else if(CategoryName.equals("hustle"))
			{
				for(Map.Entry<String,Comedy> entry : SaxParserDataStore.coms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("hustle"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Hustle";
			}
			else if(CategoryName.equals("longshot"))
			{
				for(Map.Entry<String,Comedy> entry : SaxParserDataStore.coms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("longshot"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Longshot";
			}
			else if(CategoryName.equals("mib"))
			{
				for(Map.Entry<String,Comedy> entry : SaxParserDataStore.coms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("mib"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Men in Black";
			}
			else if(CategoryName.equals("mystery"))
			{
				for(Map.Entry<String,Comedy> entry : SaxParserDataStore.coms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("mystery"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Murder Mystery";
			}
			else if(CategoryName.equals("rushhour"))
			{
				for(Map.Entry<String,Comedy> entry : SaxParserDataStore.coms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("rushhour"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "RushHour";
			}
			else if(CategoryName.equals("shazam"))
			{
				for(Map.Entry<String,Comedy> entry : SaxParserDataStore.coms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("shazam"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Shazam";
			}
			else if(CategoryName.equals("superbad"))
			{
				for(Map.Entry<String,Comedy> entry : SaxParserDataStore.coms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("superbad"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Superbad";
			}
			else if(CategoryName.equals("toystory"))
			{
				for(Map.Entry<String,Comedy> entry : SaxParserDataStore.coms.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("toystory"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Toystory";
			}
		}
	


		
		/* Header, Left Navigation Bar are Printed.

		All the TV and tvs information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+"</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Comedy> entry : hm.entrySet())
		{
			Comedy com = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+com.getName()+"</h3>");
			pw.print("<strong>$"+com.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/coms/"+com.getImage()+"' alt='' /></li>");
			//System.out.println(com.getImage());
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='coms'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Book Tickets'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='coms'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+com.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='coms'>"+
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
