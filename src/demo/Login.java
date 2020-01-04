package demo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//******************************************************************************************
//apache poi jars
//https://archive.apache.org/dist/poi/release/bin/poi-bin-3.10-FINAL-20140208.zip
//
//use HSSF if excel was 2007 and below
//use XSSF if excel is 2007+ and below
//******************************************************************************************

public class Login {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "[chrome driver location]");
		WebDriver driver = new ChromeDriver();
		driver.get("url for testing");
		
		
		File src=new File("[location for excel file]");
		FileInputStream finput = new FileInputStream(src);
		XSSFWorkbook workbook = new XSSFWorkbook(finput);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		for(int i=1;i<=sheet.getLastRowNum();i++) { //looping through all the elements
			driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);//wait for 30 seconds before showing an exception
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//wait for 30sec to load the element before showing an exception
			
			WebElement email = driver.findElement(By.xpath("//input[@id='email']"));//input field of email
			WebElement password = driver.findElement(By.xpath("//input[@id='password']"));//input field of password
			WebElement loginbtn = driver.findElement(By.xpath("//input[@class='btn btn-block btn-primary']"));//login button
			
			email.clear();
			password.clear();
			
			//import data for email
			XSSFCell cell = sheet.getRow(i).getCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			email.sendKeys(cell.getStringCellValue());
			
			//import data for password
			XSSFCell cell2 = sheet.getRow(i).getCell(2);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			password.sendKeys(cell2.getStringCellValue());
			
			System.out.println(cell.getStringCellValue()+"&&"+cell2.getStringCellValue());
			
			
			loginbtn.click();
			
			if(driver.getCurrentUrl().toString()!="https://www.mashupstack.com/login") {
				System.out.println("gotcha @"+i+"\n"+driver.getCurrentUrl().toString());
				break;
			}
		
		}
		

	
		
		
		
		
		
//		WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
//		WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
//		WebElement loginbtn = driver.findElement(By.xpath("//input[@class='btn btn-block btn-primary']"));
//			
//		email.sendKeys("vishnukut8@gmail.com");
//		password.sendKeys("vishnu1998");
//		loginbtn.click();
	}

}
