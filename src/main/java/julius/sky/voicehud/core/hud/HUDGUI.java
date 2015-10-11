package julius.sky.voicehud.core.hud;

import java.util.Calendar;
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
import de.lessvoid.nifty.tools.Color;
//import eu.opends.basics.SimulationBasics;
//import eu.opends.niftyGui.listBox.TextListBoxModel;
//import eu.opends.niftyGui.HUDGUIController;
//import eu.opends.niftyGui.KeyMappingGUI.GuiLayer;
import julius.sky.voicehud.core.voice.voicecommand.VoiceCommandManager;

//public class HUDGUI {
//	
//	private InputManager inputManager;
//	private ViewPort guiViewPort;
//	private NiftyJmeDisplay niftyDisplay;
////	private ListBox<TextListBoxModel> listBox;
//	private Nifty nifty;
////	private SimulationBasics sim;
//	private boolean keyMappingHidden = true;
//	private boolean initiallyPaused = false;
//	private AssetManager assetManager;
//	private AudioRenderer audioRenderer;
////	private FlyByCamera flyCam;
//	
//	private boolean hudVisible = false;
//
//	
//	/**
//	 * This enum contains all tabs of the key mapping and graphic 
//	 * settings GUI. Each tab is connected to a certain layer and 
//	 * button.
//	 */
//	public enum GuiLayer 
//	{
//		KEYMAPPING_PAGE1("keyMappingLayer1", "keyMappingButton1"), 
//		KEYMAPPING_PAGE2("keyMappingLayer2", "keyMappingButton2"), 
//		GRAPHICSETTINGS("graphicSettingsLayer", "graphicSettingsButton");
//		
//		private String layerName;
//		private String button;
//		
//		GuiLayer(String layerName, String button)
//		{
//			this.layerName = layerName;
//			this.button = button;
//		}
//		
//		public String getLayerName()
//		{
//			return layerName;
//		}
//		
//		public String getButton()
//		{
//			return button;
//		}
//	}
//
//	
//	/**
//	 * Creates a new instance of the HUD GUI.
//	 * 
//	 * @param sim
//	 * 			SimulationBasics class.
//	 */
//	public HUDGUI()
////	public HUDGUI(SimulationBasics sim)
//	{
////		this.sim = sim;
////		this.assetManager = sim.getAssetManager();
////		this.inputManager = sim.getInputManager();
////		this.audioRenderer = sim.getAudioRenderer();
////		this.guiViewPort = sim.getGuiViewPort();
////		this.flyCam = sim.getFlyByCamera();
//		this.initHUDGUI();
//	}
//	
//	/**
//	 * Initializes key mapping and graphic settings GUI.
//	 */
//	private void initHUDGUI()
//	{		
//		NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
//    	
//    	// Create a new NiftyGUI object
//    	nifty = niftyDisplay.getNifty();
//    		
//    	String xmlPath = "Interface/HUDGUI.xml";
//    	
//    	// Read XML and initialize custom ScreenController
//    	nifty.fromXml(xmlPath, "start", new HUDGUIController(this));
//    		
//    	// attach the Nifty display to the GUI view port as a processor
//    	guiViewPort.addProcessor(niftyDisplay);
//    	
//    	// disable fly camera, since mouse pointer is needed for user input
////    	flyCam.setEnabled(false);
//	}
//	
//	
////	private void testTransparentImage(){
////		Image transPNG=Image.createImage("Textures/hud/grid.png");  //load the tranparent image or opaque image
////		int rgbData[];
////		transPNG.getRGB(rgbData, 0,transPNG.getWidth(), 0, 0,transPNG.getWidth(), transPNG.getHeight());
////		Image tranparentImage==Image.createRGBImage(rgbData, width, height, true); //process alpha (true) for opaque false
////		transPNG=null;
////	}
//
//	
//	
//	/**
//	 * Sets the visibility of the hud display to true.
//	 */
//	public void showHUD() 
//	{
//		if(!hudVisible)
//		{				
//			// attach the Nifty display to the gui view port as a processor
//			guiViewPort.addProcessor(niftyDisplay);
//			
//			// if scroll bar available --> set mouse pointer visible
////			inputManager.setCursorVisible(listBox.itemCount() > nrOflines);
//			
//			hudVisible = true;
//		}
//	}
//	
//	/**
//	 * Sets the visibility of the HUD to false.
//	 */
//	public void hideHUD() 
//	{
//		if(hudVisible)
//		{
//			// detach the Nifty display from the gui view port as a processor
//			guiViewPort.removeProcessor(niftyDisplay);
////			inputManager.setCursorVisible(false);
//			
//			hudVisible = false;
//		}
//	}
//	
//	
//	/**
//	 * Toggles the visibility of the message box.
//	 */
//	public void toggleDialog() 
//	{
//		if (hudVisible)
//			hideHUD();
//		else 
//			showHUD();
//	}
//	
//	
//	/**
//	 * Returns Nifty element of the key mapping and graphic settings GUI.
//	 * 
//	 * @return
//	 * 			Nifty Element.
//	 */
//	public Nifty getNifty()
//	{
//		return nifty;
//	}
//	
//	
//
//	/**
//	 * Changes over to the given layer of the (already opened)
//	 * key mapping and graphic settings GUI.
//	 * 
//	 * @param selectedLayer
//	 * 			Layer name to show.
//	 */
//	public void openLayer(GuiLayer selectedLayer)
//	{
//		Screen screen = nifty.getCurrentScreen();
//		
//		// show given layer, hide all others (except "menuLayer" which contains menu buttons)
//		for(Element layer : screen.getLayerElements())
//			if(layer.getId().equals(selectedLayer.getLayerName()) || layer.getId().equals("menuLayer"))
//				layer.show();
//			else
//				layer.hide();
//		
//		// set focus to button related to the selected layer
//		Button button = (Button) screen.findNiftyControl(selectedLayer.getButton(), Button.class);
//		button.setFocus();
//	}
//	
//	
//	/**
//	 * Close key mapping and graphic settings GUI.
//	 */
//	private void closeHUDGUI() 
//	{
//		nifty.exit();
//        inputManager.setCursorVisible(false);
////        flyCam.setEnabled(true);
//	}
//}
	
