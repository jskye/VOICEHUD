package julius.sky.voicehud;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;

import julius.sky.voicehud.core.hud.HUDGUI;
import julius.sky.voicehud.core.voice.voicecommand.VoiceCommandManager;
import julius.sky.voicehud.plugins.musicplayer.MusicPlayer;

//public class App extends SimpleApplication
public class App
{
	
//	private static AssetManager assetManager;
//	private static MusicPlayer musicPlayer;
//	private static HUDGUI hudGUI;
	
//    public static void main( String[] args )
//    {
////        System.out.println( "starting app" );
////        App app = new App();
////        app.setShowSettings(false);
////        app.start(); // start the app
//
//    }
    
    public static void main( String[] args ){

//    @Override
//	public void simpleInitApp() {
		try {
        	
			HUDGUI hudGUI = new HUDGUI();
			hudGUI.run();

//			VoiceCommandManager vcm = new VoiceCommandManager();
//			Thread vcmThread = new Thread(vcm);
//			 // run as deamon to terminate this thread when app terminates.
//			vcmThread.setDaemon(true);
//			vcmThread.start(); 
			
			// sharing assetmanager between threads might be a mistake.
//        	Thread musicPlayerThread = new Thread(new MusicPlayer());
        	MusicPlayer musicPlayer = new MusicPlayer();
			musicPlayer.run();
			musicPlayer.createMusicNode("Elvis_Presley", "Burning_Love");


			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
