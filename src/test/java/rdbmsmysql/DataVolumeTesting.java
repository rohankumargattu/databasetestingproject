package rdbmsmysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataVolumeTesting
{
	public static void main(String[] args) throws Exception
	{
		//Define driver class
		Class.forName("com.mysql.jdbc.Driver");
		//Connect to database
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb","root","gatturohan");
		Statement stmt=con.createStatement();
		//Read data from excel from 2nd row(index=1)
		//1st row(index=0) contains names of columns
		int c=0;
		while(2>1)
		{
			try
			{
				//Execute query on database
				int rs=stmt.executeUpdate("insert into 1andhalfcrorebatch(name,city,salary) values('abcd','pqrs',500);");
				if(rs==1)
				{
					c++;
					if(c==1000)
					{
						break;
					}
					continue;
				}
			}
			catch(Exception ex)
			{
				break;
			}
		}
		System.out.println("DB capacity is "+c);
		//Disconnect from database
		con.close();
	}
}
