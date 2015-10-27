package julius.sky.voicehud.core.hud;

import java.beans.PropertyVetoException;

import javax.speech.AudioException;
import javax.speech.EngineException;
import javax.speech.EngineStateError;

import julius.sky.voicehud.core.voice.VoiceCommandManager;
import julius.sky.voicehud.core.voice.tts.TTS;

import com.jme3.app.SimpleApplication;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.events.ElementShowEvent;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;


public class MessagesController implements ScreenController{
	

	private HUDGUIState hudGUI;
	private Nifty nifty;
	private Screen screen;
	private Thread alertThread;
	private TTS alert;
	private Thread voiceThread;
	private VoiceCommandManager vcm;

	/**
	 * Creates a new controller instance for nifty-gui.
	 */

	public MessagesController(HUDGUIState hudGUI, VoiceCommandManager vcm, Thread voiceThread) 
//	public MessagesController(HUDGUIState hudGUI) 
	{
//		this.sim = sim;
		this.hudGUI = hudGUI;
		this.nifty = hudGUI.getNifty();
		this.screen = nifty.getCurrentScreen();
//		this.voiceThread = voiceThread;
//		this.vcm = vcm;
		
		try {
			this.alert = new TTS();
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
		setTextToElement("nameLabel1", "Mum");
		setTextToElement("messageLabel1", "Hello son, how are you today?");
		this.alert.setTextToSpeak("Mum says: Hello song, how are you today?");
	    this.alertThread = new Thread(this.alert);
	    
	    
	    try {
			vcm.pauseDialog();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
//	    try {
//			voiceThread.wait();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	    
		try {
			speakMessage();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// we want voice thread to wait on tts thread.
		
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		try {
			vcm.sleep(3000);
			vcm.startDialog();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		disappear();
	}
	
	
	@NiftyEventSubscriber(id="MESSAGES")
	public void onElementShow(final String id, ElementShowEvent showevent ) {
		// TODO Auto-generated method stub
		setTextToElement("nameLabel1", "Mum");
		setTextToElement("messageLabel1", "Hello son, how are you today?");
		this.alert.setTextToSpeak("Mum says: Hello song, how are you today?");
	    this.alertThread = new Thread(this.alert);
	    
	    
	    try {
			vcm.pauseDialog();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
//	    try {
//			voiceThread.wait();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	    
		try {
			speakMessage();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// we want voice thread to wait on tts thread.
		
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		try {
			vcm.sleep(3000);
			vcm.startDialog();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		disappear();

	}
	
	
	public void disappear(){
		this.screen.getLayerElements().clear();

	}
	
	public void speakMessage() throws InterruptedException{
		alertThread.start();
//		voiceThread.join();
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


}
