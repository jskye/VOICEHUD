/**
 * University of Newcastle
 * CSSE, Sofware Engineering 
 * FINAL INDIVIDUAL THESIS PROJECT 
 * VoiceHud
 * Author: Julius Myszkowski
 * Student Id: c3155112
 * Email: julius.skye@gmail.com
 */
package julius.sky.voicehud;
import julius.sky.voicehud.core.hud.HUDTests;
import julius.sky.voicehud.core.hud.TestClockController;
import julius.sky.voicehud.core.hud.TestDateController;
import julius.sky.voicehud.core.hud.TestHUDGUIController;
import julius.sky.voicehud.core.hud.TestMessagesController;
import julius.sky.voicehud.core.router.RouterTests;
import julius.sky.voicehud.core.voice.VoiceTests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
/**
 * RunTests.java class runs all the tests.
 * Includes TestApp for testing main app class as well as the suites of
 * each of the core and plugin modules.
 */
//	public class RunTests {
//	   public static void main(String[] args) {
//	      Result result = JUnitCore.runClasses(JunitTestSuite.class);
//	      for (Failure failure : result.getFailures()) {
//	         System.out.println(failure.toString());
//	      }
//	      System.out.println(result.wasSuccessful());
//	   }
//	
//}
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestApp.class,
        HUDTests.class,
        RouterTests.class,
        VoiceTests.class
        
        })

public class RunTests {
}