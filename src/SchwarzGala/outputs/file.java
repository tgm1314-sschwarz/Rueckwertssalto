package SchwarzGala.outputs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Eine Klasse die übergebene Strings in ein file ausgibt
 * 
 * @author Schwarz Stephan
 * @author Gala Mateusz
 * @version 17-02-2015
 */
public class file {
	private File f;
	private File f2;
	private FileWriter w = null;
	private FileWriter w2 = null;
	
	String output = "";
	String output2 = "";
	String dbname = "";
	
	public file(boolean RMorERM, String output, String output2, String dbname){
		this.output = output;
		this.output2 = output2;
		this.dbname = dbname;
		
		if(RMorERM){
			createFileRM();
		} else {
			createFileERM();
		}
	}

	/**
	 * erstellt das file für das RM	(RM - dbname.html)
	 */
	public void createFileRM(){
		f = new File("RM - " + dbname + ".html");
		
		f.delete();
		w = null;
		try {
			w = new FileWriter(f, true);
			w.write(output);
			w.flush();
			w.close();
		} catch (IOException e) {
			System.err.println("Couldn't create File for RM");
		}
	}
	
	/**
	 * erstellt die beiden files für das ERM	(ERM.js, ERM - dbname.html)
	 */
	public void createFileERM(){
		f = new File("js/ERM.js");
		f2 = new File("ERM - " + dbname + ".html");
		
		f.delete();
		f2.delete();
				
		w = null;
		w2 = null;
		try {
			w = new FileWriter(f, true);
			w.write(output);
			w.flush();
			w.close();
			w2 = new FileWriter(f2, true);
			w2.write(output2);
			w2.flush();
			w2.close();
		} catch (IOException e) {
			System.err.println("Couldn't create File for ERM");
		}
	}
}