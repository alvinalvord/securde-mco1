package Controller;

import java.io.*;

public class Logger {
	
	public static final String path = "../logs/";
	
	public static void log (String error, String message) {
		System.out.println ("error: " + error + " has occurred.");
		System.out.println (message);
		try {
			String time = java.time.LocalDateTime.now ().toString ().replaceAll ("[^\\d\\w]", "_");
			System.out.println (time);
			String filename = path + time + "_" + error + ".txt";
			PrintWriter pw = new PrintWriter (new File (filename));
			pw.println (time);
			pw.println (error);
			pw.println (message);
			pw.close ();
		} catch (FileNotFoundException ex) {
			System.out.println ("File not found error @ logger");
			System.out.println ("Exiting program now");
			System.exit (0);
		}
	}
	
}