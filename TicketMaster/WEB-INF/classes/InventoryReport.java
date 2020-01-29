import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

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

@WebServlet("/InventoryReport")

public class InventoryReport extends HttpServlet {

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                Utilities utility = new Utilities(request,out);
                HttpSession session = request.getSession(true);
                MySqlDataStoreUtilities sqlUtil = new MySqlDataStoreUtilities();
                //HashMap<String,Accessory> accList = sqlUtil.getAccessoryCatalog();
                HashMap<String,Product> productList = sqlUtil.getProductCatalog();
				
                utility.printHtml("Header.html");
                utility.printHtml("LeftNavigationBar.html");
                out.print("<article><hr style='width: 95%'><h2 style='font-size: 35px; color:white'>Inventory</h2><hr style='width: 95%'>");
                out.print("<ul>");
    						out.print("<li><a href='#report1' style='font-size: 20px;color:white'><b>Product List with available quantity</b></a></li>");
    						out.print("<li><a href='#report2' style='font-size: 20px;color:white'><b>Bar Chart that shows the product names and the total number of items available</b></a></li>");
               // out.print("<li><a href='#report3' style='font-size: 20px;color:black'><b>Product List which is on Sale </b></a></li>");
               // out.print("<li><a href='#report4' style='font-size: 20px;color:black'><b>Product List which have manufacturer rebates</b></a></li>");
    						out.print("</ul>");

                out.print("<article id='report1'><hr style='width: 95%'><h2 style='font-size: 25px; color:white'> Product List with available quantity </h2><hr style='width: 95%'>");
                out.print("<table id='bestseller' style='width:80%'>");
                out.print("<tr><td>&nbsp;</td><td><b>Product Name </b></td><td><b>Product Price </b></td><td><b>Product Quantity</b></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
                for(String prodID: productList.keySet()){
                   Product prod = (Product)productList.get(prodID);
                      out.print("<tr><td>&nbsp;</td>"
                          + "<td>"+prod.getName()+"</td>"
                          + "<td> $ "+prod.getPrice()+"</td>"
                          + "<td>"+prod.getQuantity()+"</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
                  }
                out.print("</table><article>");

                out.print("<article id='report2'><hr style='width: 95%'><h2 style='font-size: 25px; color:white'> Bar Chart with the product names and the total number of items available </h2><hr style='width: 95%'>");
                out.println("<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>");
                out.println("<script type='text/javascript'>");
            		out.println("google.charts.load('current', {packages: ['corechart', 'bar']});");
            		out.println("google.charts.setOnLoadCallback(drawBasic);");
            		out.println("function drawBasic() {");
            		out.println("var data = google.visualization.arrayToDataTable([");
            		out.println("['Product Name', 'Total Items'],");

                Product pr = new Product();
                for(String prodID: productList.keySet()){
                   pr = (Product)productList.get(prodID);
                   String name = pr.getName();
                   int quantity = pr.getQuantity();
                   out.println("[' " +name+ " ', "+quantity+ "],");
                }
                out.println("]);");
                out.println("var options = {");
            		out.println("title: 'product names and the total number of items available',");
            		out.println("chartArea: {width: '65%', height: 950},");
            		out.println("hAxis: {");
            		out.println("title: 'Total number of products',");
            		out.println("minValue: 0");
            		out.println("},");
            		out.println("vAxis: {");
            		out.println("title: 'Product Name'");
            		out.println("}");
            		out.println("};");
            		out.println("var chart = new google.visualization.BarChart(document.getElementById('chart_div'));");
            		out.println("chart.draw(data, options);");
            		out.println("}");
                out.println("</script>");
                out.println("<div id='chart_div' style='width:900px; height:1000px'></div>");

                // out.print("<article id='report3'><hr style='width: 95%'><h2 style='font-size: 25px; color:white;'> Product List which is on Sale  </h2><hr style='width: 95%'>");
                // out.print("<table id='bestseller' style='width:80%'>");
                // out.print("<tr><td>&nbsp;</td><td><b>Product Name </b></td><td><b>Product Price </b></td><td><b>Product OnSale</b></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
                // for(String prodID: productList.keySet()){
                //    Product prod = (Product)productList.get(prodID);
                //    if(prod.getProdOnSale().equalsIgnoreCase("Yes")){
                //       out.print("<tr><td>&nbsp;</td>"
                //           + "<td>"+prod.getName()+"</td>"
                //           + "<td> $ "+prod.getPrice()+"</td>"
                //           + "<td>"+prod.getProdOnSale()+"</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
                //       }
                //   }
                // out.print("</table><article>");

                // out.print("<article id='report4'><hr style='width: 95%'><h2 style='font-size: 25px; color:white;'> Product List which have manufacturer rebates </h2><hr style='width: 95%'>");
                // out.print("<table id='bestseller' style='width:80%'>");
                // out.print("<tr><td>&nbsp;</td><td><b>Product Name </b></td><td><b>Product Price </b></td><td><b>Manufacturer Rebate </b></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
                // for(String prodID: productList.keySet()){
                //    Product prod = (Product)productList.get(prodID);
                //    if(prod.getManuRebate().equalsIgnoreCase("Yes")){
                //       out.print("<tr><td>&nbsp;</td>"
                //           + "<td style='color:white;'>"+prod.getName()+"</td>"
                //           + "<td> $ "+prod.getPrice()+"</td>"
                //           + "<td>"+prod.getManuRebate()+"</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
                //       }
                //   }
                out.print("</table><article>");
                
                
                utility.printHtml("Footer.html");
                
        }
}
