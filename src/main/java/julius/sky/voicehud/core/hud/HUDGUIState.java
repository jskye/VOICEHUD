package julius.sky.voicehud.core.hud;

import java.util.Calendar;
import java.lang.reflect.*;
import java.util.GregorianCalendar;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioRenderer;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial.CullHint;
import com.jme3.texture.Image;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.ListBox.SelectionMode;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import julius.sky.voicehud.App;
import julius.sky.voicehud.core.router.Route;
import julius.sky.voicehud.core.voice.VoiceCommandManager;
import julius.sky.voicehud.plugins.mobilecomm.MessagesController;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;

import de.lessvoid.nifty.Nifty;

import java.util.Calendar;

/**
 * University of Newcastle
 * CSSE, Sofware Engineering 
 * FINAL INDIVIDUAL THESIS PROJECT 
 * VoiceHud
 * Author: Julius Myszkowski
 * Student Id: c3155112
 * Email: julius.skye@gmail.com
 */

/**
 * HUDGUI class sets up the JME application.
 */

public class HUDGUIState extends AbstractAppState implements Runnable {
	

  // access main application fields
  private Thread voiceThread;
  private VoiceCommandManager vcm;
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
  private String baseHUDView = "DRIVER_HUD_VIEW";

  


  private HUDGUIState hudgui;
  private Nifty nifty;
  private NiftyJmeDisplay niftyDisplay;
  private boolean hudVisible = false;
  
  private Node x = new Node("x");  // some custom class fields...    
  public Node getX(){ return x; }  // some custom methods... 
  

  public HUDGUIState(Thread voiceThread, VoiceCommandManager vcm, App app) {
	  
      System.out.println( "constructing HUDGUI" );
	  
      this.vcm = vcm;
      this.voiceThread = voiceThread;
	  this.app = app;
	  this.inputManager = this.app.getInputManager();
	  this.assetManager = this.app.getAssetManager();
	  this.audioRenderer = this.app.getAudioRenderer();
	  this.viewPort = this.app.getViewPort();
	  this.guiViewPort = this.app.getGuiViewPort();
	  this.flyCam = this.app.getFlyByCamera();
	  this.hudgui = this;
	  
  }
  
  @Override
  public void initialize(AppStateManager stateManager, Application app) {
	  
      System.out.println( "initialising HUDGUI" );

	  
	  super.initialize(stateManager, app); 
      this.app = (SimpleApplication) app;          // cast to a more specific class

    // init stuff that is independent of whether state is PAUSED or RUNNING
      this.app.getRootNode().attachChild(getX()); // modify scene graph...
//    this.app.doSomething();                     // call custom methods...
    
		/**
		* Åctivate the Nifty-JME integration: 
		*/
		niftyDisplay = new NiftyJmeDisplay(
		       assetManager, inputManager, audioRenderer, guiViewPort);
		setNifty(niftyDisplay.getNifty());
		guiViewPort.addProcessor(niftyDisplay);
		flyCam.setDragToRotate(true); // you need the mouse for clicking now 
//        cam.setViewPort(0f, 0.5f, 0f, 0.5f);
//		nifty.setDebugOptionPanelColors(true);
 }

 @Override
  public void cleanup() {
    super.cleanup();
    // unregister all my listeners, detach all my nodes, etc...
    this.app.getRootNode().detachChild(getX()); // modify scene graph...
//    this.app.doSomethingElse();                 // call custom methods...
  }

  @Override
  public void setEnabled(boolean enabled) {
    // Pause and unpause
    super.setEnabled(enabled);
    if(enabled){
      // init stuff that is in use while this state is RUNNING
      this.app.getRootNode().attachChild(getX()); // modify scene graph...
//      this.app.doSomethingElse();                 // call custom methods...
    } else {
      // take away everything not needed while this state is PAUSED
    }
  }

  // Note that update is only called while the state is both attached and enabled.
  @Override
  public void update(float tpf) {
    // do the following while game is RUNNING
    this.app.getRootNode().getChild("x").scale(tpf); // modify scene graph...
//    x.setUserData(...);                                 // call some methods...
  }

//  @Override
//  public void simpleUpdate(float tpf) {
//  }

