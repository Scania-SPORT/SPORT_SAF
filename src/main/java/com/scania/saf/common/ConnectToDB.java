package com.scania.saf.common;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.scania.saf.log.SafLog;

 
/**
 * This program demonstrates how to establish database connection to Microsoft
 * SQL Server.
 * @author www.codejava.net
 *
 */
public class ConnectToDB {
 
	public static Connection JdbcSQLServerConnection() {
 
        Connection dbConnection = null;
 
        try {
 
            String dbURL = "jdbc:sqlserver://testspsqlsss01;databaseName=FlexNetTest";
            String user = "unserName";
            String pass = "passWord";
//
            try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            dbConnection = DriverManager.getConnection(dbURL, user, pass);
            
          //  dbConnection = DriverManager.getConnection(dbURL, user, pass);
            if (dbConnection != null) {
                DatabaseMetaData dm = (DatabaseMetaData) dbConnection.getMetaData();
            }
 
        } catch (SQLException ex) {
        	
            ex.printStackTrace();
        }
		return dbConnection;
	}
	
	
	
	public static void closeDBConnection(Connection dbConnection){
		
            try {
                if (dbConnection != null && !dbConnection.isClosed()) {
                	dbConnection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

	}
	
	public static String sqlQueryResults(String sqlQuery)
	{
		Connection dbConnection= ConnectToDB.JdbcSQLServerConnection();
		String sqlQueryResult = null;
		try {
			java.sql.Statement statement= dbConnection.createStatement();
			ResultSet res=statement.executeQuery(sqlQuery);
			while (res.next()) {
	            sqlQueryResult=res.getString(1);
	            SafLog.debug(sqlQueryResult);
	         }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ConnectToDB.closeDBConnection(dbConnection);
			e.printStackTrace();
		}
		ConnectToDB.closeDBConnection(dbConnection);
		return sqlQueryResult;
	}
	
}