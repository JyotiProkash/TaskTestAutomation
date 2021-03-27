package com.hybridframework.qa.helper.excel;

import com.hybridframework.qa.helper.logger.LoggerHelper;
import com.hybridframework.qa.helper.select.DropDownHelper;
import freemarker.core.StringArraySequence;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

/**
 * Created by Jyoti on 19/04/2020
 */
public class ExcelReaderHelper
  {
      private Logger log= LoggerHelper.getLogger(ExcelReaderHelper.class);

      public ExcelReaderHelper() throws IOException {
  }

      /**
       * This method is used getExcelData.
       * @param excelLocation
       * @param sheetName
       * @return
       */
      public Object[][] getExcelData(String excelLocation, String sheetName)
        {
            try
             {
                log.info("Creating excel object:"+excelLocation);
                Object dataSets[][]=null;
                FileInputStream file=new FileInputStream(new File(excelLocation));
                //Creation workbook instance as .xlsx file format
                XSSFWorkbook workbook = new XSSFWorkbook(file);
                //Creation sheet from the workbook
                XSSFSheet sheet=workbook.getSheet(sheetName);
                //Count total number of active rows
                int totalRow=sheet.getLastRowNum()+1;
                //count total number of active columns
                int totalColumn=sheet.getRow(0).getLastCellNum();
                //Creation 2D array of rows and columns
                dataSets=new Object[totalRow][totalColumn];

                //Iterator through each row one by one
                Iterator<Row> rowIterator = sheet.iterator();

                int i=0;
                int t=0;

                while(rowIterator.hasNext())
                  {
                     Row row = rowIterator.next();
                     if(i++!=0)
                       {
                         int k=t;
                         t++;
                         //iterate through all the column for each row
                         Iterator<Cell> cellIterator = row.cellIterator();

                         int j=0;

                         while(cellIterator.hasNext())
                           {
                              Cell cell=cellIterator.next();
                              //Check the cell type format accordingly
                              switch(cell.getCellType())
                                {
                                     case NUMERIC:
                                     //System.out.println(i+",");
                                     //System.out.println(j);
                                     dataSets[k][j++]=String.valueOf(cell.getNumericCellValue());
                                     System.out.println(cell.getNumericCellValue());
                                     break;

                                     case STRING:
                                     //System.out.print(k+",");
                                     //System.out.print(j);
                                     dataSets[k][j++]=cell.getStringCellValue();
                                     System.out.println(cell.getStringCellValue());
                                     break;

                                     case BOOLEAN:
                                     dataSets[k][j++]=String.valueOf(cell.getBooleanCellValue());
                                     System.out.println(cell.getBooleanCellValue());
                                     break;

                                     case BLANK:
                                     dataSets[k][j++]="";
                                     System.out.println(cell.getErrorCellValue());
                                     break;

                                     case FORMULA:
                                     dataSets[k][j++]=String.valueOf(cell.getCellFormula());
                                     System.out.println(cell.getCellFormula());
                                     break;
                                     default:
                                     System.out.println(cell.getStringCellValue());
                                     break;
                                }
                           }
                       }
                  }
                file.close();
                workbook.close();
                return dataSets;
             }
            catch(Exception e)
             {
                e.printStackTrace();
             }
            return null;
        }

      /**
       * This method is used updateResult status in excel.
       * @param excelLocation
       * @param sheetName
       * @param testCaseName
       * @param testStatus
       * @throws IOException
       */
      public void updateResult(String excelLocation, String sheetName, String testCaseName, String testStatus) throws IOException
        {
          try
            {
              FileInputStream file = new FileInputStream(new File(excelLocation));
              //Creation workbook instance as .xlsx file format
              //@SuppressWarnings("resource")
              XSSFWorkbook workbook = new XSSFWorkbook(file);
              //Creation sheet from the workbook
              XSSFSheet sheet = workbook.getSheet(sheetName);
              //Count total number of active row
              int totalRow = sheet.getLastRowNum() + 1;
              for (int i = 1; i < totalRow; i++)
                {
                  XSSFRow r = sheet.getRow(i);
                  String str = r.getCell(0).getStringCellValue();
                  if (str.contains(testCaseName))
                    {
                      r.createCell(1).setCellValue(testStatus);
                      file.close();
                      log.info("Result Updated...");
                      FileOutputStream out = new FileOutputStream(new File(excelLocation));
                      workbook.write(out);
                      out.close();
                      break;
                    }
                }
            }
          catch (Exception e)
            {
                e.printStackTrace();
            }
        }


		/*public static void main(String[] args) throws IOException {
			String excelLocation=System.getProperty("user.dir")+"/src/main/resources/testData/DataFile.xlsx";
			//System.out.println(excelLocation);
		    String sheetName="UserData";
            ExcelReaderHelper excelReaders=new ExcelReaderHelper();
			//excelReaders.getExcelData(excelLocation, sheetName);
            //System.out.println(excelReaders.getExcelData(excelLocation, sheetName));
            excelReaders.updateResult(excelLocation,"UserData","Login","pass");
            excelReaders.updateResult(excelLocation,"UserData","Account","pass");
            excelReaders.updateResult(excelLocation,"UserData","Cart","pass");
            excelReaders.updateResult(excelLocation,"UserData","Checkout","pass");
		}*/
  }
