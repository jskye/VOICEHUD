package julius.sky.voicehud;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;

import julius.sky.voicehud.core.hud.HUDGUI;
import julius.sky.voicehud.core.voice.VoiceCommandManager;
import julius.sky.voicehud.plugins.musicplayer.MusicPlayer;

//public class App extends SimpleApplication
public class App
{
	
//	private static MusicPlayer musicPlayer;
	private HUDGUI HUDGUI;
	private VoiceCommandManager vcm;
	private Thread HUDThread;
	private Thread voiceThread;
	private App app;
	
    public static void main( String[] args )
    {
        System.out.println( "starting app" );
        App thisApp = new App();
        thisApp.app = thisApp;
        thisApp.start(); // start the app

    }
    
//    public static void main( String[] args ){
    public void start(){
//    @Override
//	public void simpleInitApp() {
		try {

			HUDGUI = new HUDGUI(app);
			HUDThread = new Thread(HUDGUI);
			HUDGUI.run();
			

			vcm = new VoiceCommandManager(app);
			voiceThread = new Thread(vcm);
			 // run as deamon to terminate this thread when app terminates.
			voiceThread.setDaemon(true);
			voiceThread.start(); 
			
			
//			guiThread.sleep(2000);
//			System.out.println("try open new layer");
//			hudGUI.openLayer(GuiLayer.VIEW_DEFAULT);
//			hudGUI.getNifty().addXml("Interface/TEST.xml");

//        	MusicPlayer musicPlayer = new MusicPlayer();
//			musicPlayer.run();
//			musicPlayer.createMusicNode("Elvis_Presley", "Burning_Love");
//			musicPlayer.playMusic();


			
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

	public HUDGUI getHUDGUI() {
		return HUDGUI;
	}

	public VoiceCommandManager getVcm() {
		return vcm;
	}
    
    
}
