package Generic_Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRW {
	XSSFWorkbook wb;
	FileInputStream fis;
	public ExcelRW(String filePath) throws Exception{
		 fis= new FileInputStream(filePath);
		 wb= new XSSFWorkbook(fis);
	}
	
	
	public int getRow(String sheetname){
		XSSFSheet sheet = wb.getSheet(sheetname);		
		return sheet.getLastRowNum();
		
	}
	
	public int getColum(String sheetname){
		XSSFSheet sheet = wb.getSheet(sheetname);	
		return sheet.getRow(0).getLastCellNum();
		
	}
	
	public String readCell(String sheetname,int row,int col){
		XSSFSheet sheet = wb.getSheet(sheetname);
		 XSSFCell cell = sheet.getRow(row).getCell(col);
		 String celltext="";
		 if(cell.getCellType()==Cell.CELL_TYPE_STRING){
			 celltext=cell.getStringCellValue();
		 }else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
			  celltext=String.valueOf(cell.getNumericCellValue());
		 }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK){
			 celltext="";
		 }
		return celltext;
	}
	
	public void writeCell(String sheetname,int row,int col,String value){
		XSSFSheet sheet = wb.getSheet(sheetname);
		sheet.getRow(row).getCell(col).setCellValue(value);				
	}
	
	public void saveandClose(String fpath) throws Exception{
		FileOutputStream fos = new FileOutputStream(fpath);
		wb.write(fos);
		fis.close();
		fos.close();	
	}
	
	
	
	

}