//package julius.sky.voicehud.core.hud;



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

/**
 * Nifty GUI 1.3 demo using XML for static content 
 * and Java for dynamic content. 
 */
public class HUDGUI extends SimpleApplication implements Runnable {

  private int health;
  private MyStartScreen startScreen;
  private HUDGUI hudgui;
  
 // this will probably be moved to router because its a routing of commands to gui layers (views).
//	public enum GuiLayer 
//	{
//		VIEW_STARTUP("startup_view", "keyMappingButton1"), 
//		VIEW_DEFAULT("keyMappingLayer2", "keyMappingButton2"), 
//		VIEW3("graphicSettingsLayer", "graphicSettingsButton");
//		
//		private String layerName;
//		private String button;
//		
//		GuiLayer(String layerName, String button)
//		{
//			this.layerName = layerName;
//			this.button = button;
//		}
//		
//		public String getLayerName()
//		{
//			return layerName;
//		}
//		
//		public String getButton()
//		{
//			return button;
//		}
//	}

//  public static void main(String[] args) {
//  public HUDGUI(AssetManager assetManager) {
  public HUDGUI() {
//	this.initialize();
//	this.assetManager = assetManager;
    AppSettings settings = new AppSettings(true);
//    settings.setResolution(640, 480);
//    HUDGUI app = new HUDGUI();
    this.setShowSettings(false); // splashscreen
    
    // set to fullscreen. turned off while developing.
//    settings.setFullscreen(true);
    
    this.setSettings(settings);
  }

  @Override
  public void simpleInitApp() {
    setDisplayFps(false);
    setDisplayStatView(false);

    /**
     * Just some simple JME content to show it's really a JME app:
     */
    Box b = new Box(Vector3f.ZERO, 1, 1, 1);
    Geometry geom = new Geometry("Box", b);
    
    if(assetManager!=null){System.out.println("assetmanager not null");}
    else{System.out.println("assetmanager null");}
    Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    mat.setColor("Color", ColorRGBA.Blue);
    geom.setMaterial(mat);
    rootNode.attachChild(geom);

//    startScreen = new MyStartScreen();
//    stateManager.attach(startScreen);

    /**
     * Åctivate the Nifty-JME integration: 
     */
    NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(
            assetManager, inputManager, audioRenderer, guiViewPort);
    Nifty nifty = niftyDisplay.getNifty();
    guiViewPort.addProcessor(niftyDisplay);
//    nifty.fromXml("Interface/tutorial/screen3.xml", "start", startScreen);
//    nifty.fromXml("Interface/HUDGUI.xml", "start", startScreen);
    String xmlPath = "Interface/HUDGUI.xml";
	nifty.fromXml(xmlPath, "start", new HUDGUIController(this));

    //nifty.setDebugOptionPanelColors(true);
    
    flyCam.setDragToRotate(true); // you need the mouse for clicking now    
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
//public void openLayer(GuiLayer selectedLayer)
//{
//	Screen screen = nifty.getCurrentScreen();
//	
//	// show given layer, hide all others (except "menuLayer" which contains menu buttons)
//	for(Element layer : screen.getLayerElements())
//		if(layer.getId().equals(selectedLayer.getLayerName()) || layer.getId().equals("menuLayer"))
//			layer.show();
//		else
//			layer.hide();
//	
//	// set focus to button related to the selected layer
//	Button button = (Button) screen.findNiftyControl(selectedLayer.getButton(), Button.class);
//	button.setFocus();
//}

}
