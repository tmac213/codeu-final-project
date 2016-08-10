import java.io.File;
import java.io.*;
import static java.io.File.*;
import static java.util.Arrays.*;
import java.util.Scanner;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

// Helper method that is called if the user types "help"
// The options for the CLI are printed
//public void HelperPrint{
	
//} //end of HelperPrint

public class CLIcode {
    public static void main( String[] args ) throws IOException{
       	// Part 1: Determine what options the user started program with and adjust to them

	OptionParser p = new OptionParser("n::q::w::h::");
	OptionSet options = p.parse(args);
	
	// Print out the help menu
        if(options.has("h")){
	  System.out.println("h");
          //HelperPrint();
        }
	// Set the number of results
	if(options.has("n")){											                    System.out.println("-n exists");
	}			
	// Set the lowest rank percentage the user is accepting
	if(options.has( "q" )){													    System.out.println("-q exists");
	}
	// Set the minimum word count of the articles to return
	if(options.has("w")){
	    System.out.println("-w exists");
	}

	// Part 2: Ask user for the keywords they want to search for & search

	String text = "";
	
	Scanner input = new Scanner(System.in);
	
	System.out.println("Welcome to ddSearch.");
	System.out.println("Please input the keyword(s) with options you want to search for: ");
	text = input.nextLine();
	
	do
	{
	  int i = 0;
	  String [] input_list = text.split(" ");
	
 	  // Build the http URL to retrieve the result
	  //String temp = "http://52.3.149.50:8080/search?q="+text;
	   String temp = "http://qartis.com/test.json"+text;
	  temp = temp.replaceAll(" ", "%20");
          //String requestURL = "http://qartis.com/test.json";
	  URL wikiRequest = new URL(temp);

	  Scanner wikiScanner = new Scanner(wikiRequest.openStream());
          String result = wikiScanner.useDelimiter("\\Z").next();
	  JSONArray json = new JSONArray(result);
	  wikiScanner.close();
	  
	  //Iterate over json object (id, url, title, summary, timestamp)
	  //Handle every error if one+ is empty

	  //System.out.println(json.toString());
	 // System.out.println(json[0].url);
	  String[] data = new String[]{"id", "url", "title", "summary", "timestamp"};
	  StringBuffer sb = new StringBuffer("Results: ");
	  sb.append("\n");
	  for(int m = 0; m <json.length(); m++)
	  {
	    JSONObject jsonObject = json.getJSONObject(m);
	    sb.append(jsonObject.getString("title"));
	    sb.append("\n");
	    sb.append(jsonObject.getString("summary"));
	    sb.append("\n");
	    sb.append(jsonObject.getString("timestamp"));
	    sb.append("\n");
	    sb.append(jsonObject.getString("url"));
	    sb.append("\n");
	    sb.append("\n");
	  }

	  System.out.println(sb.toString());

	  /*for(int m = 0; m <json.length(); m++)
	  {
		JSONObject jsonObject = json.getJSONObject(m);
		for(String n : jsonObject.keys()) {
		  System.out.println("Key: " + n + "; Value: " + jsonObject.getString(n));
		}
	  }*/

	  System.out.println("Please input the keyword(s) to search for: ");
	  text = input.nextLine();	 
	}
	while (!text.equals("exit"));

	System.out.println("Thank you for using ddSearch.");

  } //end of main
} //end of CLIcode class
