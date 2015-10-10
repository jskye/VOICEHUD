package julius.sky.voicehud;

import julius.sky.voicehud.core.hud.HUDGUI;
import julius.sky.voicehud.core.voice.VoiceCommandManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "starting app" );
        String[] dialogargs = {};
        String[] hudargs = {};

        try {
			HUDGUI hudgui = new HUDGUI();
			hudgui.run();
			
//			DialogDemo dd = new DialogDemo();
//			dd.run();
			
			VoiceCommandManager vcm = new VoiceCommandManager();
			Thread vcmThread = new Thread(vcm);
			 // run as deamon to terminate this thread when app terminates.
			vcmThread.setDaemon(true);
			vcmThread.start(); 

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
