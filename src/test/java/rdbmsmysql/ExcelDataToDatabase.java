package rdbmsmysql;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelDataToDatabase
{
	public static void main(String[] args) throws Exception
	{
		File f=new File("exceltodb.xlsx");
		FileInputStream fi=new FileInputStream(f);
		Workbook wb=WorkbookFactory.create(fi);
		Sheet sh=wb.getSheet("Sheet1");
		int nour=sh.getPhysicalNumberOfRows();
		int nouc=sh.getRow(0).getLastCellNum();
		//Create results heading
		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
		Date dt=new Date();
		String rname=sf.format(dt);
		sh.getRow(0).createCell(nouc).setCellValue(rname);
		
		//Define driver class
		Class.forName("com.mysql.jdbc.Driver");
		//Connect to database
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb","root","gatturohan");
		Statement stmt=con.createStatement();
		//Read data from excel from 2nd row(index=1)
		//1st row(index=0) contains names of columns
		for(int i=1;i<nour;i++)
		{
			//Read data from excel
			DataFormatter df=new DataFormatter();
			String name=df.formatCellValue(sh.getRow(i).getCell(0));
			String city=df.formatCellValue(sh.getRow(i).getCell(1));
			int salary=Integer.parseInt(df.formatCellValue(sh.getRow(i).getCell(2)));
			try
			{
				//Execute query on database
				int rs=stmt.executeUpdate("insert into 1andhalfcrorebatch(name,city,salary) values('"+name+"','"+city+"',"+salary+");");
				if(rs==1)
				{
					sh.getRow(i).createCell(nouc).setCellValue("Row create with this data");
				}
			}
			catch(Exception ex)
			{
				sh.getRow(i).createCell(nouc).setCellValue("Row was not created with this data");
			}
		}
		//Disconnect from database
		con.close();
		
		//Autofit
		sh.autoSizeColumn(nouc);
		
		//Save and close excel file
		FileOutputStream fo=new FileOutputStream(f);
		wb.write(fo);
		fo.close();
		fi.close();
		wb.close();
	}
}
