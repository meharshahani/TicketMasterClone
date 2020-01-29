import java.sql.*;
import java.util.*;
                	
public class MySqlDataStoreUtilities
{
static Connection conn = null;
static String message;
public static String getConnection()
{

	try
	{
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb","root","root");							
	message="Successfull";
	return message;
	}
	catch(SQLException e)
	{
		message="unsuccessful";
		     return message;
	}
	catch(Exception e)
	{
		message=e.getMessage();
		return message;
	}
}

public static void Insertproducts()
{
	try
	{
		
		
		getConnection();
		
		
		String truncatetableacc = "delete from Product_accessories;";
		PreparedStatement pstt = conn.prepareStatement(truncatetableacc);
		pstt.executeUpdate();
		
		String truncatetableprod = "delete from Productdetails;";
		PreparedStatement psttprod = conn.prepareStatement(truncatetableprod);
		psttprod.executeUpdate();
		int quantity = 15;
				
		
		//String insertProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,quantity,prodOnSale,manuRebate)" +
		String insertProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,quantity,runtime,summary)" +
		"VALUES (?,?,?,?,?,?,?,?,?);";
		for(Map.Entry<String,Comedy> entry : SaxParserDataStore.coms.entrySet())
		{   
			Comedy com = entry.getValue();
			String name = "coms";
			//System.out.println("hello");
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			System.out.println(com.getId());
			pst.setString(1,name);
			pst.setString(2,com.getId());
			pst.setString(3,com.getName());
			pst.setDouble(4,com.getPrice());
			pst.setString(5,com.getImage());
			pst.setString(6,com.getRetailer());
			// pst.setString(7,com.getCondition());
			// pst.setDouble(8,com.getDiscount());
			pst.setInt(7,25);
			pst.setString(8,com.getRuntime());
			pst.setString(9,com.getSummary());
			// pst.setString(8,"yes");
			// pst.setString(9,"no");
			pst.execute();
		}

		for(Map.Entry<String,Horror> entry : SaxParserDataStore.horrors.entrySet())
		{   
			String name = "horrors";
	        Horror horror = entry.getValue();
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			System.out.println(horror.getId());
			pst.setString(1,name);
			pst.setString(2,horror.getId());
			pst.setString(3,horror.getName());
			pst.setDouble(4,horror.getPrice());
			pst.setString(5,horror.getImage());
			pst.setString(6,horror.getRetailer());
			// pst.setString(7,horror.getCondition());
			// pst.setDouble(8,horror.getDiscount());
			pst.setInt(7,10);
			pst.setString(8,horror.getRuntime());
			pst.setString(9,horror.getSummary());
			// pst.setString(8,"yes");
			// pst.setString(9,"no");
			pst.executeUpdate();

		}
		
		for(Map.Entry<String,Romantic> entry : SaxParserDataStore.roms.entrySet())
		{   
			String name = "roms";
	        Romantic rom = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,rom.getId());
			pst.setString(3,rom.getName());
			pst.setDouble(4,rom.getPrice());
			pst.setString(5,rom.getImage());
			pst.setString(6,rom.getRetailer());
			// pst.setString(7,rom.getCondition());
			// pst.setDouble(8,rom.getDiscount());
			pst.setInt(7,40);
			pst.setString(8,rom.getRuntime());
			pst.setString(9,rom.getSummary());
			// pst.setString(8,"no");
			// pst.setString(9,"yes");
			pst.executeUpdate();
		}

		
	}
	catch(Exception e)
	{
  		e.printStackTrace();
	}
} 



// public static HashMap<String,TV> getTvs()
// {	
// 	HashMap<String,TV> hm=new HashMap<String,TV>();
// 	try 
// 	{
// 		getConnection();
		
// 		String selectTv="select * from  Productdetails where ProductType=?";
// 		PreparedStatement pst = conn.prepareStatement(selectTv);
// 		pst.setString(1,"tvs");
// 		ResultSet rs = pst.executeQuery();
	
// 		while(rs.next())
// 		{	TV tv = new TV(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
// 				hm.put(rs.getString("Id"), tv);
// 				tv.setId(rs.getString("Id"));
				
