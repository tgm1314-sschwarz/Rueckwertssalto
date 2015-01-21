package gala.schwarz.test;

import gala.schwarz.connection.*;
import gala.schwarz.parser.*;
import java.sql.SQLException;
import org.apache.commons.cli.ParseException;

public class test_eclipse {
	public static void main(String[] args) {
		Connector c = new Connector();
		
		Parser p = new Parser();
		
		try {
			try {
				p.parse(args);
			} catch (SQLException e) {
				System.err.println("--connection error");
			}
		} catch (ParseException e) {
			System.err.println("--parsing error");
		}
	}
}