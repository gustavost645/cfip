package open.digytal.util;

import org.hsqldb.util.DatabaseManagerSwing;

public class StartHSQLDB {
	static String FILE_URL="file:/porgamador/cfip/database/cfipdb";
	public static void main(String[] args) {
		local();		
		//server();
	}
	static void local() {
		final String[] dbArgs = { "--user", "sa", "--password", "", "--url", "jdbc:hsqldb:" +FILE_URL };
		DatabaseManagerSwing.main(dbArgs);
		
	}
	static void server() {
		final String[] dbArg = {"--database.0", FILE_URL, "--dbname.0", "database","--port","5454"};
		org.hsqldb.server.Server.main(dbArg);
		/*final String[] dbArgsServer = { "--url", "jdbc:hsqldb:hsql://localhost:5454/database" };
		DatabaseManagerSwing.main(dbArgsServer);*/
	}	
}