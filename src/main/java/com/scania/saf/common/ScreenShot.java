package com.scania.saf.common;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import com.scania.saf.log.SafLog;



public class ScreenShot extends OtherFunctionality{
	
	/**
	 * Return a new file name for snap shot
	 */
	static private String getSnapShotFileName(String testCaseName) {
		SafLog.debug(testCaseName);
		String fileName;
		String fileNameAbsolutePath;
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy_HH_mm_ss");
		Date date = new Date();
		fileName =  dateFormat.format(date) + "_" + testCaseName+ ".png";
		//fileNameAbsolutePath=getFullPath("sreenshot")+fileName;
		return fileName;
	}

	/**
	 * Create and return full path to folder, with ending backslash.
	 */
	static public String getFullPath(String folder) {
		SafLog.debug(folder);
		String filePath;
		try {
			// Get the dir path
			File directory = new File(".");
			filePath = directory.getCanonicalPath() + "\\screenshot\\" + folder + "\\";
			new File(filePath).mkdir();	
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return filePath;
	}
	
	
/**
 * Take screen shot, saved in project folder
 * @param fileName
 */
	static public void getScreenShot(String fileName) {
		SafLog.debug(fileName);
		try {	
			// Capture the screen shot of the area of the screen defined by the
			// rectangle
			Robot robot = new Robot();
			BufferedImage bi = robot.createScreenCapture(new Rectangle(1920,
					1200));
			ImageIO.write(bi, "png", new File(fileName));

		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Take screen shot, saved in under screen/folder/fileName
	 * @param fileName
	 * @param folder
	 */
	static public void getScreenShot( String folder, String fileName){
		SafLog.debug(folder + ", " + fileName);
		String snapShotFullPath=getFullPath(folder)+getSnapShotFileName(fileName);
		getScreenShot(snapShotFullPath);
		SafLog.info("file:////"+snapShotFullPath+ snapShotFullPath );
	}


}