// 				try
// 				{
// 				String selectaccessory = "Select * from Product_accessories where productName=?";
// 				PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
// 				pstacc.setString(1,rs.getString("Id"));
// 				ResultSet rsacc = pstacc.executeQuery();
				
// 				HashMap<String,String> acchashmap = new HashMap<String,String>();
// 				while(rsacc.next())
// 				{    
// 					if (rsacc.getString("accessoriesName") != null){
					
// 					 acchashmap.put(rsacc.getString("accessoriesName"),rsacc.getString("accessoriesName"));
// 					 tv.setAccessories(acchashmap);
// 					}
					 
// 				}					
// 				}catch(Exception e)
// 				{
// 						e.printStackTrace();
// 				}
// 		}
// 	}
// 	catch(Exception e)
// 	{
// 	}
// 	return hm;			
// }

public static HashMap<String,Comedy> getComedys()
{	
	HashMap<String,Comedy> hm=new HashMap<String,Comedy>();
	try 
	{
		getConnection();
		
		String selectTv="select * from Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectTv);
		pst.setString(1,"coms");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	
			//Comedy com = new Comedy(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
			Comedy com = new Comedy(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("runtime"),rs.getString("ProductType"),rs.getString("summary"));	
			com.setId(rs.getString("Id"));
			hm.put(com.getId(), com);
				
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}


public static HashMap<String,Horror> getHorrors()
{	
	HashMap<String,Horror> hm=new HashMap<String,Horror>();
	try 
	{
		getConnection();
		
		String selectHorror="select * from Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectHorror);
		pst.setString(1,"horrors");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	
			//Horror horror = new Horror(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
			Horror horror = new Horror(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("runtime"),rs.getString("ProductTye"),rs.getString("summary"));	
			hm.put(rs.getString("Id"), horror);
				horror.setId(rs.getString("Id"));
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Romantic> getRomantics()
{	
	HashMap<String,Romantic> hm=new HashMap<String,Romantic>();
	try 
	{
		getConnection();
		
		String selectRomantic="select * from Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectRomantic);
		pst.setString(1,"roms");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	
			//Romantic rom = new Romantic(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				
			Romantic rom = new Romantic(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("runtime"),rs.getString("ProductTye"),rs.getString("summary"));
			hm.put(rs.getString("Id"), rom);
				rom.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}



// public static HashMap<String,Accessory> getAccessories()
// {	
// 	HashMap<String,Accessory> hm=new HashMap<String,Accessory>();
// 	try 
// 	{
// 		getConnection();
		
// 		String selectAcc="select * from  Productdetails where ProductType=?";
// 		PreparedStatement pst = conn.prepareStatement(selectAcc);
// 		pst.setString(1,"accessories");
// 		ResultSet rs = pst.executeQuery();
	
// 		while(rs.next())
// 		{	Accessory acc = new Accessory(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
// 				hm.put(rs.getString("Id"), acc);
// 				acc.setId(rs.getString("Id"));

// 		}
// 	}
// 	catch(Exception e)
// 	{
// 	}
// 	return hm;			
// }






//public static String addproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String productCondition,double productDiscount,String prod)
public static String addproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String prod,String runtime,String summary)
//public static String addproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String runtime)
{
	String msg = "Product is added successfully";
	try{
		
		getConnection();
		//String addProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount)" +
		String addProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,quantity,runtime,summary)" +	
		"VALUES (?,?,?,?,?,?,?,?,?);";
		   
			String name = producttype;
			Integer quantity = 20;
	        System.out.println(productName);	
			PreparedStatement pst = conn.prepareStatement(addProductQurey);
			pst.setString(1,name);
			pst.setString(2,productId);
			pst.setString(3,productName);
			pst.setDouble(4,productPrice);
			pst.setString(5,productImage);
			pst.setString(6,productManufacturer);
			pst.setInt(7,quantity);
			pst.setString(8,runtime);
			pst.setString(9,summary);
			// pst.setString(7,productCondition);
			// pst.setDouble(8,productDiscount);
			
			pst.executeUpdate();
			try{
				if (!prod.isEmpty())
				{
					String addaprodacc =  "INSERT INTO Product_accessories(productName,accessoriesName)" +
					"VALUES (?,?);";
					PreparedStatement pst1 = conn.prepareStatement(addaprodacc);
					pst1.setString(1,prod);
					pst1.setString(2,productId);
					pst1.executeUpdate();
					
				}
			}catch(Exception e)
			{
				msg = "Error while adding the product";
				e.printStackTrace();
		
			}
			
			
		
	}
	catch(Exception e)
	{
		msg = "Error while adding the product";
		e.printStackTrace();
		
	}
	return msg;
}


public static String updateproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String runtime,String summary)
{ 
    String msg = "Product is updated successfully";
	try{
		
		getConnection();
		String updateProductQurey = "UPDATE Productdetails SET productName=?,productPrice=?,productImage=?,productManufacturer=?,runtime=?,summary=? where Id =?;" ;
		
		   
				        			
			PreparedStatement pst = conn.prepareStatement(updateProductQurey);
			
			pst.setString(1,productName);
			pst.setDouble(2,productPrice);
			pst.setString(3,productImage);
			pst.setString(4,productManufacturer);
			// pst.setString(5,productCondition);
			// pst.setDouble(6,productDiscount);
			// pst.setString(5,productId);
			pst.setString(5,runtime);
			pst.setString(6,summary);
			pst.setString(7,productId);
			pst.executeUpdate();
			
			
		
	}
	catch(Exception e)
	{
		msg = "Product cannot be updated";
		e.printStackTrace();
		
	}
 return msg;	
}
public static String deleteproducts(String productId)
{   
	String msg = "Product is deleted successfully";
	try
	{
		
		getConnection();
		String deleteproductsQuery ="Delete from Productdetails where Id=?";
		PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
		pst.setString(1,productId);
		
		pst.executeUpdate();
	}
	catch(Exception e)
	{
			msg = "Product cannot be deleted";
	}
	return msg;
}

public static void deleteOrder(int orderId,String orderName)
{
	try
	{
		
		getConnection();
		String deleteOrderQuery ="Delete from customerorders where OrderId=? and orderName=?";
		PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
		pst.setInt(1,orderId);
		pst.setString(2,orderName);
		pst.executeUpdate();
	}
	catch(Exception e)
	{
			
	}
}

public static void insertOrder(int orderId,String userName,String orderName,double orderPrice,String userAddress,String creditCardNo,String date_place)
{
	try
	{
	
		getConnection();
		
		String insertIntoCustomerOrderQuery = "INSERT INTO customerOrders(OrderId,userName,orderName,orderPrice,userAddress,creditCardNo,date_place) "
		+ "VALUES (?,?,?,?,?,?,?);";	

		System.out.print(orderId+ userName+ orderName+ orderPrice + userAddress + creditCardNo + date_place);
			
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
		//set the parameter for each column and execute the prepared statement
		pst.setInt(1,orderId);
		pst.setString(2,userName);
		pst.setString(3,orderName);
		pst.setDouble(4,orderPrice);
		pst.setString(5,userAddress);
		pst.setString(6,creditCardNo);
		pst.setString(7,date_place);
		pst.execute();
	}
	catch(Exception e)
	{
	
	}		
}

public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder()
{	

	HashMap<Integer, ArrayList<OrderPayment>> orderPayments=new HashMap<Integer, ArrayList<OrderPayment>>();
		
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select * from customerorders";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		ResultSet rs = pst.executeQuery();	
		ArrayList<OrderPayment> orderList=new ArrayList<OrderPayment>();
		while(rs.next())
		{
			if(!orderPayments.containsKey(rs.getInt("OrderId")))
			{	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(rs.getInt("orderId"), arr);
			}
			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("OrderId"));		
			

			//add to orderpayment hashmap
			OrderPayment order= new OrderPayment(rs.getInt("OrderId"),rs.getString("userName"),rs.getString("orderName"),rs.getDouble("orderPrice"),rs.getString("userAddress"),rs.getString("creditCardNo"),rs.getString("date_place"));
			listOrderPayment.add(order);
					
		}
				
					
	}
	catch(Exception e)
	{
		
	}
	return orderPayments;
}


