package com.scania.saf.todo.common;

/**
 * This class includes common test steps that could be used by other test scenarios under src/test/java.
 * Sucg common test steps are login, logout,etc.
 * @author George
 *
 */
public class CommonTestSteps extends CommonUtilities {
	
private boolean isPreviousTestCaseSucceeded;
	
	public boolean isPreviousTestCaseSucceeded() {
		return isPreviousTestCaseSucceeded;
	}

	public void setPreviousTestCaseSucceeded(boolean isPreviousTestCaseSucceeded) {
		this.isPreviousTestCaseSucceeded = isPreviousTestCaseSucceeded;
	}
}
