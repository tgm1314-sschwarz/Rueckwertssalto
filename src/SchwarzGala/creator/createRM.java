package SchwarzGala.creator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import SchwarzGala.outputs.file;
import SchwarzGala.outputs.html;

/**
 * Eine Klasse die aus übergebenen MetaDaten einer Datenbank ein Relationship Model erstellt
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
	
	private html html;
	private file f;
	
	private boolean pktf =false;
	private boolean fktf =false;
	
	String output="";
	
	public createRM(String dbname, ArrayList tables, ArrayList att, ArrayList foreignKey, ArrayList primaryKey, ArrayList fktable){
		html = new html(true, dbname);
		output = html.gethtml();
		
		for(int i = 0; i < att.size(); i++){
			//anfang jeder zeile
			output += "<tr><td style='font-weight: bold;'>" + tables.get(i) + "</td><td>(";
			
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
						output += "<u>";
						pktf = true;
					}
				}

				//foreign Keys
				for(int k = 0; k < fk.length; k++){
					if(feld[j].equals(fk[k])){
						output += "<i>" + fkt[k] + ".";
						fktf = true;
					}
				}
				
				// attribut einfügen
				output += feld[j];
				
				// falls pk oder fk, unterstreichen bzw. kursiv schreiben
				if(fktf){
					output += "</i>";
					fktf = false;
				}
				if(pktf){
					output += "</u>";
					pktf = false;
				}
				//überprüfen ob es das letzte attribut pro tabelle ist, wenn nein + ", "
				if(j != feld.length - 1){
					output += ", ";
				}
			}
			//ende jeder zeile
			output+=")</td></tr>\n";
		}
		
		//html dokument beenden
		output += "</table>\n</div>\n\n</body>\n</html>";
		
		//ausgabe in ein file
		f = new file(true, output, null, dbname);
	}
}
