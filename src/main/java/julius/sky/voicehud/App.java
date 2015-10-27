package julius.sky.voicehud;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.system.AppSettings;

import julius.sky.voicehud.core.hud.HUDGUIState;
import julius.sky.voicehud.core.hud.StartScreenState;
import julius.sky.voicehud.core.router.Router;
import julius.sky.voicehud.core.voice.VoiceCommandManager;
import julius.sky.voicehud.plugins.musicplayer.MusicPlayer;
import julius.sky.voicehud.simulation.SimpleMovieState;
import julius.sky.voicehud.simulation.Simulation;
import julius.sky.voicehud.simulation.driving.DrivingSimulation;

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
	private Router router;
	private Simulation simulation;
	private ScheduledThreadPoolExecutor executor;
	
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
		  settings.setFullscreen(true);
		  this.setSettings(settings);
		  setDisplayFps(false);
		  setDisplayStatView(false);
//		  startScreen = new StartScreenState();
//		  getStateManager().attach(startScreen);
		  		  
		  /* This constructor creates a new executor with a core pool size of 4. */
		  executor = new ScheduledThreadPoolExecutor(4);
		  try {
			this.initialiseVoiceRecognition();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
    
    /**
	 * 
	 */
	private void initSimulation() {
		this.simulation = (Simulation) new DrivingSimulation();
	}

	/**
	 * 
	 */
	private void initRouter() {
		this.router = new Router(getHUDGUI(), getSimulation());
	}

	/**
	 * @return
	 */
	private Simulation getSimulation() {
		return this.simulation;
	}

	/**
	 * @throws Exception 
	 * 
	 */
	private void initialiseVoiceRecognition() throws Exception {

		Callable callToCompleteInit = new Callable(){
	        public Object call() throws Exception {
	            return completeInit();
	        }
	    };
	    
		vcm = new VoiceCommandManager(app, callToCompleteInit);
		
		
		voiceThread = new Thread(vcm);
		 // run as deamon to terminate this thread when app terminates.
		voiceThread.setDaemon(true);
		
		

//		Future future = executor.submit(completedInitialisation); // complete setup or states, router...
//		if(future.isDone()){
			voiceThread.start(); // starts recogniser listening
//		}
	}
	
	public boolean completeInit(){
		  this.initialiseAppStates();
		  this.initSimulation();
		  this.initRouter();
		  return true;
	}

	// start the application
    public void initialiseAppStates(){
        
    	System.out.println( "attaching app states" );
		try {
			
			SimpleMovieState sm = new SimpleMovieState(app);
			getStateManager().attach(sm);
			
			hudGuiState = new HUDGUIState(voiceThread, vcm, app);
//			hudGuiState = new HUDGUIState(app);
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

	/**
	 * @return
	 */
	public Router getRouter() {
		// TODO Auto-generated method stub
		return this.router;
	}

    @Override
    public void destroy() {
        super.destroy();
        executor.shutdown();
    }

    
    
}
