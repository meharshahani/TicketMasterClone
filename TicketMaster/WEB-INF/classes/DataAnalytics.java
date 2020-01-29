import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.AggregationOutput;
import javax.servlet.http.HttpSession;
@WebServlet("/DataAnalytics")

public class DataAnalytics extends HttpServlet {
	static DBCollection myReviews;
	/* Trending Page Displays all the Consoles and their Information in Game Speed*/

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
				
		
		//check if the user is logged in
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to View Reviews");
			response.sendRedirect("Login");
			return;
		}
		
						
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Data Analytics on Review</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table id='bestseller'>");
		pw.print("<form method='post' action='FindReviews'>");
	
     		pw.print("<table id='bestseller'>");
			pw.print("<tr>");
			pw.print("<td> <input type='checkbox' name='queryCheckBox' value='productName'> Select </td>");
                                pw.print("<td> Product Name: </td>");
                                pw.print("<td>");
                                       pw.print("<select name='productName'>");
				       pw.print("<option value='ALL_PRODUCTS'>All Products</option>");
                                       pw.print("<option value='millers'>Were the millers</option>");
                                       pw.print("<option value='bridesmaids'>BridesMaids</option>");
                                       pw.print("<option value='rebound'>The Rebound</option>");
                                       pw.print(" <option value='hangover'>Hangover</option>");
                                       pw.print("<option value='dates'>50 First Dates</option>");  
							           pw.print("<option value='airplane'>Airplane</option>");
										pw.print("<option value='dumb'>Dumb and the dumber</option>");
										pw.print("<option value='sunday'>Sunday</option>");
										pw.print("<option value='good'>Good Boys</option>");
										pw.print("<option value='hustle'>Hustle</option>");
										pw.print("<option value='longshot'>LongShot</option>");
										pw.print("<option value='MIB'>MAn in Black</option>");
										pw.print("<option value='mystery'>Murder Mystery</option>");
										pw.print("<option value='rushhour'>Rushhour</option>");
										pw.print("<option value='shazam'>Shazam</option>");
										pw.print("<option value='superbad'>Superbad</option>");
										pw.print("<option value='toystory'>ToyStory</option>");
										pw.print("<option value='ghost'>The Ghost Train</option>");
										pw.print("<option value='rings'>Rings</option>");
										pw.print("<option value='witch'>The mark of a witch</option>");
										pw.print("<option value='apocalypse'>Apocalypse Now</option>");
										pw.print("<option value='quite'>A quite place</option>");
										pw.print("<option value='getout'>Get Out</option>");
										//pw.print("<option value='Keypad'>Keypad</option>");
										pw.print("<option value='mommy'>Goodnight mommy</option>");
										pw.print("<option value='halloween'>Halloween</option>");
										pw.print("<option value='it'>IT</option>");
										pw.print("<option value='itfollows'>IT FOllows</option>");
										pw.print("<option value='pulp'>Pulpfiction</option>");
										pw.print("<option value='ready'>Ready or not</option>");
										pw.print("<option value='rings2'>Rings 2</option>");
										pw.print("<option value='scary'>Scary stories</option>");
										pw.print("<option value='baba'>the babadook</option>");
										pw.print("<option value='conjuring'>The Conjuring</option>");
										pw.print("<option value='nun'>The nun</option>");
										pw.print("<option value='love'>P.S. I love you</option>");
										pw.print("<option value='safe'>A safe heaven</option>");
										pw.print("<option value='star'>A Star is born</option>");
										pw.print("<option value='angry'>12 angry men</option>");
										pw.print("<option value='minutes'>30 minutes or less</option>");
										pw.print("<option value='time'>About time</option>");
										pw.print("<option value='cast'>Cast Away</option>");
										pw.print("<option value='asians'>Crazy Rich asians</option>");
										pw.print("<option value='john'>Dear John</option>");
										pw.print("<option value='gump'>Forest Gump</option>");
										pw.print("<option value='lala'>Lala Land</option>");
										pw.print("<option value='onday'>One Day</option>");
										pw.print("<option value='sick'>Big Sick</option>");
										pw.print("<option value='blue'>The Blue VAlentine</option>");
										pw.print("<option value='fault'>The Fault in our stars</option>");
										pw.print("<option value='god'>The godfather</option>");
										pw.print("<option value='matrix'>The Matrix</option>");
										pw.print("<option value='shawshank'>The SHawshank Redemption</option>");

                                pw.print("</td>");
			pw.print("<tr>");
			     pw.print("<td> <input type='checkbox' name='queryCheckBox' value='productPrice'> Select </td>");
                                pw.print("<td> Product Price: </td>");
                              pw.print(" <td>");
                                  pw.print("  <input type='number' name='productPrice' value = '0' size=10  /> </td>");
						pw.print("<td>");
					pw.print("<input type='radio' name='comparePrice' value='EQUALS_TO' checked> Equals <br>");
					pw.print("<input type='radio' name='comparePrice' value='GREATER_THAN'> Greater Than <br>");
					pw.print("<input type='radio' name='comparePrice' value='LESS_THAN'> Less Than");
					pw.print("</td></tr>");				
                            
  			
			pw.print("<tr><td> <input type='checkbox' name='queryCheckBox' value='reviewRating'> Select </td>");
                               pw.print(" <td> Review Rating: </td>");
                               pw.print(" <td>");
                                   pw.print(" <select name='reviewRating'>");
                                       pw.print(" <option value='1' selected>1</option>");
                                       pw.print(" <option value='2'>2</option>");
                                       pw.print(" <option value='3'>3</option>");
                                     pw.print("   <option value='4'>4</option>");
                                      pw.print("  <option value='5'>5</option>");
                                pw.print("</td>");
				pw.print("<td>");
				pw.print("<input type='radio' name='compareRating' value='EQUALS_TO' checked> Equals <br>");
				pw.print("<input type='radio' name='compareRating' value='GREATER_THAN'> Greater Than"); 
			pw.print("</td></tr>");
			
			// pw.print("<tr>");
			// 					pw.print("<td> <input type='checkbox' name='queryCheckBox' value='retailerCity'> Select </td>");
            //                     pw.print("<td> Retailer City: </td>");
            //                     pw.print("<td>");
            //                     pw.print("<input type='text' name='retailerCity' /> </td>");
								
            //                 pw.print("</tr>");
							
							 pw.print("<tr>");
								pw.print("<td> <input type='checkbox' name='queryCheckBox' value='retailerZipcode'> Select </td>");
                               pw.print(" <td> Retailer Zip code: </td>");
                               pw.print(" <td>");
                                    pw.print("<input type='text' name='retailerZipcode' /> </td>");
					        pw.print("</tr>");
				pw.print("<tr><td>");
					pw.print("<input type='checkbox' name='extraSettings' value='GROUP_BY'> Group By");
								pw.print("</td>");
								pw.print("<td>");
								pw.print("<select name='groupByDropdown'>");
                               // pw.print("<option value='GROUP_BY_CITY' selected>City</option>");
                                pw.print("<option value='GROUP_BY_PRODUCT'>Product Name</option>");                                        
                                pw.print("</td><td>");
								pw.print("<input type='radio' name='dataGroupBy' value='Count' checked> Count <br>");
								pw.print("<input type='radio' name='dataGroupBy' value='Detail'> Detail <br>");
								pw.print("</td></tr>");
								
									
			
						pw.print("<tr>");
                                pw.print("<td colspan = '4'> <input type='submit' value='Find Data' class='btnbuy' /> </td>");
                            pw.print("</tr>");
							
							
		pw.print("</table>");	
		pw.print("</div></div></div>");			
		utility.printHtml("Footer.html");
	
	
	
			
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
