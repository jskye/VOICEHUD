package julius.sky.voicehud.core.hud;

import com.jme3.app.SimpleApplication;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;


public class DateController implements ScreenController{
	

	private HUDGUIState hudGUI;
	private Nifty nifty;
	private Screen screen;

	/**
	 * Creates a new controller instance for nifty-gui.
	 */
	public DateController(HUDGUIState hudGUI) 
	{
//		this.sim = sim;
		this.hudGUI = hudGUI;
		this.nifty = hudGUI.getNifty();
		this.screen = nifty.getCurrentScreen();
	}

	public void bind(Nifty arg0, Screen arg1) {
		// TODO Auto-generated method stub
		
	}

	public void onEndScreen() {
		// TODO Auto-generated method stub
		
	}

	public void onStartScreen() {
		// TODO Auto-generated method stub
//		setTextToElement("nameLabel1", "Mum");
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