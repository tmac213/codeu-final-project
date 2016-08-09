import java.io.File;

import static java.io.File.*;
import static java.util.Arrays.*;

import joptsimple.OptionParser;

// Helper method that is called if the user types "help"
// The options for the CLI are printed
//public class HelperPrint{
	
//} //end of HelperPrint
public class CLIcode {
public static void main( String[] args ){
	OptionParser p = new OptionParser("n::q::w::");
    	
	p.accepts("help");
    //    p.accepts("pass").requiredIf("ftp").withRequiredArg();
	     
	OptionSet options = p.parse(args);
	if(options.has("help")){
		System.out.println("");
		//HelperPrint();
	}

	if(options.has("n")){
		System.out.println("-n exists");
	}
	
	if(opt.has( "q" )){
		System.out.println("-q exists");
        }
							     
	if(opt.has("w")){
		System.out.println("-w exists");		
	}

} //end of main
}
