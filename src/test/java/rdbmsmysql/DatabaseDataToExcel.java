package rdbmsmysql;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DatabaseDataToExcel 
{
	public static void main(String[] args) throws Exception
	{
		//Create an excel file
		File f=new File("dbtoexcel.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook();
		Sheet sh=wb.createSheet("Sheet1");
		//Create column headings
		sh.createRow(0).createCell(0).setCellValue("ID");
		sh.getRow(0).createCell(1).setCellValue("Name");
		sh.getRow(0).createCell(2).setCellValue("City");
		sh.getRow(0).createCell(3).setCellValue("Salary");
		//Define driver class
		Class.forName("com.mysql.jdbc.Driver");
		//Connect to database
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb","root","gatturohan");
		Statement stmt=con.createStatement();
		//Execute query on database
		ResultSet rs=stmt.executeQuery("select * from 1andhalfcrorebatch;");
		//Get Result set and display
		int record=0;
		while(rs.next())
		{
			record=record+1;
			sh.createRow(record).createCell(0).setCellValue(rs.getInt(1));
			sh.getRow(record).createCell(1).setCellValue(rs.getString(2));
			sh.getRow(record).createCell(2).setCellValue(rs.getString(3));
			sh.getRow(record).createCell(3).setCellValue(rs.getInt(4));
		}
		//Disconnect from database
		con.close();
		
		//Autofit
		sh.autoSizeColumn(0);
		sh.autoSizeColumn(1);
		sh.autoSizeColumn(2);
		sh.autoSizeColumn(3);
		//Save and close excel
		FileOutputStream fo=new FileOutputStream(f);
		wb.write(fo);
		fo.close();
		wb.close();
	}
}
