package testrulesmanagement;

import logMgn.SafLog;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import commonFuncMgn.ScreenShot;
import commontestmanagement.CommonTestSteps;
import configMgn.ConfigureSaf;

import windowAndPageMgn.pageMgn.CommonWindowsAndPages;


/**
 * Test rules on test case level. 
 * The rules include information text before and after a test case. 
 * Further more the rules include a seconds try in case a test case fail
 * @author George
 *
 */
public class TestRuleTestCaseLevel extends  CommonTestSteps implements TestRule {
	
	CommonWindowsAndPages commonWindowsAndPages;
	
	
	public TestRuleTestCaseLevel() {
	}

	public Statement apply(Statement base, Description description) {
		return statement(base, description);
	}

	private Statement statement(final Statement base, final Description description) {

		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
					//siteDriverManager= new SiteDriverManager();
					commonWindowsAndPages =new CommonWindowsAndPages();
					CommonTestSteps commonTestSteps= new CommonTestSteps();
				try {
					
					SafLog.testCaseStart(description);
					base.evaluate();
					CommonTestSteps.quiteDriver();
					SafLog.testCaseEnd(description);
					return;
				} catch (Throwable e) {
//					if (ConfigureSaf.SAF_RERUN_TEST_CASE_IF_FAILED) {
//						SafLog.info(e.getStackTrace().toString());
//						SafLog.testCaseStart(description);
//						CommonTestSteps.quiteDriver();
//						commonTestSteps.initWindowsAndPages();
//						base.evaluate();
//						CommonTestSteps.quiteDriver();;
//						SafLog.testCaseEnd(description);
//					}
					setPreviousTestCaseSucceeded(false);
					String folder=description.getClassName();
					SafLog.testCaseErrorMessage(e);
					ScreenShot.getScreenShot(folder.substring(folder.lastIndexOf(".")+1, folder.length()),description.getMethodName());
					CommonTestSteps.quiteDriver();	
					SafLog.testCaseFailed(description);
					throw new Exception("TEST CASE FAILED");
				}
			}

		};
	}

	
	
	}