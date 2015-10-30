package julius.sky.voicehud.core.hud;

import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.speech.AudioException;
import javax.speech.EngineException;
import javax.speech.EngineStateError;

import julius.sky.voicehud.core.voice.tts.TTS;

import com.jme3.app.SimpleApplication;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.events.ElementShowEvent;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;


public class DateController implements ScreenController{
	

	private HUDGUIState hudGUI;
	private Nifty nifty;
	private Screen screen;
    private String currentTime;

	private TTS dateSpeakable;
	private Thread alertThread;
	private int dateElementShownCount;



	/**
	 * Creates a new controller instance for nifty-gui.
	 */

	/**
	 * Creates a new controller instance for nifty-gui.
	 */
	public DateController(HUDGUIState hudGUI) 
	{
//		this.sim = sim;
		this.hudGUI = hudGUI;
		this.nifty = hudGUI.getNifty();
		this.screen = nifty.getCurrentScreen();
		try {
			this.dateSpeakable = new TTS();
		} catch (EngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AudioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EngineStateError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.alertThread = null;
	}

	public void bind(Nifty arg0, Screen arg1) {
		// TODO Auto-generated method stub
		
	}

	public void onEndScreen() {
		// TODO Auto-generated method stub
		
	}

	public void onStartScreen() {
		// TODO Auto-generated method stub
		updateTime();
		speakDate();
		try {
			Thread.sleep(3);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		disappear();
	}
	
	public void disappear(){
		this.screen.getLayerElements().clear();

	}
	
	@NiftyEventSubscriber(id="DATE")
	public int onElementShow(final String id, ElementShowEvent showevent ) {
		updateTime();
		System.out.println("speaking time");
		speakDate();
		dateElementShownCount++;
		try {
			Thread.sleep(3);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		disappear();		
		return dateElementShownCount;

	}
	
	// needs to be called when command is recognised and layer shown.
	public void updateTime(){
       Calendar cal = Calendar.getInstance();       
       SimpleDateFormat fmt = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);       
       setCurrentTime(fmt.format(cal.getTime()));
       System.out.println(fmt.format(cal.getTime()));
       setTextToElement("dateLabel1", getCurrentTime().toString());
       this.dateSpeakable.setTextToSpeak(getCurrentTime());
       this.alertThread = new Thread(this.dateSpeakable);

	}
	
	public void speakDate(){
			alertThread.start();
	}
	
	public String getCurrentTime() {
		return currentTime;
	}
	
	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}
	
	
	
	/**
	 * Sets given text to the given element (e.g. label).
	 * 
	 * @param elementID
	 * 			Element's ID to assign a text to.
	 * 
	 * @param text
	 * 			Text to set.
	 */
    private void setTextToElement(String elementID, String text) 
    {
    	getElementByID(elementID).getRenderer(TextRenderer.class).setText(text);
    }
    
    /**
     * Looks up an element by ID.
     * 
     * @param elementID
     * 			ID to  look up.
     * 
     * @return
     * 			Element with given ID.
     */
    private Element getElementByID(String elementID)
    {
    	return nifty.getCurrentScreen().findElementByName(elementID);
    }
    
    private int getElementShownCount(){
    	return this.dateElementShownCount;
    }

}