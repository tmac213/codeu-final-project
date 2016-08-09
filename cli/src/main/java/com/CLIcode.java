import java.io.File;
import java.io.*;
import static java.io.File.*;
import static java.util.Arrays.*;
import java.util.Scanner;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

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
	 
	  System.out.println("Please input the keyword(s) to search for: ");
	  text = input.nextLine();
	}
	while (!text.equals("exit"));

	System.out.println("Thank you for using ddSearch.");

  } //end of main
} //end of CLIcode class
