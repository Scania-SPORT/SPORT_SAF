package com.scania.saf;

public class Stack {

	private static final int CLIENT_CODE_STACK_INDEX;
	
	static {
		// Finds out the index of "this code" in the returned stack trace - funny but it differs in JDK 1.5 and 1.6
	    int i = 0;
	    for(StackTraceElement ste : Thread.currentThread().getStackTrace()) {
	        i++;
	        if(ste.getClassName().equals(Stack.class.getName()))
	            break;
	    }
	    CLIENT_CODE_STACK_INDEX = i;
	}

	public static StackTraceElement getStackTraceElement(int i) {
	    return Thread.currentThread().getStackTrace()[CLIENT_CODE_STACK_INDEX+i];
	}
	
}
