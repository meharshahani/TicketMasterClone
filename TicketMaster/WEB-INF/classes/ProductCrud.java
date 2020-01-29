import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductCrud")

public class ProductCrud extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			Utilities utility = new Utilities(request, pw);
			String action = request.getParameter("button");
			
			String msg = "good";
			String producttype= "",productId="",productName="",productImage="",productManufacturer="",productCondition="",prod = "",runtime="",summary="";
			double productPrice=0.0,productDiscount = 0.0;

			HashMap<String,Comedy> allcoms = new HashMap<String,Comedy> ();			
			HashMap<String,Horror> allhorrors = new HashMap<String,Horror> ();
			HashMap<String,Romantic> allroms = new HashMap<String,Romantic> ();


			//HashMap<String,Game> allgames = new HashMap<String,Game> ();
			//HashMap<String,Accessory> allaccessories=new HashMap<String,Accessory>();
			System.out.println(action);
			if (action.equals("add") || action.equals("update"))
			{	
				System.out.println("ABCD");
				 producttype = request.getParameter("producttype");
				 System.out.println("PRODUCTTYPE:"+producttype);
				 productId   = request.getParameter("productId");
				 productName = request.getParameter("productName"); 
				 productPrice = Double.parseDouble(request.getParameter("productPrice"));
				 productImage = request.getParameter("productImage");
				 productManufacturer = request.getParameter("productManufacturer");
				 runtime = request.getParameter("runtime");
				 summary = request.getParameter("summary");
				//  productCondition = request.getParameter("productCondition");
				//  productDiscount = Double.parseDouble(request.getParameter("productDiscount"));
				 
			}
			else
			{
				productId   = request.getParameter("productId");
			}	
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");

			if(action.equals("add"))
			{
			  if(producttype.equals("coms"))
			  {
				  allcoms = MySqlDataStoreUtilities.getComedys();
				  if(allcoms.containsKey(productId))
				  {
					  msg = "Product already available";
					  
				  }	  
			  }
			  else if (producttype.equals("horrors"))
			  {
				  allhorrors = MySqlDataStoreUtilities.getHorrors();
				  if(allhorrors.containsKey(productId))
				  {
					  msg = "Product already available";
				  }
			  }
			  else if (producttype.equals("roms"))
			  {
				  allroms = MySqlDataStoreUtilities.getRomantics();
				  if(allroms.containsKey(productId))
				  {
					  msg = "Product already available";
				  }
			  }
			  
			  // else if (producttype.equals("accessories"))
			  // {  
					// if(!request.getParameter("product").isEmpty())
					// 	{
					// 		prod = request.getParameter("product");
					// 		allconsoles = MySqlDataStoreUtilities.getConsoles();
					// 		if(allconsoles.containsKey(prod))
					// 		{
					// 			allaccessories = MySqlDataStoreUtilities.getAccessories();
					// 			if(allaccessories.containsKey(productId)){
					// 				msg = "Product already available";
					// 			}
					// 		}else{
					// 			msg = "The product related to accessories is not available";
					// 		}
						
						
					// 	}else{
					// 		msg = "Please add the prodcut name";
					// 	}
				  
			  // }	
			  if (msg.equals("good"))
			  {  
				  try
				  {
				//	  msg = MySqlDataStoreUtilities.addproducts(producttype,productId,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,prod);
				  	 msg = MySqlDataStoreUtilities.addproducts(producttype,productId,productName,productPrice,productImage,productManufacturer,prod,runtime,summary);
					//msg = MySqlDataStoreUtilities.addproducts(producttype,productId,productName,productPrice,productImage,productManufacturer,runtime);	
				}
				  catch(Exception e)
				  { 
					msg = "Product cannot be inserted";
				  }
				  msg = "Product has been successfully added";
			  }					
			}
			else if(action.equals("update"))
			{
			  if(producttype.equals("coms"))
			  {
				  allcoms = MySqlDataStoreUtilities.getComedys();
				  System.out.println(allcoms);
				  if(!allcoms.containsKey(productId)){
					  msg = "Product not available";
				  }
					  
			  }
			  else if(producttype.equals("horrors"))
			  {
				  allhorrors = MySqlDataStoreUtilities.getHorrors();
				  if(!allhorrors.containsKey(productId)){
					  msg = "Product not available";
				  }
			  }
			  else if(producttype.equals("roms"))
			  {
				  allroms = MySqlDataStoreUtilities.getRomantics();
				  if(!allroms.containsKey(productId)){
					  msg = "Product not available";
				  }
			  }
			 



			 //  else if (producttype.equals("accessories"))
			 //  {
				//   allaccessories = MySqlDataStoreUtilities.getAccessories();
				//   if(!allaccessories.containsKey(productId)){
				// 	  msg = "Product not available";
				// }
			 //  }	

			  if (msg.equals("good"))
			  {		
				
				  try
				  {
					//msg = MySqlDataStoreUtilities.updateproducts(producttype,productId,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount);
					msg = MySqlDataStoreUtilities.updateproducts(producttype,productId,productName,productPrice,productImage,productManufacturer,runtime,summary); 
				
				}
				  catch(Exception e)
				  { 
					msg = "Product cannot be updated";
				  }
				  msg = "Product has been successfully updated";
			  } 
			}
			else if(action.equals("delete"))
			{
				  msg = "bad";

				  allcoms = MySqlDataStoreUtilities.getComedys();
				  if(allcoms.containsKey(productId))
				  {
					  msg = "good";	 
				  }
			  
				  allhorrors = MySqlDataStoreUtilities.getHorrors();
				  if(allhorrors.containsKey(productId)){
					  msg = "good";
				  }
			  
				  allroms = MySqlDataStoreUtilities.getRomantics();
				  if(allroms.containsKey(productId)){
					  msg = "good";
				  }

			  
				//   allaccessories = MySqlDataStoreUtilities.getAccessories();
				//   if(allaccessories.containsKey(productId)){
				// 	  msg = "good";
				// }
		       		
				  if (msg.equals("good"))
				  {		
					
					  try
					  {  
						
						 msg = MySqlDataStoreUtilities.deleteproducts(productId);
					  }
					  catch(Exception e)
					  { 
						msg = "Product cannot be deleted";
					  }
					   msg = "Product has been successfully deleted";
				  }else{
					  msg = "Product not available";
				  }
			}	
				
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<h4 style='color:blue'>"+msg+"</h4>");
			pw.print("</div></div></div>");		
			utility.printHtml("Footer.html");
			
	}
}