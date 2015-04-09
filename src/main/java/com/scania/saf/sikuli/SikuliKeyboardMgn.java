package com.scania.saf.sikuli;

import java.io.File;

//import org.sikuli.api.ImageTarget;
//import org.sikuli.api.ScreenRegion;
//import org.sikuli.api.Target;

import com.scania.saf.Log;
import com.scania.saf.selenium.JavaScriptCalls;

public class SikuliKeyboardMgn {

//	static ScreenRegion screenRegion;
//	public JavaScriptCalls javaScriptCalls;
//    
//	public JavaScriptCalls getJavaScriptCalls() {
//		return javaScriptCalls;
//	}
//
//	public void setJavaScriptCalls(JavaScriptCalls javaScriptCalls) {
//		this.javaScriptCalls = javaScriptCalls;
//	}
//	
//	public ScreenRegion getScreenRegion() {
//		return screenRegion;
//	}
//
//	public void setScreenRegion(ScreenRegion screenRegion) {
//		this.screenRegion = screenRegion;
//	}
//
//	public boolean isImageOnScreen(String imagePath){
//		Log.debug(imagePath);
//		File file= new File(imagePath);
//		Target target= new ImageTarget(file);
//		ScreenRegion sR=screenRegion.wait(target, 700);
//		if (sR == null){
//			return false;
//		}else{
//			return true;
//		}
//	}
//	/*
//	public void assertImageOnScreen(String imagePath){
//		PocLog.debug(imagePath);
//		Assert.assertTrue("The image, " + imagePath + " did not exist on the screen", isImageOnScreen(imagePath));	
//	}
//	
//	public void assertImageNotOnScreen(String imagePath){
//		PocLog.debug(imagePath);
//		Assert.assertTrue("The image, " + imagePath + " exists on the screen", !isImageOnScreen(imagePath));	
//	}
//	*/
}
