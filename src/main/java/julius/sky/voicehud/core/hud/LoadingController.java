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

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 * LoadingController.java class
 */
public class LoadingController implements ScreenController {

	private StartScreenState startScreenState;
	private Nifty nifty;
	private Screen screen;

	/**
	 * Creates a new controller instance for nifty-gui.
	 */
	public LoadingController(StartScreenState startScreenState) 
	{
//		this.sim = sim;
		this.startScreenState = startScreenState;
		this.nifty = startScreenState.getNifty();
		this.screen = nifty.getCurrentScreen();
	}
	
	
	/* (non-Javadoc)
	 * @see de.lessvoid.nifty.screen.ScreenController#bind(de.lessvoid.nifty.Nifty, de.lessvoid.nifty.screen.Screen)
	 */
	@Override
	public void bind(Nifty arg0, Screen arg1) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see de.lessvoid.nifty.screen.ScreenController#onEndScreen()
	 */
	@Override
	public void onEndScreen() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see de.lessvoid.nifty.screen.ScreenController#onStartScreen()
	 */
	@Override
	public void onStartScreen() {
		// TODO Auto-generated method stub

	}

}
