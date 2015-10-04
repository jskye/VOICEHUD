package julius.sky.voicehud;

import julius.sky.voicehud.core.hud.HUDGUI;
import julius.sky.voicehud.core.voice.Dialog;

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

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
