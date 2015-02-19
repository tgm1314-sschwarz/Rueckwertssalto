package SchwarzGala.creator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.jar.JarFile;

import SchwarzGala.outputs.cpJSDirectory;
import SchwarzGala.outputs.file;
import SchwarzGala.outputs.html;

/**
 * Eine Klasse die aus übergebenen MetaDaten einer Datenbank ein Entity-Relationship Model erstellt
 * 
 * @author Schwarz Stephan
 * @author Gala Mateusz
 * @version 15-02-2015
 */
public class createERM {
	private String [] feld = null;
	private String [] pk = null;
	private String [] pkt = null;
	private String color = "blue";
	private String figure = "Cube1";
	
	private html html;
	private file f;
	private cpJSDirectory cp;
	
	private boolean pktf = false;
	private boolean fktf = false;
	
	String output="";
	
	public createERM(String dbname, ArrayList tables, ArrayList att, ArrayList foreignKey, ArrayList primaryKey, ArrayList pktable, ArrayList fktable){
		output = "var nodeDataArray;\n"
				+ "var linkDataArray;\n\n";
		output += "function getDBName() { \n	return '" + dbname + "';\n}\n\n";
		
		
		//anfang funktion für Entities, anfang nodeDataArray
		output += "function createEntities() {\n	nodeDataArray = [\n";
		for(int i = 0; i < att.size(); i++){
			//anfang jeder zeile
			output += "	  { key: '" + tables.get(i) + "',\n		items: [\n";
			
			//String[]'s füllen
			pk = ((String) primaryKey.get(i)).split(" ");
			((String) foreignKey.get(i)).split(" ");
			feld = ((String) att.get(i)).split(" ");
			
			for(int j = 0 ;  j  < feld.length; j++){
				//primary Keys
				for(int k = 0; k < pk.length; k++){
					if(feld[j].equals(pk[k])){
						pktf = true;
						color = "red";
						figure = "Decision";
					}
				}
				
				//jede zeile mit dem Attribut befüllen
				output += "			{ name: '"+feld[j]+"', iskey: "+pktf+", figure: '"+figure+"', color: '"+color+"' }";
				
				//reset falls pk
				if(pktf){
					pktf = false;
					color = "blue";
					figure = "Cube1";
				}
				
				//überprüfen ob es das letzte attribut pro tabelle ist, wenn nein + ", "
				if(j != feld.length - 1){
					output += ",\n";
				}else{
					output += "\n";
				}
			}
			
			//ende jeder zeile
			output += "	  ] },\n\n";
		}
		output += "	];\n	return nodeDataArray;\n}\n\n";
	
		
		//anfang funktion für Relationships
		output += "function createRelationships() {\n	linkDataArray = [\n";
		for(int i=0;i < tables.size();i++){
			pkt = ((String) pktable.get(i)).split(" ");
			((String) fktable.get(i)).split(" ");
			
			if(!pktable.get(i).equals("")){
				for(int j=0;j < pkt.length; j++){
					//überprüft ob eine tabelle von einer anderen mehr als einen FK bezieht, damit trotzdem nur eine Relation gezeichnet wird
					if(!(j+1==pkt.length)){		//gegen outofbounds exception
						if(pkt.length>1 && (pkt[j].equals(pkt[j+1]))){
							pkt[j] = "";		//löscht den ersten eintrag welcher doppelt vorkommt
						}
					}
					//die zeilen
					output += "	  { from: '" + tables.get(i) + "', to: '" + pkt[j] + "', text: 'N', toText: '1' },\n";
				}
			}
			//letzte zeile
			if(i+1==tables.size()){
				output += "	  { from: '', to: '', text: '', toText: '' }\n";
			}
		}
		output += "	];\nreturn linkDataArray;\n}";
		
		//ausgabe in ein file;
		html = new html(false, dbname);
		
		cp = new cpJSDirectory();
		JarFile defaultJar = null;
		cpJSDirectory.jarForClass(getClass(), defaultJar);
		
		JarFile fromJar;
		try {
			fromJar = new JarFile("Rueckwertssalto.jar");;
			cpJSDirectory.copyResourcesToDirectory(fromJar, "js", "js/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		f = new file(false, output, html.gethtml(), dbname);
	}
}