package julius.sky.voicehud;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.system.AppSettings;


import julius.sky.voicehud.core.hud.HUDGUIState;
import julius.sky.voicehud.core.hud.SimpleMovieState;
import julius.sky.voicehud.core.hud.StartScreenState;
import julius.sky.voicehud.core.voice.VoiceCommandManager;
import julius.sky.voicehud.plugins.musicplayer.MusicPlayer;

public class App extends SimpleApplication
//public class App
{
	
//	private static MusicPlayer musicPlayer;

	private StartScreenState startScreenState;
	private HUDGUIState hudGuiState;
	private VoiceCommandManager vcm;
	private Thread HUDThread;
	private Thread voiceThread;
	private Thread movieThread;
	private App app;
	
    public static void main( String[] args )
    {
        System.out.println( "starting app" );
        App thisApp = new App();
        thisApp.app = thisApp;
        thisApp.start(); // start the app
        
    }
    
	/* (non-Javadoc)
	 * @see com.jme3.app.SimpleApplication#simpleInitApp()
	 * initialise application and its scenes
	 */
	@Override
	public void simpleInitApp() {

	      System.out.println( "initialising App" );

		  AppSettings settings = new AppSettings(true);
		  this.setShowSettings(false); // splashscreen
		  // set to fullscreen. turned off while developing.
		  settings.setFullscreen(false);
		  this.setSettings(settings);
		  setDisplayFps(false);
		  setDisplayStatView(false);
//		  startScreen = new StartScreenState();
//		  getStateManager().attach(startScreen);
		  this.initialiseVoiceRecognition();
		// sleep main thread 5 seconds whilst the voice recognition gets ready
			try {
				Thread.sleep(10000);
//				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  this.initialiseAppStates();
	}
	
    
    /**
	 * 
	 */
	private void initialiseVoiceRecognition() {
		// TODO Auto-generated method stub
		vcm = new VoiceCommandManager(app);
		voiceThread = new Thread(vcm);
		 // run as deamon to terminate this thread when app terminates.
		voiceThread.setDaemon(true);
		voiceThread.start(); 
	}

	// start the application
    public void initialiseAppStates(){
        
    	System.out.println( "attaching app states" );
		try {
			
			SimpleMovieState sm = new SimpleMovieState(app);
			getStateManager().attach(sm);
			
			hudGuiState = new HUDGUIState(app);
			getStateManager().attach(hudGuiState);


			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public Thread getHUDThread(){
    	return this.HUDThread;
    }
    
    public Thread getVoiceThread(){
    	return this.voiceThread;
    }


	public HUDGUIState getHUDGUI() {
		return hudGuiState;
	}

	public VoiceCommandManager getVcm() {
		return vcm;
	}


    
    
}
