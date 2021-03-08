package rdbmsmysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DeleteQuery 
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
			int rs=stmt.executeUpdate("delete from 1andhalfcrorebatch;");
			if(rs==1)
			{
				System.out.println("Data successfully deleted");
			}
		}
		catch(Exception ex)
		{
			System.out.println("Data not deleted");
		}
		
		//Disconnect from database
		con.close();
	}
}
