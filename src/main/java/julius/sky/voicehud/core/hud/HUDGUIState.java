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
import julius.sky.voicehud.core.router.Router.GuiLayer;
import julius.sky.voicehud.core.voice.VoiceCommandManager;

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

//public class HUDGUIApp extends SimpleApplication implements Runnable {
public class HUDGUIState extends AbstractAppState implements Runnable {
	

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

  
  private int health;
  private HUDGUIState hudgui;
  private Nifty nifty;
  private NiftyJmeDisplay niftyDisplay;
  private boolean hudVisible = false;
  
  private Node x = new Node("x");  // some custom class fields...    
  public Node getX(){ return x; }  // some custom methods... 
  

  public HUDGUIState(App app) {
	  
      System.out.println( "constructing HUDGUI" );
	  
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

  public String getPlayerName() {
    return System.getProperty("user.name");
  }

public void run() {
	this.initialize(stateManager, app);
}

/**
* Changes over to the given layer of the (already opened)
* key mapping and graphic settings GUI.
* 
* @param selectedLayer
* 			Layer name to show.
*/
@SuppressWarnings("unchecked")
public void openLayer(GuiLayer selectedLayer)
{
	// get current nifty screen
	Screen screen = getNifty().getCurrentScreen();
//	System.out.println("current screen: " + "numlayers= " 
//	+ screen.layoutLayersCallCount+ screen.debugOutput());

	String layerXMLPath = "Interface/views/" + selectedLayer.getLayerName() + ".xml";	
	
	// go through all current layers first to determine whether or not to hide.
	for(Element layer : screen.getLayerElements()){
		
		System.out.println("looking through existing layers");
		
		// when HUD heard when visible, all layers should hide.
		if(selectedLayer.getLayerName().equals("HUD_VIEW") && hudVisible){
			
			for(Element visiblelayer : screen.getLayerElements()){
				System.out.println("hiding hud's visible layer: "+visiblelayer +"("+visiblelayer.isVisible()+")");
				visiblelayer.hide();
				visiblelayer.setVisible(false);
				System.out.println(visiblelayer+" is now visible?: "+visiblelayer.isVisible());
			}			
			if(layer.getId().equals("HUD")){hudVisible = false;}
			return;
		}
		
		// when non-HUD layer heard when visible and HUD visible, hide layer.
		else if(!layer.getId().equals("HUD")
				&& layer.getId().equals(selectedLayer.getLayerId()) 
				&& layer.isVisible() 
				&& hudVisible){
			System.out.println("hiding layer: " + selectedLayer.getLayerName());
			layer.hide();
			layer.setVisible(false);
//			if(layer.getId().equals("HUD_VIEW")){hudVisible = false;}
			return;
		}
		// when non-hud layer selected and not visible and hud visible, show layer.
		// or when hud not visible and layer is hud then show it. 
		else if(
				(!selectedLayer.getLayerName().equals("HUD_VIEW") 
				&& layer.getId().equals(selectedLayer.getLayerId()) 
				&& !layer.isVisible() && hudVisible) 
			|| (selectedLayer.getLayerName().equals("HUD_VIEW") && !hudVisible)){
			
			System.out.println("showing layer: " + selectedLayer.getLayerName());
//			if(layer.getId().equals("CLOCK")){
//				ClockController cc = (ClockController)getNifty().findScreenController("ClockController");	
//			}
			layer.show();
			layer.setVisible(true);
			if(layer.getId().equals("HUD")){hudVisible = true;}
			return;
		}
		
		System.out.println(!layer.getId().equals("HUD"));
		System.out.println( layer.getId().equals(selectedLayer.getLayerId()));
		System.out.println( layer.isVisible() );
		System.out.println( hudVisible);

	}
	

	
		// if non-hud layer hasn't been loaded yet and hud is visible load it.
		// if hud layer hasnt been loaded yet and is not visible load it.
		if (!selectedLayer.getLayerName().equals("HUD_VIEW") && hudVisible
				|| (selectedLayer.getLayerName().equals("HUD_VIEW") && !hudVisible)){
			
			System.out.println("loading layer: " + selectedLayer.toString() + selectedLayer.getLayerName());			
			
			//TODO: workout why this doesnt work
			// hacky onthefly parameterised controller construction.
			ScreenController layersViewController= null;
//			try{
//				HUDGUI hudgui = null;
//				String className = selectedLayer.getControllerName();
//				Class cl =  Class.forName(className);
//				Constructor con = cl.getConstructor(HUDGUI.class);
//				Object layersViewControllerObj = con.newInstance(hudgui);
//				layersViewController = (ScreenController) layersViewControllerObj;
//				
//			}catch(Exception e){
//				e.printStackTrace();
//			}
			
			if(selectedLayer.getLayerName().equals("HUD_VIEW")){
				System.out.println("constructing hudgui controller");
				layersViewController = new HUDGUIController(this);
			}
			else if(selectedLayer.getLayerName().equals("MESSAGES_VIEW")){
				System.out.println("constructing messages controller");
				layersViewController = new MessagesController(this);
			}
			else if(selectedLayer.getLayerName().equals("CLOCK_VIEW")){
				System.out.println("constructing clock controller");
				layersViewController = new ClockController(this);

			}
			else if(selectedLayer.getLayerName().equals("DATE_VIEW")){
				System.out.println("constructing clock controller");
				layersViewController = new DateController(this);
			}
					
//			getNifty().registerScreenController(layersViewController);	
			getNifty().fromXml(layerXMLPath, "start", layersViewController);
			
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
		// so have moved into open layer for now.
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
		String layerXMLPath = "Interface/views/HUDGUI.xml";
		getNifty().fromXml(layerXMLPath, "start", new HUDGUIController(this));
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


}
