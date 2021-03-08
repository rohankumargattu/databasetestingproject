package rdbmsmysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertQuery 
{
	public static void main(String[] args) throws Exception
	{
		//Define driver class
		Class.forName("com.mysql.jdbc.Driver");
		//Connect to database
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb","root","gatturohan");
		Statement stmt=con.createStatement();
		//Get Result set and display
		try
		{
			//Execute query on database
			int rs=stmt.executeUpdate("insert into 1andhalfcrorebatch values(15,'ajay','adilabad',500);");
			if(rs==1)
			{
				System.out.println("Data successfully inserted");
			}
		}
		catch(Exception ex)
		{
			System.out.println("Data not successfully inserted");
		}
		
		//Disconnect from database
		con.close();
	}
}
