package rdbmsmysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectQuery 
{
	public static void main(String[] args) throws Exception
	{
		//Define driver class
		Class.forName("com.mysql.jdbc.Driver");
		//Connect to database
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb","root","gatturohan");
		Statement stmt=con.createStatement();
		//Execute query on database
		ResultSet rs=stmt.executeQuery("select * from 1andhalfcrorebatch;");
		//Get Result set and display
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+"--"+rs.getString(2)+"--"+rs.getString(3)+"--"+rs.getInt(4));
		}
		//Disconnect from database
		con.close();
	}
}
