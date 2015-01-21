package gala.schwarz.creator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Eine Klasse die aus übergebenen MetaDaten einer Datenbank ein Relationenmodell erstellt
 * 
 * @author Schwarz Stephan
 * @author Gala Mateusz
 * @version 15-01-2015
 */
public class createRM {
	private String [] feld = null;
	private String [] pk = null;
	private String [] fk = null;
	private String [] fkt = null;
	
	private boolean pktf =false;
	private boolean fktf =false;
	
	private File f;
	private FileWriter w;
	
	String ausgabe="";
	
	public void createRM(String dbname, ArrayList tables,ArrayList att, ArrayList foreignKey, ArrayList primaryKey, ArrayList fktable){
		//erstellung des html dokuments (valide)
		ausgabe = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'><html><head><title>RM-generator v1.1</title></head><body>";
		ausgabe += "<div id='header' style='border-bottom: 2px solid black;'><h2>RM - " + dbname + "</h2></div><br />";
		ausgabe += "<div id='model' style='font-size: 16px; font-family: Courier New;'><table border='0'>";
		
		
		
		for(int i = 0; i < att.size(); i++){
			//anfang jeder zeile
			ausgabe += "<tr><td style='font-weight: bold;'>" + tables.get(i) + "</td><td>(";
			
			//String[]'s füllen
			pk = ((String) primaryKey.get(i)).split(" ");
			fk = ((String) foreignKey.get(i)).split(" ");
			feld = ((String) att.get(i)).split(" ");
			fkt = ((String) fktable.get(i)).split(" ");
			
			//alle felder nach fk und pk überprüfen
			for(int j = 0 ;  j  < feld.length; j++){			

				//primary Keys
				for(int k = 0; k < pk.length; k++){
					if(feld[j].equals(pk[k])){
						ausgabe += "<u>";
						pktf = true;
					}
				}

				//foreign Keys
				for(int k = 0; k < fk.length; k++){
					if(feld[j].equals(fk[k])){
						ausgabe += "<i>" + fkt[k] + ".";
						fktf = true;
					}
				}
				
				// attribut einfügen
				ausgabe += feld[j];
				
				// falls pk oder fk, unterstreichen bzw. kursiv schreiben
				if(fktf){
					ausgabe += "</i>";
					fktf = false;
				}
				if(pktf){
					ausgabe += "</u>";
					pktf = false;
				}
				//überprüfen ob es das letzte attribut pro tabelle ist, wenn nein + ", "
				if(j != feld.length - 1){
					ausgabe += ", ";
				}
			}
			//ende jeder zeile
			ausgabe+=")</td></tr>";
		}
		
		//html dokument beenden
		ausgabe += "</div></body></html>";
		
		//file erstellen und füllen
		f =  new File("C:/Users/d4rkor3/Desktop/RM-" + dbname + ".html");
		f.delete();
		w = null;
		try {
			w = new FileWriter(f, true);
			w.write(ausgabe);
			w.flush();
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