public static void insertUser(String username,String password,String repassword,String usertype)
{
	try
	{	

		getConnection();
		String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,repassword,usertype) "
		+ "VALUES (?,?,?,?);";	
				
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
		pst.setString(1,username);
		pst.setString(2,password);
		pst.setString(3,repassword);
		pst.setString(4,usertype);
		pst.execute();
	}
	catch(Exception e)
	{
	
	}	
}

public static HashMap<String,User> selectUser()
{	
	HashMap<String,User> hm=new HashMap<String,User>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from Registration";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	User user = new User(rs.getString("username"),rs.getString("password"),rs.getString("usertype"));
				hm.put(rs.getString("username"), user);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

 public HashMap<String,Product> getProductCatalog(){
            Product prod;
            HashMap<String,Product> productList = new HashMap<String,Product>();
            try{
                getConnection();
                
                Statement stmt=conn.createStatement();
                ResultSet rs=stmt.executeQuery("select * from moviedb.productdetails");

                while(rs.next()){
                    prod = new Product();
                    
                    prod.setName(rs.getString("productName"));
                    prod.setPrice(rs.getDouble("productPrice"));
                    prod.setId(rs.getString("id"));
					prod.setQuantity(rs.getInt("quantity"));
					// prod.setProdOnSale(rs.getString("prodOnSale"));
					// prod.setManuRebate(rs.getString("manuRebate"));
					
                    productList.put(prod.getId(),prod);
                }
                rs.close();
                stmt.close();
            }catch(SQLException exception){
                exception.printStackTrace();
            }
            

            return productList;
       }

	    public ArrayList<Product> getProductSalesStat(){
						 Product prod;
						 ArrayList<Product> prodSoldList = new ArrayList<Product>();
						 try{
								 getConnection();
								
								 Statement stmt=conn.createStatement();
								 ResultSet rs=stmt.executeQuery("select orderName,orderPrice,count(orderName) as items_sold,(orderPrice * count(orderName)) as total_sales from moviedb.customerorders group by orderName");
								 while(rs.next()){
										 prod = new Product();
										 prod.setName(rs.getString("orderName"));
										 prod.setPrice(rs.getDouble("orderPrice"));                                    
										 prod.setItems_Sold(rs.getInt("items_sold"));
										 prod.setTotal_Sales(rs.getDouble("total_sales"));
										 prodSoldList.add(prod);
								 }
								 rs.close();
								 stmt.close();
						 }catch(SQLException exception){
								 exception.printStackTrace();
						 }
						 
						 return prodSoldList;
			 }


			 public ArrayList<Product> getDailySalesTransaction(){
						 Product prod;
						 ArrayList<Product> salesTransList = new ArrayList<Product>();
						 try{
								 getConnection();
								 
								 Statement stmt=conn.createStatement();
								 ResultSet rs=stmt.executeQuery("select date_place,sum(orderPrice) as total_sales from moviedb.customerorders group by date_place");
								 while(rs.next()){
										 prod = new Product();
										 prod.setDate_Place(rs.getString("date_place"));
										 prod.setTotal_Sales(rs.getDouble("total_sales"));
										 salesTransList.add(prod);
								 }
								 rs.close();
								 stmt.close();
						 }catch(SQLException exception){
								 exception.printStackTrace();
						 }
						 
						 return salesTransList;
			 }

			 public static HashMap<String,Product> getData()
	{
		HashMap<String,Product> hm=new HashMap<String,Product>();
		try
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectCustomerQuery="select * from  productdetails";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			while(rs.next())
			{	
				///Product p = new Product(rs.getString("Id"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getString("ProductType"),rs.getDouble("productDiscount"));
				Product p = new Product(rs.getString("Id"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("ProductType"));
				hm.put(rs.getString("Id"), p);
			}
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		return hm;			
	}

	
}		