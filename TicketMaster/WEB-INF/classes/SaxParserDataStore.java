import org.xml.sax.InputSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import  java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SaxParserDataStore extends DefaultHandler {
    Comedy com;
    Horror horror;
    Romantic rom;
	
	String ProductType;
	String Id;
	String productName;
	Double productPrice;
	String productImage;
	String productManufacturer;
	// String productCondition;
	// Double productDiscount;
	String runtime;
	String summary;
	String manufacturerRebate;
	String quantity = "10"; 

    
    static HashMap<String,Comedy> coms;
    static HashMap<String,Horror> horrors;
	static HashMap<String,Romantic> roms;
    
    String tvXmlFileName;

    String elementValueRead;
	String currentElement="";

    public SaxParserDataStore()
	{
	}

	public SaxParserDataStore(String tvXmlFileName) 
	{
    	this.tvXmlFileName = tvXmlFileName;

    	coms = new HashMap<String, Comedy>();
		horrors=new  HashMap<String, Horror>();
		roms=new  HashMap<String, Romantic>();

		parseDocument();
    }

   //parse the xml using sax parser to get the data
 
	private void parseDocument() 
	{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
		{
	    SAXParser parser = factory.newSAXParser();
	    parser.parse(tvXmlFileName, this);
		
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
	}

   

////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document. 
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.  
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////
	
	// when xml start element is parsed store the id into respective hashmap for console,games etc 
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("com")) 
		{
			currentElement="com";
			com = new Comedy();
			com.setId(attributes.getValue("id"));
			//ProductType = "Comdey";
			Id = attributes.getValue("id");
		}
		if (elementName.equalsIgnoreCase("hor")) 
		{
			currentElement="hor";
			horror = new Horror();
			horror.setId(attributes.getValue("id"));
			//ProductType = "horror";
			Id = attributes.getValue("id");
		}
		if (elementName.equalsIgnoreCase("rom")) 
		{
			currentElement="rom";
			rom = new Romantic();
			rom.setId(attributes.getValue("id"));
			//ProductType = "rom";
			Id = attributes.getValue("id");
		}

    }
	// when xml end element is parsed store the data into respective hashmap for ,games etc respectively
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException 
    {
 
        if (element.equals("com")) 
        {
			coms.put(com.getId(),com);
			return;
        }
        if (element.equals("hor")) 
        {
			horrors.put(horror.getId(),horror);
			return;
        }
        if (element.equals("rom")) 
        {
			roms.put(rom.getId(),rom);
			return;
        }
        
        if (element.equalsIgnoreCase("image")) 
        {
		    if(currentElement.equals("com")){
				com.setImage(elementValueRead);
				productImage = elementValueRead;
				System.out.println("com"+elementValueRead);
			}
				
			if(currentElement.equals("hor"))
			{
				horror.setImage(elementValueRead);
				productImage = elementValueRead;
				System.out.println("hor"+elementValueRead);
			}

			if(currentElement.equals("rom"))
			{
				rom.setImage(elementValueRead);
				productImage = elementValueRead;
				System.out.println("rom"+elementValueRead);
			}         
			return;
        }


		if (element.equalsIgnoreCase("runtime"))
		 {
            if(currentElement.equals("com")){
				com.setRuntime(elementValueRead);
				runtime = elementValueRead;
			}
			if(currentElement.equals("hor"))
			{
				horror.setRuntime(elementValueRead);
				runtime = elementValueRead;
			}
			if(currentElement.equals("rom"))
			{
				rom.setRuntime(elementValueRead);
				runtime = elementValueRead;
			}       
			return;  
		}

		if (element.equalsIgnoreCase("summary"))
		 {
            if(currentElement.equals("com")){
				com.setSummary(elementValueRead);
				summary = elementValueRead;
			}
			if(currentElement.equals("hor"))
			{
				horror.setSummary(elementValueRead);
				summary = elementValueRead;
			}
			if(currentElement.equals("rom"))
			{
				rom.setSummary(elementValueRead);
				summary = elementValueRead;
			}       
			return;  
		}

		if (element.equalsIgnoreCase("manufacturer"))
		 {
			if(currentElement.equals("com"))
			{
				com.setRetailer(elementValueRead);
				productManufacturer = elementValueRead;
			}
        	if(currentElement.equals("hor"))
				horror.setRetailer(elementValueRead);
			if(currentElement.equals("rom"))
				rom.setRetailer(elementValueRead);

			return;
		}

        if (element.equalsIgnoreCase("name")) 
        {
			if(currentElement.equals("com"))
			{
				com.setName(elementValueRead);
				productName = elementValueRead;
			}
        	if(currentElement.equals("hor"))
				horror.setName(elementValueRead);
			if(currentElement.equals("rom"))
				rom.setName(elementValueRead);
			return;
	    }
	
        if(element.equalsIgnoreCase("price"))
        {
			if(currentElement.equals("com"))
			{
				com.setPrice(Double.parseDouble(elementValueRead));
				productPrice = Double.parseDouble(elementValueRead);
			}
        	if(currentElement.equals("hor"))
				horror.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("rom"))
				rom.setPrice(Double.parseDouble(elementValueRead));         
			return;
		}

		// if (element.equalsIgnoreCase("discount")) {
        //     if(currentElement.equals("com")){
		// 		com.setDiscount(Double.parseDouble(elementValueRead));
		// 		productDiscount = Double.parseDouble(elementValueRead);
		// 	}
				
		// 	if(currentElement.equals("hor"))
		// 		horror.setDiscount(Double.parseDouble(elementValueRead));
		// 	if(currentElement.equals("rom"))
		// 		rom.setDiscount(Double.parseDouble(elementValueRead));
                      
		// 	return;
		// }
		
		// MySqlDataStoreUtilities.Insertproducts(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,quantity);

	}
	//get each element in xml tag
    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }


    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////
	
//call the constructor to parse the xml and get product details
 public static void addHashmap() {
		String TOMCAT_HOME = System.getProperty("catalina.home");	
		new SaxParserDataStore(TOMCAT_HOME+"\\webapps\\TicketMaster\\ProductCatalog.xml");
    } 
}
