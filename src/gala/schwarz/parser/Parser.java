package gala.schwarz.parser;

import gala.schwarz.connection.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.cli.*;

/**
 * Klasse, die options erstellt und die usereingabe nach diesen parametern überprüft
 * @author schwarz
 * @version 03.01.2015
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
		options.addOption("s", true, "sort");
		options.addOption("r", true, "sortdirection");
		options.addOption("w", true, "where");
		options.addOption("t", true, "seperate");
		options.addOption("f", true, "fields");
		options.addOption("o", true, "txt");
		options.addOption("T", true, "table");
		
		CommandLineParser parser = new BasicParser();
		CommandLine cmd = parser.parse(options, args);
		
		System.out.println("---------------------------------------------");
		
		//Überprüfen der Options
		if(cmd.hasOption("h")) {
			System.out.println("hostname		: " + cmd.getOptionValue("h"));
			this.cn.setHostname(cmd.getOptionValue("h"));
		} else {
			System.out.println("hostname		: Localhost");
			this.cn.setHostname(("Localhost"));
		} 
		if(cmd.hasOption("u")) {
			System.out.println("username		: " + cmd.getOptionValue("u"));
			this.cn.setUsername(cmd.getOptionValue("u"));
		} else {
			System.out.println("username		:" + System.getProperty("user.name"));
			this.cn.setUsername("");
		}
		if(cmd.hasOption("p")) {
			System.out.println("password		: " + cmd.getOptionValue("p"));
			this.cn.setPassword(cmd.getOptionValue("p"));
		} else {
			System.out.println("password		: ");
			this.cn.setPassword("");
		}
		if(cmd.hasOption("d")) {
			System.out.println("database		: " + cmd.getOptionValue("d"));
			this.cn.setDatabase(cmd.getOptionValue("d"));
		} else {
			System.out.println("no database selected");
			this.cn.setDatabase("");
		}
		if(cmd.hasOption("s")) {
			System.out.println("sort			: " + cmd.getOptionValue("s"));
			this.cn.setSort(cmd.getOptionValue("s"));
		} else {
			System.out.println("sort			:");
		}
		if(cmd.hasOption("r")) {
			System.out.println("sort direction		: " + cmd.getOptionValue("r"));
			this.cn.setSortdirection(cmd.getOptionValue("r"));
		} else {
			System.out.println("sort direction		: ASC ");
			this.cn.setSortdirection("ASC");
		}
		if(cmd.hasOption("w")) {
			System.out.println("where clause		: " + cmd.getOptionValue("w"));
			this.cn.setFilter(cmd.getOptionValue("w"));
		} else {
			System.out.println("where clause		:  ");
			this.cn.setFilter("nofilter");
		}
		if(cmd.hasOption("t")) {
			System.out.println("seperate column		: " + cmd.getOptionValue("t"));
			this.cn.seperateColumn(cmd.getOptionValue("t"));
		} else {
			System.out.println("seperate column		: '|' ");
			this.cn.seperateColumn("|");
		}
		if(cmd.hasOption("f")) {
			System.out.println("fields			: " + cmd.getOptionValue("f"));
			this.cn.setField(cmd.getOptionValue("f"));
		} else {
			System.err.println("--no field selected");
			System.exit(0);
		}
		if(cmd.hasOption("o")) {
			System.out.println("file name		: " + cmd.getOptionValue("o"));
			this.cn.setFile(cmd.getOptionValue("o"));
		} else {
			System.out.println("file name		:  ");
			this.cn.setFile("nofile");
		}
		if(cmd.hasOption("T")) {
			System.out.println("table			: " + cmd.getOptionValue("T"));
			this.cn.setTable(cmd.getOptionValue("T"));
		} else {
			System.err.println("--no table selected");
			System.exit(0);
		}
		
		this.cn.connect();
		this.cn.createQuery();
		this.cn.executeQuery();
		this.cn.read();
		this.cn.disconnect();
	}
}
