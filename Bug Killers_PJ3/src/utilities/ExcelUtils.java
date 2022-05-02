package utilities;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {
	private static HSSFWorkbook workbook;
    private static HSSFSheet sheet;
    private static HSSFCell cell;

   public void setExcelFile(String excelFilePath,String sheetName) throws IOException {
       //Create an object of File class to open xls file
       File file =    new File("D:\\Programs\\Eclipse Workspace\\Bug Killers_PJ3\\src\\testData\\TestData.xls");
       
       //Create an object of FileInputStream class to read excel file
       FileInputStream inputStream = new FileInputStream(file);
       
       //Creating workbook instance that refers to .xls file
       workbook=new HSSFWorkbook(inputStream);
       
       //Creating a Sheet object
       sheet=workbook.getSheet(sheetName);
       
   }

    public String getCellData(int rowNumber,int cellNumber){
    	//getting the cell value from rowNumber and cell Number
        cell =sheet.getRow(rowNumber).getCell(cellNumber);
        
        CellType cellType = cell.getCellType();
        //System.out.println(cellType);
		//returning the cell value as string
    	switch(cellType) {
	    	case NUMERIC:
	            double doubleVal = cell.getNumericCellValue();
	            return String.valueOf(doubleVal);
	        case STRING:
	            return cell.getStringCellValue();
	        case ERROR:
	            return String.valueOf(cell.getErrorCellValue());
	        case BLANK:
	            return "";
	        case FORMULA:
	            return cell.getCellFormula();
	        case BOOLEAN:
	            return String.valueOf(cell.getBooleanCellValue());
	        case _NONE:
	        	return "error decoding string value of the cell";
	        default:
	        	return "error decoding string value of the cell";
    	}
        
        
        
    }

    public int getRowCountInSheet(){
       int rowcount = sheet.getLastRowNum()-sheet.getFirstRowNum();
       return rowcount;
    }

    public void setCellValue(int rowNum,int cellNum,String cellValue,String excelFilePath) throws IOException {
    	//creating a new cell in row and setting value to it      
    	sheet.getRow(rowNum).createCell(cellNum).setCellValue(cellValue);
        
    	FileOutputStream outputStream = new FileOutputStream(excelFilePath);
    	workbook.write(outputStream);
    }
}
