package gala.schwarz.connection;

import gala.schwarz.creator.*;
import java.sql.*;
import java.util.ArrayList;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Klasse die eine Verbindung zu eine Datenbank herstellt und MetaDaten ausließt
 * 
 * @author Schwarz Stephan
 * @author Gala Mateusz
 * @version 15-01-2015
 */
public class Connector {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private ResultSet resultTables;
	private createRM rm;
	private com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = null;
	
	private String dbname= "", s="", s2="";
	private ArrayList <String> att = new ArrayList <String>();
	private ArrayList <String> tables = new ArrayList <String>();
	private ArrayList <String> pk = new ArrayList <String>();
	private ArrayList <String> fk = new ArrayList <String>();
	private ArrayList <String> fktable = new ArrayList <String>();
	
	
	public Connector(){
		this.ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
	}

	/**
	 * Verbindet mit einer Datenbank und ließt MetaDaten aus
	 * @throws SQLException
	 * @throws IOException 
	 */
	public void connect() throws SQLException{
		con = (Connection) ds.getConnection();
		st = (Statement) con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
		rs = null;
		
		String tabnames = "";
		DatabaseMetaData md = con.getMetaData();
		
		resultTables = md.getTables(null, null, "%", null);
		while (resultTables.next()) {
			dbname = resultTables.getString(1);			//datenbank name
			tabnames=resultTables.getString(3);			//alle tabellennamen
			tables.add(tabnames);						//arraylist mit tabellennamen füllen
			rs = st.executeQuery("SELECT * from " + tabnames);
			
			
			//alle attribute für jede tabelle durchgehen
			for(int i=1; i <= rs.getMetaData().getColumnCount(); i++){
				s += (rs.getMetaData().getColumnName(i) + " ");	
			}
			att.add(s);		//arraylist mit attributen füllen
			s = "";
			
			
			//foreign keys auslesen
			rs = md.getImportedKeys(null, null, tabnames);
			while (rs.next()) {
				s2+=rs.getString("PKTABLE_NAME") + " ";			//tabellenname der tabelle von der man den fk bezieht
				s+=rs.getString("FKCOLUMN_NAME" ) + " ";		//name des fk
			}
			fktable.add(s2);		//arraylist mit foreign keys sortiert nach tabellen füllen
			fk.add(s);				//arraylist mit foreign keys füllen
			s = "";
			s2 = "";
			
			
			//primary keys auslesen
			rs = md.getPrimaryKeys("", "", tabnames);
			while (rs.next()) {
				s+=rs.getString("COLUMN_NAME") + " ";		//name des pk
			}
			pk.add(s);		//arraylist mit primay keys füllen
			s="";
		}
		
		rm = new createRM();
		rm.createRM(dbname, tables, att, fk, pk, fktable);
	}
	
	
	/**
	 * Disconnected sich von der Datenbank
	 * @throws SQLException
	 */
	public void disconnect() throws SQLException{
		resultTables.close(); rs.close(); st.close(); con.close();
	}
	
	
	/**
	 * set methoden für hostname, username, password und database
	 * @param hostname
	 * @param username
	 * @param password
	 * @param database
	 */
	public void setHostname(String ip){
		ds.setServerName(ip);
	}
	public void setUsername(String bn){
		ds.setUser(bn);
	}
	public void setPassword(String pw){
		ds.setPassword(pw);
	}
	public void setDatabase(String db){
		ds.setDatabaseName(db);
	}
}


