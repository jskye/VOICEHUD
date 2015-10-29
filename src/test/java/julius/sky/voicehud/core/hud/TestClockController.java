/**
 * University of Newcastle
 * CSSE, Sofware Engineering 
 * FINAL INDIVIDUAL THESIS PROJECT 
 * VoiceHud
 * Author: Julius Myszkowski
 * Student Id: c3155112
 * Email: julius.skye@gmail.com
 */
package julius.sky.voicehud.core.hud;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import  org.junit.*;

import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.elements.events.ElementShowEvent;
/**
 * @author juliusskye
 *
 */
public class TestClockController {
    private String currentTime;
    private String dummyClockLabel;

    @Before
    public void init() {
    	updateTime();
    }
    
    public void updateTime(){
    	 // mock the updateTime method
		   Calendar cal = Calendar.getInstance();
	       SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	       setCurrentTime(sdf.format(cal.getTime()));
//	       setTextToElement("clockLabel1", getCurrentTime().toString());
	       dummyClockLabel = currentTime;
    }
	
	@Test
	// twelve hour time is defaulted because when spoken to the user, 00 (12) is confusing.
	public void isTwelveHourTime(){
	       // mock the time method but in 24 hr time.
		   Calendar cal = Calendar.getInstance();
	       SimpleDateFormat sdf24 = new SimpleDateFormat("hh:mm");
	       String militaryTime = sdf24.format(cal.getTime());
//	       String dummyClockLabel2 = currentTime;
	       Assert.assertNotEquals(dummyClockLabel, militaryTime);
		}
	

    /**
	 * @param format
	 */
	private void setCurrentTime(String format) {
		// TODO Auto-generated method stub
		
	}
	
	

}
