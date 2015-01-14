package gala.schwarz.connection;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;

import com.mysql.jdbc.*;

/**
 * Klasse, die eine Verbindung anhand der parameter aufbaut, 
 * eine Query anhand der parameter definiert und ausführt
 * und das resultSet ausließt und entweder ausgibt oder in ein file speichert.
 * @author schwarz
 * @version 03.01.2015
 */
public class Connector {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = null;
	private String[] fieldSeperator;
	private String sd = "ASC";
	private String field = "";
	private String table = "";
	private String query= "";
	private String sc = "";
	private String filter = "";
	private String sfeld = "";
	private String file = "";
	
	public Connector(){
		this.ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
	}
	
	
	/**
	 * baut eine Verbindung auf
	 */
	public void connect() {
		try {
			con = (Connection) ds.getConnection();
		} catch (SQLException e) {
			System.err.println("--connection error");
		}
		try {
			st = (Statement) con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY );
		} catch (SQLException e) {
			System.err.println("--statement error");
		}
	}
	/**
	 * erstellt die Query
	 */
	public void createQuery() {
		this.fieldSeperator = field.split(",");
		this.query ="SELECT ";
		
		for(int i = 0; i < fieldSeperator.length;){
			this.query += fieldSeperator[i];
			i++;
			if(i < fieldSeperator.length){
				this.query+=", ";
			}
		}
		
		this.query += " FROM " + this.table;
		
		if(filter.equals("nofilter")){}else{
			this.query += " WHERE " + this.filter;
		}
		if(sfeld != ""){
			this.query += " ORDER BY " + this.sfeld + " " +this.sd; 
		}
	}
	/**
	 * führt die Query aus
	 */
	public void executeQuery() {
		System.out.println("---------------------------------------------");
		System.out.println(query);
		try {
			rs = st.executeQuery(this.query);
		} catch (SQLException e) {
			System.err.println("--query contains errors");
		}
	}
	/**
	 * ließt aus dem resultSet das Ergebnis aus, und gibt dieses aus
	 * @throws SQLException 
	 */
	public void read() throws SQLException {
		String ausgabe = "";
		FileWriter writer = null;
		if(file.equals("nofile")){}else{
			File f = new File(this.file);
			f.delete();
			try {
				writer = new FileWriter(f, true);
			} catch (IOException e) {
				System.err.println("--couldnt create file");
			}
		}
		
		ResultSetMetaData rsmd = rs.getMetaData();
		System.out.println(rsmd.getColumnCount() + " Columns");
		
		
		// Ergebnisse verarbeiten
		try {
			while (rs.next()) { // Cursor bewegen
				for(int i = 0; i < fieldSeperator.length; ){
					ausgabe += " " + rs.getObject(fieldSeperator[i]);
					i++;
					if(i < fieldSeperator.length){
						ausgabe += " " + rsmd.getColumnTypeName(i) + this.sc + " ";
					}
				}
				ausgabe+="\n";
			}
		} catch (SQLException e) {
			System.err.println("--resultSet couldnt be read");
		}
		
		if(file.equals("nofile")){
			System.out.println(ausgabe);
		}else{
			try {
				writer.write(ausgabe);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				System.err.println("--couldnt write into file");
			}
		}
	}
	/**
	 * Methode zum aufräumen nachdem der User fertig ist
	 */
	public void disconnect() {
		try {
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("--disconnecting error");
		}
	}
	
	
	/**
	 * set-Methoden für die eingegebenen Argumente
	 * @param ip, un, pw, db, sd, f, t, sc, filter, sfeld...die angegebenen Parameter
	 */
	public void setHostname(String ip){
		ds.setServerName(ip);
	}
	public void setUsername(String un){
		ds.setUser(un);
	}
	public void setPassword(String pw){
		ds.setPassword(pw);
	}
	public void setDatabase(String db){
		ds.setDatabaseName(db);
	}
	public void setSortdirection(String sd){
		if(sd.equals("ASC")||sd.equals("DESC")){
			this.sd = sd;
		}else{
			System.err.println("--Error in sortdirection-input");
		}
	}
	public void setField(String f){
		this.field = f;
	}
	public void setTable(String t){
		this.table = t;
	}
	public void seperateColumn(String sc){
		this.sc = sc;
	}
	public void setFilter(String filter){
		this.filter = filter;
	}
	public void setSort(String sfeld){
		this.sfeld = sfeld;
	}
	public void setFile(String file) {
		this.file = file;
	}
}


