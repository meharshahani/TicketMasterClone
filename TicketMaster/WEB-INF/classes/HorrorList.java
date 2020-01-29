import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HorrorList")

public class HorrorList extends HttpServlet {

	/* Laptop Page Displays all the laptops and their Information in Game Speed */

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the laptops type whether it is electronicArts or activision or takeTwoInteractive */
				
		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, Horror> hm = new HashMap<String, Horror>();
		
		if(CategoryName==null)
		{
			hm.putAll(SaxParserDataStore.horrors);
			name = "";
		}
		else
		{
		  if(CategoryName.equals("ghost"))
		  {
			for(Map.Entry<String,Horror> entry : SaxParserDataStore.horrors.entrySet())
				{
				if(entry.getValue().getRetailer().equals("ghost"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "The ghost train";
		  }
		  else if(CategoryName.equals("rings"))
		  {
			for(Map.Entry<String,Horror> entry : SaxParserDataStore.horrors.entrySet())
				{
				if(entry.getValue().getRetailer().equals("rings"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
			name = "The rings";
		  }
		  else if(CategoryName.equals("witch"))
		  {
			for(Map.Entry<String,Horror> entry : SaxParserDataStore.horrors.entrySet())
				{
				if(entry.getValue().getRetailer().equals("witch"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "Mark of the Witch";
		  }
		  else if(CategoryName.equals("apocalypse"))
		  {
			for(Map.Entry<String,Horror> entry : SaxParserDataStore.horrors.entrySet())
				{
				if(entry.getValue().getRetailer().equals("apocalypse"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "Apocalypse Now";
		  }
		  else if(CategoryName.equals("quiet"))
		  {
			for(Map.Entry<String,Horror> entry : SaxParserDataStore.horrors.entrySet())
				{
				if(entry.getValue().getRetailer().equals("quiet"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "A quiet place";
		  }
		   else if(CategoryName.equals("getout"))
		  {
			for(Map.Entry<String,Horror> entry : SaxParserDataStore.horrors.entrySet())
				{
				if(entry.getValue().getRetailer().equals("getout"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "Get out";
		  }
		   else if(CategoryName.equals("mommy"))
		  {
			for(Map.Entry<String,Horror> entry : SaxParserDataStore.horrors.entrySet())
				{
				if(entry.getValue().getRetailer().equals("mommy"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "Goodnight Mommy";
		  }
		   else if(CategoryName.equals("halloween"))
		  {
			for(Map.Entry<String,Horror> entry : SaxParserDataStore.horrors.entrySet())
				{
				if(entry.getValue().getRetailer().equals("halloween"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "Halloween";
		  }
		   else if(CategoryName.equals("it"))
		  {
			for(Map.Entry<String,Horror> entry : SaxParserDataStore.horrors.entrySet())
				{
				if(entry.getValue().getRetailer().equals("it"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "IT";
		  }
		  else if(CategoryName.equals("itfollows"))
		  {
			for(Map.Entry<String,Horror> entry : SaxParserDataStore.horrors.entrySet())
				{
				if(entry.getValue().getRetailer().equals("itfollows"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "IT Follows";
		  }
		   else if(CategoryName.equals("pulp"))
		  {
			for(Map.Entry<String,Horror> entry : SaxParserDataStore.horrors.entrySet())
				{
				if(entry.getValue().getRetailer().equals("pulp"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "Pulp Fiction";
		  }

		    else if(CategoryName.equals("ready"))
		  {
			for(Map.Entry<String,Horror> entry : SaxParserDataStore.horrors.entrySet())
				{
				if(entry.getValue().getRetailer().equals("ready"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "Ready Or Not";
		  }

		    else if(CategoryName.equals("rings2"))
		  {
			for(Map.Entry<String,Horror> entry : SaxParserDataStore.horrors.entrySet())
				{
				if(entry.getValue().getRetailer().equals("rings2"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "Rings 2";
		  }

		  else if(CategoryName.equals("scary"))
		  {
			for(Map.Entry<String,Horror> entry : SaxParserDataStore.horrors.entrySet())
				{
				if(entry.getValue().getRetailer().equals("scary"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "Scary Stories";
		  }
		  else if(CategoryName.equals("baba"))
		  {
			for(Map.Entry<String,Horror> entry : SaxParserDataStore.horrors.entrySet())
				{
				if(entry.getValue().getRetailer().equals("baba"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "The Babadook";
		  }
		   else if(CategoryName.equals("conjuring"))
		  {
			for(Map.Entry<String,Horror> entry : SaxParserDataStore.horrors.entrySet())
				{
				if(entry.getValue().getRetailer().equals("conjuring"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "The Conjuring";
		  }
		   else if(CategoryName.equals("nun"))
		  {
			for(Map.Entry<String,Horror> entry : SaxParserDataStore.horrors.entrySet())
				{
				if(entry.getValue().getRetailer().equals("nun"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "The Nun";
		  }
		  
		}

		/* Header, Left Navigation Bar are Printed.

		All the laptops and its information are dispalyed in the Content Section

		and then Footer is Printed*/
		
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+"</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Horror> entry : hm.entrySet()){
			Horror horror = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+horror.getName()+"</h3>");
			pw.print("<strong>"+ "$" + horror.getPrice() + "</strong><ul>");
			pw.print("<li id='item'><img src='images/horrors/"+horror.getImage()+"' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='horrors'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Book Tickets'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='horrors'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+horror.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='horrors'>"+
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
