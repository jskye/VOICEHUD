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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestApp {
	
    App thisApp;
    
    @Before
    public void init() {
    	thisApp = new App();
    	thisApp.start();
    }
    
    
//	@Test
//	public void getHUDGUI() {
//		Assert.assertEquals(thisApp.getHUDGUI().getClass().getSimpleName(), "HUDGUIState");
//	}
//	
//	@Test
//	public void voiceThreadIsDaemon() throws InterruptedException {
//		Thread.sleep(10000);
//		Assert.assertEquals(thisApp.getVoiceThread().isDaemon(), true);
//	}
	
}
