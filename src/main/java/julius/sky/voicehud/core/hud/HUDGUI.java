package julius.sky.voicehud.core.hud;

import java.util.Calendar;
import java.lang.reflect.*;
import java.util.GregorianCalendar;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioRenderer;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.ViewPort;
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

import com.jme3.app.SimpleApplication;
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

public class HUDGUI extends SimpleApplication implements Runnable {

  private App app;
  private int health;
  private HUDGUI hudgui;
  private Nifty nifty;
  private NiftyJmeDisplay niftyDisplay;
  private boolean hudVisible = false;
  


//  public static void main(String[] args) {
//  public HUDGUI(AssetManager assetManager) {
  public HUDGUI(App app) {
	  this.app = app;
	  this.hudgui = this;
//	this.initialize();
//	this.assetManager = assetManager;
    AppSettings settings = new AppSettings(true);
//    settings.setResolution(640, 480);
//    HUDGUI app = new HUDGUI();
    this.setShowSettings(false); // splashscreen
    
    // set to fullscreen. turned off while developing.
    settings.setFullscreen(false);
    
    this.setSettings(settings);
  }

  @Override
  public void simpleInitApp() {
    setDisplayFps(false);
    setDisplayStatView(false);

    /**
     * Just some simple JME content to show it's really a JME app:
     */
//    Box b = new Box(Vector3f.ZERO, 1, 1, 1);
//    Geometry geom = new Geometry("Box", b);
//    
//    if(assetManager!=null){System.out.println("assetmanager not null");}
//    else{System.out.println("assetmanager null");}
//    Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//    mat.setColor("Color", ColorRGBA.Blue);
//    geom.setMaterial(mat);
//    rootNode.attachChild(geom);

//    startScreen = new MyStartScreen();
//    stateManager.attach(startScreen);

    /**
     * Åctivate the Nifty-JME integration: 
     */
    niftyDisplay = new NiftyJmeDisplay(
            assetManager, inputManager, audioRenderer, guiViewPort);
    setNifty(niftyDisplay.getNifty());
    guiViewPort.addProcessor(niftyDisplay);
//    nifty.fromXml("Interface/views/tutorial/screen3.xml", "start", startScreen);
//    nifty.fromXml("Interface/views/HUDGUI.xml", "start", startScreen);
//    String xmlPath = "Interface/views/HUDGUI.xml";
//	getNifty().fromXml(xmlPath, "start", new HUDGUIController(this));

    //nifty.setDebugOptionPanelColors(true);
    
    flyCam.setDragToRotate(true); // you need the mouse for clicking now    

    //	System.out.println("try open new layer");
    //	this.getNifty().fromXml("Interface/views/TEST.xml", "start");


  }

  @Override
  public void simpleUpdate(float tpf) {
  }

  public String getPlayerName() {
    return System.getProperty("user.name");
  }

public void run() {

    this.start();
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
//	getNifty().fromXml(layerXMLPath, "start", new HUDGUIController(this));
	
	
	// got through all current layers first to determine whether or not to hide.
	for(Element layer : screen.getLayerElements()){
		
		System.out.println("looking through existing layers");
		
		// when hud spoken when visible, all layers should hide.
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
		
		// when non-hud layer spoken when visible and hud visible, hide layer.
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
			if(layer.getId().equals("CLOCK")){
				ClockController cc = (ClockController)getNifty().findScreenController("ClockController");	
			}
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
    inputManager.setCursorVisible(false);
    flyCam.setEnabled(true);
}

public Nifty getNifty() {
	return nifty;
}

public void setNifty(Nifty nifty) {
	this.nifty = nifty;
}

}