  public String getPviewName() {
    return System.getProperty("user.name");
  }

public void run() {
	this.initialize(stateManager, app);
}

/**
* Changes over to the given view of the (already opened)
* key mapping and graphic settings GUI.
* 
* @param selectedview
* 			view name to show.
*/
@SuppressWarnings("unchecked")
public void openView(Route selectedview)
{
	// get current nifty screen
	Screen screen = getNifty().getCurrentScreen();
//	System.out.println("current screen: " + "numviews= " 
//	+ screen.layoutviewsCallCount+ screen.debugOutput());
	String viewXMLPath = null;
	if(selectedview !=null){
		viewXMLPath = "Interface/views/" + selectedview.getViewname() + ".xml";	
	}
	else{
		System.out.println("No view for that commmand");
		return;
	}
	
	// go through all current views first to determine whether or not to hide.
	for(Element view : screen.getLayerElements()){
		
		System.out.println("looking through existing views");
		
		// when HUD heard when visible, all views should hide.
		if(selectedview.getViewname().equals(getBaseHUDView()) && hudVisible){
			
			for(Element visibleview : screen.getLayerElements()){
				System.out.println("hiding hud's visible view: "+visibleview +"("+visibleview.isVisible()+")");
				visibleview.hide();
				visibleview.setVisible(false);
				System.out.println(visibleview+" is now visible?: "+visibleview.isVisible());
			}			

			if(selectedview.getViewId().equals("DRIVERHUD")){hudVisible = false;}
			return;
		}
		
		// when non-HUD view heard when visible and HUD visible, hide view.
		else if(!view.getId().equals("DRIVERHUD")
				&& view.getId().equals(selectedview.getViewId()) 
				&& view.isVisible() 
				&& hudVisible){
			System.out.println("hiding view: " + selectedview.getViewname());
			view.hide();
			view.setVisible(false);
			return;
		}
		// when non-hud view heard and not visible and hud visible, show view.
		// or when hud not visible and view is hud then show it. 
		else if(
				(!selectedview.getViewname().equals(getBaseHUDView()) 
				&& view.getId().equals(selectedview.getViewId()) 
				&& !view.isVisible() && hudVisible) 
			|| (selectedview.getViewname().equals(getBaseHUDView()) && !hudVisible)){
			
			System.out.println("showing view: " + selectedview.getViewname());
			view.show();
			view.setVisible(true);
			if(view.getId().equals("DRIVERHUD")){hudVisible = true;}
			return;
		}
		
		System.out.println(!view.getId().equals(getBaseHUDView()));
		System.out.println( view.getId().equals(selectedview.getViewId()));
		System.out.println( view.isVisible() );
		System.out.println( hudVisible);

	}
	

	
		// if non-hud view hasn't been loaded yet and hud is visible load it.
		// if hud view hasnt been loaded yet and is not visible load it.
		if (!selectedview.getViewname().equals(getBaseHUDView()) && hudVisible
				|| (selectedview.getViewname().equals(getBaseHUDView()) && !hudVisible)){
			
			System.out.println("loading view: " + selectedview.toString() + selectedview.getViewname());			
			
			//TODO: workout why this doesnt work
			// hacky onthefly parameterised controller construction.
			// this is needed because the HUDGUIState shouldnt need to know
			// which views it needs to load, actually router should call this method.
			// TODO: route commands to views in router class.
			ScreenController viewController= null;
//			try{
//				HUDGUI hudgui = null;
//				String className = selectedview.getControllerName();
//				Class cl =  Class.forName(className);
//				Constructor con = cl.getConstructor(HUDGUI.class);
//				Object viewsViewControllerObj = con.newInstance(hudgui);
//				viewsViewController = (ScreenController) viewsViewControllerObj;
//				
//			}catch(Exception e){
//				e.printStackTrace();
//			}
			
			if(selectedview.getViewname().equals(getBaseHUDView())){
				System.out.println("constructing hudgui controller");
				viewController = new HUDGUIController(this);
			}
			else if(selectedview.getViewname().equals("MESSAGES_VIEW")){
				System.out.println("constructing messages controller");
				viewController = new MessagesController(this, vcm, voiceThread);
//				viewController = new MessagesController(this);
			}
			else if(selectedview.getViewname().equals("CLOCK_VIEW")){
				System.out.println("constructing clock controller");
				viewController = new ClockController(this);

			}
			else if(selectedview.getViewname().equals("DATE_VIEW")){
				System.out.println("constructing clock controller");
				viewController = new DateController(this);
			}
			else if(selectedview.getViewname().equals("FUEL_VIEW")){
				System.out.println("constructing clock controller");
				viewController = new FuelController(this);
			}
			else if(selectedview.getViewname().equals("MAP_VIEW")){
				System.out.println("constructing clock controller");
				viewController = new MapController(this);
			}
					
//			getNifty().registerScreenController(viewsViewController);	
			getNifty().fromXml(viewXMLPath, "start", viewController);
			
			hudVisible = true;
			return; 
		}

}


public void loadView(){
	
}


public void toggleHUD(){
	if(!hudVisible){
		showHUD();
	}
	else{
		hideHUD();
		// was having an exception when killing the screen,
		// so have moved into open view for now.
	}
}

/**
 * Sets the visibility of the hud display to true.
 */
public void showHUD() 
{
		// attach the Nifty display to the gui view port as a processor
		guiViewPort.addProcessor(niftyDisplay);
		System.out.println("showing HUD screen: ");
		String viewXMLPath = "Interface/views/" + getBaseHUDView() + ".xml";
		getNifty().fromXml(viewXMLPath, "start", new HUDGUIController(this));
		hudVisible = true;
}

/**
 * Sets the visibility of the HUD to false.
 */
public void hideHUD() 
{		
		guiViewPort.removeProcessor(niftyDisplay);		
		hudVisible = false;
}


/**
 * Close key mapping and graphic settings GUI.
 */
private void closeHUDGUI() 
{
	nifty.exit();
}

public Nifty getNifty() {
	return nifty;
}

public void setNifty(Nifty nifty) {
	this.nifty = nifty;
}

/**
* @return the baseHUDView
*/
public String getBaseHUDView() {
	return baseHUDView;
}


}
