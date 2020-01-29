import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Romantic")


/* 
	TV class contains class variables name,price,image,retailer,condition,discount and Accessories Hashmap.

	TV class constructor with Arguments name,price,image,retailer,condition,discount and Accessories .
	  
	Accessory class contains getters and setters for name,price,image,retailer,condition,discount and Accessories .

*/

public class Romantic extends HttpServlet{
	private String id;
	private String name;
	private double price;
	private String image;
	private String retailer;
	private String runtime;
	private String summary;
	private String ProductType;
	// private String condition;
	// private double discount;	
	

	//public Romantic(String name, double price, String image, String retailer,String condition,double discount)
	public Romantic(String name, double price, String image, String retailer,String runtime,String summary,String ProductType)
	{
		this.name=name;
		this.price=price;
		this.image=image;
		this.retailer = retailer;
		this.runtime = runtime;
		this.summary = summary;
		this.ProductType = ProductType;
		// this.condition=condition;
		// this.discount = discount;
	}
	

	public Romantic(){
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRetailer() {
		return retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getProductType() {
		return ProductType;
	}

	public void setProductType(String ProductType) {
		this.ProductType = ProductType;
	}
	
	// public String getCondition() {
	// 	return condition;
	// }

	// public void setCondition(String condition) {
	// 	this.condition = condition;
	// }

	// public double getDiscount() {
	// 	return discount;
	// }

	// public void setDiscount(double discount) {
	// 	this.discount = discount;
	// }
	
}
