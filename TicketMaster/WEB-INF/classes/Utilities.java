import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/Utilities")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session; 
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}



	/*  Printhtml Function gets the html file name as function Argument, 
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

	public void printHtml(String file) {
		String result = HtmlToString(file);
		//to print the right navigation in header of username cart and logout etc
		if (file == "Header.html") {
				result=result+"<div id='menu' style='float: right;'><ul>";
			if (session.getAttribute("username")!=null){
				String username = session.getAttribute("username").toString();
				String usertype = session.getAttribute("usertype").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);

				if(session.getAttribute("usertype").equals("manager"))
				{
					result = result + "<li><a href='ProductModify?button=Addproduct'><span class='glyphicon'>Addproduct</span></a></li>"
						+ "<li><a href='ProductModify?button=Updateproduct'><span class='glyphicon'>Updateproduct</span></a></li>"
						+"<li><a href='ProductModify?button=Deleteproduct'><span class='glyphicon'>Deleteproduct</span></a></li>"
						+"<li><a href='DataVisualization'><span class='glyphicon'>Trending</span></a></li>"
						+"<li><a href='DataAnalytics'><span class='glyphicon'>DataAnalytics</span></a></li>"
						+"<li><a href='InventoryReport'><span class='glyphicon'>Inventory</span></a></li>"
						+"<li><a href='SalesReport'><span class='glyphicon'>Sales Report</span></a></li>"
						+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
				}
				
				else if(session.getAttribute("usertype").equals("retailer")){
					result = result + "<li><a href='Registration'><span class='glyphicon'>Create Customer</span></a></li>"
						+ "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
						+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
				}
				else
				{
				result = result + "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
						+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
						+ "<li><a href='Account'><span class='glyphicon'>Account</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
			    }
			}
			else
				result = result +"<li><a href='ViewOrder'><span class='glyphicon'>View Order</span></a></li>"+ "<li><a href='Login'><span class='glyphicon'>Login</span></a></li>";
				result = result +"<li><a href='Cart'><span class='glyphicon'>Cart</span></a></li></ul></div></div><div id='page'>";
				pw.print(result);
		} else
				pw.print(result);
	}
	

	/*  getFullURL Function - Reconstructs the URL user request  */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		} 
		catch (Exception e) {
		}
		return result;
	} 

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}
	
	/*  logout Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/
	
	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}
	
	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}
	
	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
	public User getUser(){
		String usertype = usertype();
		HashMap<String, User> hm=new HashMap<String, User>();
		//String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{		
				hm=MySqlDataStoreUtilities.selectUser();
			}
			catch(Exception e)
			{
			}	
		User user = hm.get(username());
		return user;
	}
	
	/*  getCustomerOrders Function gets  the Orders for the user*/
	public ArrayList<OrderItem> getCustomerOrders(){
		ArrayList<OrderItem> order = new ArrayList<OrderItem>(); 
		if(OrdersHashMap.orders.containsKey(username()))
			order= OrdersHashMap.orders.get(username());
		return order;
	}

	/*  getOrdersPaymentSize Function gets  the size of OrderPayment */
	public int getOrderPaymentSize(){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		//String TOMCAT_HOME = System.getProperty("catalina.home");
		int size=0;
			try
			{
				orderPayments =MySqlDataStoreUtilities.selectOrder();
			}
			catch(Exception e)
			{
			
			}
			// int size=0;
			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
					 // size=size + 1;
			size=entry.getKey();		 
			}
			return size;		
	}

	/*  CartCount Function gets  the size of User Orders*/
	public int CartCount(){
		if(isLoggedin())
		return getCustomerOrders().size();
		return 0;
	}
	
	/* StoreProduct Function stores the Purchased product in Orders HashMap according to the User Names.*/

	public void storeProduct(String name,String type,String maker, String acc){
		if(!OrdersHashMap.orders.containsKey(username())){	
			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
			OrdersHashMap.orders.put(username(), arr);
		}
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
		HashMap<String,Comedy> allcoms = new HashMap<String,Comedy> ();
		HashMap<String,Horror> allhorrors = new HashMap<String,Horror> ();
		HashMap<String,Romantic> allroms = new HashMap<String,Romantic> ();


		if(type.equals("coms")){
			Comedy com;
			try
			{
				allcoms = MySqlDataStoreUtilities.getComedys();
			}
			catch(Exception e)
			{
		
			}
			com = allcoms.get(name);
			com = SaxParserDataStore.coms.get(name);
			OrderItem orderitem = new OrderItem(com.getName(), com.getPrice(), com.getImage(), com.getRetailer(),com.getRuntime(),com.getSummary(),type);
			orderItems.add(orderitem);
		}
		if(type.equals("horrors")){
			Horror horror = null;
			try
			{
				allhorrors = MySqlDataStoreUtilities.getHorrors();
			}
			catch(Exception e)
			{
		
			}
			horror = SaxParserDataStore.horrors.get(name);
			OrderItem orderitem = new OrderItem(horror.getName(), horror.getPrice(), horror.getImage(), horror.getRetailer(), horror.getRuntime(),horror.getSummary(),type);
			orderItems.add(orderitem);
		}
		if(type.equals("roms")){
			Romantic rom = null;
			try
			{
				allroms = MySqlDataStoreUtilities.getRomantics();
			}
			catch(Exception e)
			{
		
			}
			rom = SaxParserDataStore.roms.get(name);
			OrderItem orderitem = new OrderItem(rom.getName(), rom.getPrice(), rom.getImage(), rom.getRetailer(), rom.getRuntime(),rom.getSummary(),type);
			orderItems.add(orderitem);
		}
		
	}
	// store the payment details for orderstickets
	public void storePayment(int orderId,String orderName,double orderPrice,String userAddress,String creditCardNo,String date_place, String customer)
	{
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
		//String TOMCAT_HOME = System.getProperty("catalina.home");
			// get the payment details file 
			try
			{
				
				orderPayments=MySqlDataStoreUtilities.selectOrder();
			}
			catch(Exception e)
			{
			
			}
			if(orderPayments==null)
			{
				orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			}
			// if there exist order id already add it into same list for order id or create a new record with order id
			
			if(!orderPayments.containsKey(orderId)){	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(orderId, arr);
			}
		ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
		OrderPayment orderpayment = new OrderPayment(orderId,username(),orderName,orderPrice,userAddress,creditCardNo,date_place);
		listOrderPayment.add(orderpayment);	
			
			// add order details into file
			
		try
		{	
			if(session.getAttribute("usertype").equals("retailer"))
			{
				MySqlDataStoreUtilities.insertOrder(orderId,customer,orderName,orderPrice,userAddress,creditCardNo,date_place);
			}
			else
			{
				MySqlDataStoreUtilities.insertOrder(orderId,username(),orderName,orderPrice,userAddress,creditCardNo,date_place);
			}
		}
		catch(Exception e)
		{
			System.out.println("inside exception file not written properly");
		}	
	}


	public String storeReview(String productname,String producttype,String productmaker,String reviewrating,String reviewdate,String reviewtext,String reatilerpin,String price)
     {
	String message=MongoDBDataStoreUtilities.insertReview(productname,username(),producttype,productmaker,reviewrating,reviewdate,reviewtext,reatilerpin,price);
		if(!message.equals("Successfull"))
		{ return "UnSuccessfull";
		}
		else
		{
		HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
		try
		{
			reviews=MongoDBDataStoreUtilities.selectReview();
		}
		catch(Exception e)
		{
			
		}
		if(reviews==null)
		{
			reviews = new HashMap<String, ArrayList<Review>>();
		}
			// if there exist product review already add it into same list for productname or create a new record with product name
			
		if(!reviews.containsKey(productname)){	
			ArrayList<Review> arr = new ArrayList<Review>();
			reviews.put(productname, arr);
		}
		ArrayList<Review> listReview = reviews.get(productname);		
		Review review = new Review(productname,username(),producttype,productmaker,reviewrating,reviewdate,reviewtext,reatilerpin,price);
		listReview.add(review);	
			
			// add Reviews into database
		
		return "Successfull";	
		}
	}

	
	/* get Functions returns the Hashmap with all particular product in the store.*/

	public HashMap<String, Comedy> getComedy(){
			HashMap<String, Comedy> hm = new HashMap<String, Comedy>();
			hm.putAll(SaxParserDataStore.coms);
			return hm;
	}

	public HashMap<String, Horror> getHorror(){
			HashMap<String, Horror> hm = new HashMap<String, Horror>();
			hm.putAll(SaxParserDataStore.horrors);
			return hm;
	}

	public HashMap<String, Romantic> getRomantic(){
			HashMap<String, Romantic> hm = new HashMap<String, Romantic>();
			hm.putAll(SaxParserDataStore.roms);
			return hm;
	}



	/* getProducts Functions returns the Arraylist of products in the store.*/

	public ArrayList<String> getProductsCom(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Comedy> entry : getComedy().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	public ArrayList<String> getProductsHorror(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Horror> entry : getHorror().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	public ArrayList<String> getProductsRomantic(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Romantic> entry : getRomantic().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}



	 public HashMap<String,String> readOutputFile(){
String csvFile = "C:/test3/output.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
HashMap<String,String> prodRecmMap = new HashMap<String,String>();
try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] prod_recm = line.split(cvsSplitBy,2);
prodRecmMap.put(prod_recm[0],prod_recm[1]);
            }

} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
}

return prodRecmMap;
}

}


