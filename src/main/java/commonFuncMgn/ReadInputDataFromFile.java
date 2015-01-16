package commonFuncMgn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import logMgn.SafLog;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.openqa.selenium.WebDriver;


import com.csvreader.CsvReader;

import configMgn.ConfigureSaf;

import parametermanagement.OtherParameters;

/**
 * This class includes reading functinoality from different types of files, such as Excel and CSV.
 * The files and file paths are created using ClassLoader.
 * @author George
 *
 */
public class ReadInputDataFromFile extends OtherParameters{

//	protected WebDriver siteDriver=SiteDriverManager.getSiteDriverSingleton();
//	private static final String saf_USER_DATA_FILE_NAME=ConfigureSaf.getConfigurationProperty(ConfigureSaf.saf_USER_DATA_FILE_NAME);
//	private static final String saf_LANGUAGE=ConfigureSaf.getConfigurationProperty(ConfigureSaf.saf_LANGUAGE);
//	private static final String saf_LANGUAGE_FILE_NAME=ConfigureSaf.getConfigurationProperty(ConfigureSaf.saf_LANGUAGE_FILE_NAME);
	
	ClassLoader classLoader = getClass().getClassLoader();
	/**Get the language Excel file from class*/
	File safLanguageFile=new File(classLoader.getResource(ConfigureSaf.SAF_LANGUAGE_FILE_NAME).getFile());
	/**Get the CSV file from class*/
	File safUserDataFile = new File(classLoader.getResource(ConfigureSaf.SAF_USER_DATA_FILE_NAME).getFile());
	String classPathCSVFile=safUserDataFile.getPath();
	
	/**A generic method for return input data from CSV/Excel file or normal input. 
	 * If string[0] contains UD_ then the data is fetched from CSV.
	 * If string[0] contains LP_ then the data is fetched from Excel. 
	 * Otherwise the normal input data is returned.
	 * @param string
	 * @return
	 */
	public String getInputData(String... string) {
		SafLog.debug(string);
		String inputData = "";
		//TODO remove try state with something less time consuming
		try {
			
			if (string[0].contains("UD_")) {
				inputData = readInputDataFromCSVFile(string[0], string[1]);
			} else if (string[0].contains("LP_")) {
				inputData = readInputDataFromExcelSheet(string[0], string[1]);
			} else {
				inputData = string[0];
			}
		}catch (Exception e) {
			SafLog.debug(inputData);
		}
		return inputData;
	}

	/**
	 * Return input data from excel sheet. Takes the Key index and the sheet name for the string to return.
	 * The language for the string to return is fetched from the configuration properties(corresponds to the language to test).
	 * The name file 
	 * The string to return is fetched with the following algorithm: 
	 * 1) Search for the Key index cell in column 0. 
	 * 2) When the Key index cell is found step through the row to find the language cell, and thereby the  column index for the language
	 * 3) Search for the indexKey in column 0
	 * 4) When indexKey is found step through the row to find the language cell, according the column index for the language.
	 * 
	 * @param indexKey
	 * @param sheetName
	 * @return
	 */
	public String readInputDataFromExcelSheet(String indexKey, String sheetName) {
		SafLog.debug("input value to get and Excel sheet: " + indexKey);
		String errorMessage = "The value was not found in the Excel sheet. Looking for row number '" + indexKey + "' and column '" + ConfigureSaf.SAF_LANGUAGE + "'.";
		String errorMessageColumn = "\n-----------------------------------------------------------\n" +
				"The language column was not found in the Excel sheet. Looking for row number '" + indexKey + "' and column '" + ConfigureSaf.SAF_LANGUAGE + "'.";

		int columnNumber = -1;
		String cellInputData = "";
		HSSFCell hSSCell = null;
		
		try {
			/** Creating Input Stream **/
			FileInputStream languageFileInputStream = new FileInputStream(safLanguageFile);
			/** Create a POIFSFileSystem object **/
			POIFSFileSystem languagePOIFileSystem = new POIFSFileSystem(
					languageFileInputStream);
			/** Create a workbook using the File System **/
			HSSFWorkbook hSSWorkBook = new HSSFWorkbook(languagePOIFileSystem);
			/** Get the first sheet from workbook **/
			HSSFSheet sheet = hSSWorkBook.getSheet(sheetName);
			/** We now need something to iterate through the cells. **/
			Iterator rowIter = sheet.iterator();
			/**Iterate through the first column(key column). When "Index key" is found then get the column index for the given language
			 * Go on and iterate the first column. When indexKey is found then iterate the row until the language column then break
			 */
			while (rowIter.hasNext()) {
				HSSFRow myRow = (HSSFRow) rowIter.next();
				Iterator cellIter = myRow.cellIterator();
				hSSCell = (HSSFCell) cellIter.next();
				SafLog.debug(hSSCell.toString());
				if (hSSCell.toString().contentEquals("Index Key")) {
					/**fetch the column number for the language*/
					while (cellIter.hasNext()) {
						hSSCell = (HSSFCell) cellIter.next();
						SafLog.debug(hSSCell.toString());
						if (hSSCell.toString().contentEquals(ConfigureSaf.SAF_LANGUAGE)) {
							columnNumber = hSSCell.getColumnIndex();
							break;
						}
					}
				}
				if (hSSCell.toString().contentEquals(indexKey)) {
					//Make sure the language is column is there
					if (columnNumber<0){
						throw new RuntimeException(errorMessageColumn);
					}
					for (int i = 0; i < columnNumber; i++) {
						hSSCell = (HSSFCell) cellIter.next();
					}
					cellInputData = hSSCell.getStringCellValue();
					SafLog.debug(cellInputData);
					break;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(errorMessage, e);
		}
		return cellInputData;
	}
	
	
	/**
	 * Return input data from CSV file. The file name corresponds to the property saf_USER_DATA_FILE_NAME in the configuration properties. 
	 * The method takes the coordinates(userDataId, culumnName) for the cell and returns the value in the cell
	 * The string to return is fetched with the following algorithm: 
	 * 1) Search for the user data Id cell in column 0. 
	 * 2) When the data Id cell is found get the value for the cell according to the column name
	 * @param userDataId
	 * @param columnName
	 * @return
	 */
	public String readInputDataFromCSVFile(String userDataId, String columnName) {
		SafLog.debug("input value to get from CSV file : " + userDataId + " ; " + columnName);
		String errorMessage = "The value was not found in the CSV file, " + classPathCSVFile + ". Looking for row number '" + userDataId + "' and column '" + columnName + "'.";
		String inputValueFromCSV="";
		try {
			char chDelimiter = ';';
			CsvReader csvReader = new CsvReader(classPathCSVFile, chDelimiter);
			csvReader.readHeaders();
			/**Get the first cell(first column and first row)*/
			String userDataIdHeader=csvReader.getHeader(0);
			/**Go through the column with the name corresponding to userDataIdHeader, till you find a match for userDataId*/
			while (csvReader.readRecord()) {
				String getValueToCompare = csvReader.get(userDataIdHeader);
				if (getValueToCompare.contentEquals(userDataId)) {
					/**Get the cell value according to the columName*/
					inputValueFromCSV=csvReader.get(columnName);
					break;
				}
			}
			csvReader.close();
			if (inputValueFromCSV==""){				
				throw new Exception(errorMessage);
			}else{
				return inputValueFromCSV;				
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(errorMessage, e);
		} catch (IOException e) {
			throw new RuntimeException(errorMessage, e);
		} catch (Exception e) {
			throw new RuntimeException(errorMessage, e);
		}
	}
}
