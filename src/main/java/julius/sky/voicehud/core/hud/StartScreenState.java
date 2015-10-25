package julius.sky.voicehud.core.hud;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioRenderer;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 *
 */
public class StartScreenState extends AbstractAppState implements ScreenController {

	  // access main application fields
	  private SimpleApplication app;
	  private Node              rootNode;
	  private AssetManager      assetManager;
	  private AppStateManager   stateManager;
	  private InputManager      inputManager;
	  private ViewPort          viewPort;
	  private ViewPort guiViewPort;
	  private AudioRenderer audioRenderer;
	  private FlyByCamera flyCam;
	  private Camera cam;
	  
	  private Nifty nifty;
	  private Screen screen;
	  private NiftyJmeDisplay niftyDisplay;


  /** custom methods */
  public StartScreenState() {
    /** You custom constructor, can accept arguments */
  }

  public void startGame(String nextScreen) {
    nifty.gotoScreen(nextScreen);  // switch to another screen
  }

  public void quitGame() {
    app.stop();
  }

  public String getPlayerName() {
    return System.getProperty("user.name");
  }

  /** Nifty GUI ScreenControl methods */
  public void bind(Nifty nifty, Screen screen) {
    this.nifty = nifty;
    this.screen = screen;
  }

  public void onStartScreen() {
  }

  public void onEndScreen() {
  }

  /** jME3 AppState methods */
  @Override
  public void initialize(AppStateManager stateManager, Application app) {
    this.app = (SimpleApplication)app;
  }

  @Override
  public void update(float tpf) {
	  	
	  niftyDisplay = new NiftyJmeDisplay(
		       assetManager, inputManager, audioRenderer, guiViewPort);
		setNifty(niftyDisplay.getNifty());
		guiViewPort.addProcessor(niftyDisplay);
		flyCam.setDragToRotate(true); // you need the mouse for clicking now 
//       cam.setViewPort(0f, 0.5f, 0f, 0.5f);
//		nifty.setDebugOptionPanelColors(true);
	  
		// attach the Nifty display to the gui view port as a processor
	  	this.guiViewPort = this.app.getGuiViewPort();
		guiViewPort.addProcessor(niftyDisplay);
		System.out.println("showing HUD screen: ");
		String loadingLayer = "Interface/views/LOADING_VIEW.xml";
		getNifty().fromXml(loadingLayer, "start", new LoadingController(this));
		
		
//    if (screen.getScreenId().equals("start")) {
//      Element niftyElement = nifty.getCurrentScreen().findElementByName("score");
//      // Display the time-per-frame -- this field could also display the score etc...
//      niftyElement.getRenderer(TextRenderer.class).setText((int)(tpf*100000) + ""); 
//    }
  }
  
  public Nifty getNifty() {
		return nifty;
	}

	public void setNifty(Nifty nifty) {
		this.nifty = nifty;
	}
}
