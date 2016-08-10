/*
 * CLIcode.java
 * All the code needed to run the CLI for codeufinalproject (ddSearch)
 * Created by Lindsey Gillaspie, August 2016
 */

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

public class CLIcode {

  /*
   * HelperPrint()
   * Helper method that is called if the user types "h" as an option
   * The options for the CLI are printed
   */
  public static void HelperPrint() throws IOException{
    OptionParser parser = new OptionParser() {
      {
	accepts( "n" ).withRequiredArg().ofType( Integer.class ).describedAs( "number of results" ).defaultsTo( 1 );
	accepts( "q" ).withRequiredArg().ofType( Integer.class ).describedAs( "rank percentage" ).defaultsTo(1);
	accepts( "w" ).withRequiredArg().ofType( Integer.class ).describedAs( "minimum # of words in article" ).defaultsTo(1);

	accepts( "h" , "show help" ).forHelp();
	accepts( "e" , "want to see pages with errors?" ).withRequiredArg().ofType( String.class ).describedAs( "y or n" );
	accepts( "l" ).withRequiredArg().ofType( String.class ).describedAs( "language" );
        accepts( "s" , "want to see summaries?" ).withRequiredArg().ofType( String.class ).describedAs( "y or n" );
      }
    };
    parser.printHelpOn( System.out );
   } //end of HelperPrint
  
  /*
   * OptionsChosen()
   * Finds which options were chosen, then executes the appropriate tasks
   */
   public static void OptionsChosen(OptionSet options) throws IOException{
	// Print out the help menu
	if(options.has("h")){
	  System.out.println("h");
	  HelperPrint();
	}
	// Set the number of results (I'm Feeling Lucky would be 1)
	if(options.has("n")){
	  System.out.println("-n was chosen");
	}
	// Set the lowest rank percentage the user is accepting
	if(options.has( "q" )){
	  System.out.println("-q was chosen");
	}			
	// Set the minimum word count of the articles to return
	if(options.has("w")){
	  System.out.println("-w was chosen");
	}
	// Whether or not the user wants to accept links with Wikipedia errors
	if(options.has("e")){
	  System.out.println("-e was chosen");
	}
	// Which language the user wants to read in
	if(options.has("l")){
	  System.out.println("-l was chosen");
	}
	// Whether or not the user wants a summary of the link
	if(options.has("s")){
	  System.out.println("-s was chosen");
	}

   } //end of OptionsChosen()


    public static void main( String[] args ) throws Exception{
       	// Part 1: Determine what options the user started program with and adjust to them

	OptionParser p = new OptionParser("n::q::w::h::e::l::s::");
	OptionSet options = p.parse(args);
	OptionsChosen(options);
	
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
	  String temp = "http://52.3.149.50:8080/search?q="+text;
	  //String temp = "http://qartis.com/test.json"+text;
	  temp = temp.replaceAll(" ", "%20");
          URL wikiRequest = new URL(temp);

	  Scanner wikiScanner = new Scanner(wikiRequest.openStream());
          String result = wikiScanner.useDelimiter("\\Z").next();
	  JSONArray json = new JSONArray(result);
	  wikiScanner.close();
	  
	  //Iterate over json object (id, url, title, summary, timestamp)
	  //Handle every error if one+ is empty

	  // String[] data = new String[]{"id", "url", "title", "summary", "timestamp", "score"};
	  StringBuffer sb = new StringBuffer("\n");
	  sb.append("Results: ");
	  sb.append("\n");
	  // Print out each value for each key
	  for(int m = 0; m < json.length(); m++)
	  {
	    JSONObject jsonObject = json.getJSONObject(m);
	    //sb.append(jsonObject.getString("title"));
	    //sb.append("\n");
	    //sb.append(jsonObject.getString("summary"));
	    //sb.append("\n");
	    //sb.append(jsonObject.getString("timestamp"));
	    //sb.append("\n");
	    if(jsonObject.getString("url") != null){
	      sb.append(jsonObject.getString("url"));
	      sb.append("\n");
	    } 
	    sb.append(jsonObject.getInt("score"));
	    sb.append("\n");
	    sb.append("\n");
	  }

	  System.out.println(sb.toString());

	  System.out.println("Please input the keyword(s) to search for: ");
	  text = input.nextLine();	 
	}
	while (!text.equals("exit"));

	System.out.println("Thank you for using ddSearch.");

  } //end of main
} //end of CLIcode class
