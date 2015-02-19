package SchwarzGala.outputs;

/**
 * Eine Klasse die ein valides html dokument als String erzeugt
 * 
 * @author Schwarz Stephan
 * @author Gala Mateusz
 * @version 15-02-2015
 */
public class html {
	private String html = "";
	private String dbname;
	
	/**
	 * Erstellt ein valides HTML dokument
	 * 
	 * @param RMorERM		true=RM, false=ERM
	 * @param dbname		datenbank name für den header
	 */
	public html(boolean RMorERM, String dbname) {
		this.dbname = dbname;
		
		if(RMorERM){		//RM oder ERM
			createHTMLrm();
		} else {
			createHTMLerm();
		}
	}
	
	/**
	 * erstellt ein valides html dokument für das RM
	 */
	public void createHTMLrm(){
		html = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>\n<html>\n<head><title>generated RM</title></head>\n<body style='background-color: lightgrey;'>";
		html += "\n\n<div id='header' style='border-bottom: 2px solid black;'>\n<h2>RM - " + dbname + "</h2>\n</div>";
		html += "\n\n<div id='model' style='font-size: 16px; font-family: Courier New;'>\n<table border='0'>\n";
	}
	
	/**
	 * erstellt ein valides html dokument für das ERM
	 */
	public void createHTMLerm(){
		html = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>\n<html><head><title>generated-ERM</title></head>\n";
		html += "<!-- Copyright 1998-2015 by Northwoods Software Corporation. -->\n\n";
		html += "<body style='background-color: lightgrey;height: 100%;' onload='init()'>\n\n";
		html += "<script language='javascript' type='text/javascript' src='js/go-debug.js'></script>\n"
				+ "<script language='javascript' type='text/javascript' src='js/ERM.js'></script>\n"
				+ "<script language='javascript' type='text/javascript' src='js/layout.js'></script>\n\n";
		html += "<div id='header' style='border-bottom: 2px solid black;'>\n<h2>ERM - <script type='text/javascript'>document.write(getDBName());</script></h2>\n</div>\n\n";
        html += "<div id='ERM' style='background-color: lightgrey; border: solid 1px black; width: 100%; height: 880px'></div>\n</body>\n</html>";
	}
	
	/**
	 * get methode
	 * @return das valide html dokument
	 */
	public String gethtml() {
		return this.html;
	}
}
