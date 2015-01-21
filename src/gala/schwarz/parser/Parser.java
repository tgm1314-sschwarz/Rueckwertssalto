package gala.schwarz.parser;

import gala.schwarz.connection.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.cli.*;

/**
 * Klasse, die options erstellt und die usereingabe nach diesen parametern überprüft
 * 
 * @author Schwarz Stephan
 * @author Gala Mateusz
 * @version 15-01-2015
 */
public class Parser {
	private Connector cn = new Connector();
	
	public void parse(String [] args) throws ParseException, SQLException{
		Options options = new Options();
		
		//erstellen der Options
		options.addOption("h", true, "hostname");
		options.addOption("u", true, "username");
		options.addOption("p", true, "password");
		options.addOption("d", true, "database");
		
		CommandLineParser parser = new BasicParser();
		CommandLine cmd = parser.parse(options, args);
		
		System.out.println("---------------------------------------------");
		
		//Überprüfen der Options
		if(cmd.hasOption("h")) {
			System.out.println("hostname: 		" + cmd.getOptionValue("h"));
			this.cn.setHostname(cmd.getOptionValue("h"));
		} else {
			System.out.println("hostname: 		Localhost");
			this.cn.setHostname(("Localhost"));
		} 
		if(cmd.hasOption("u")) {
			System.out.println("username: 		" + cmd.getOptionValue("u"));
			this.cn.setUsername(cmd.getOptionValue("u"));
		} else {
			System.out.println("username: 		" + System.getProperty("user.name"));
			this.cn.setUsername("");
		}
		if(cmd.hasOption("p")) {
			System.out.println("password: 		" + cmd.getOptionValue("p"));
			this.cn.setPassword(cmd.getOptionValue("p"));
		} else {
			System.out.println("password: 		");
			this.cn.setPassword("");
		}
		if(cmd.hasOption("d")) {
			System.out.println("database: 		" + cmd.getOptionValue("d"));
			this.cn.setDatabase(cmd.getOptionValue("d"));
		} else {
			System.out.println("database: 		");
			this.cn.setDatabase("");
		}
		
		System.out.println("---------------------------------------------");
		
		this.cn.connect();
		this.cn.disconnect();
	}
}
