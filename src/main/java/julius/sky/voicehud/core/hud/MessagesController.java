package julius.sky.voicehud.core.hud;

import java.beans.PropertyVetoException;

import javax.speech.AudioException;
import javax.speech.EngineException;
import javax.speech.EngineStateError;

import julius.sky.voicehud.core.voice.tts.TTS;

import com.jme3.app.SimpleApplication;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;


public class MessagesController implements ScreenController{
	

	private HUDGUIState hudGUI;
	private Nifty nifty;
	private Screen screen;
	private Thread alertThread;
	private TTS alert;

	/**
	 * Creates a new controller instance for nifty-gui.
	 */

	public MessagesController(HUDGUIState hudGUI) 
	{
//		this.sim = sim;
		this.hudGUI = hudGUI;
		this.nifty = hudGUI.getNifty();
		this.screen = nifty.getCurrentScreen();
		
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
		speakMessage();
		try {
			Thread.sleep(3);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		disappear();
	}
	
	public void speakMessage(){
		alertThread.start();
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
