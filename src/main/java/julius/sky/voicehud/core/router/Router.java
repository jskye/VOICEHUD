package julius.sky.voicehud.core.router;

import julius.sky.voicehud.core.hud.HUDGUIState;
import julius.sky.voicehud.simulation.Simulation;

import com.jme3.app.SimpleApplication;

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
 * Router class routes commands to their views 
 * (by loading layers that have associated controllers).
 */

public class Router {
		
	  private HUDGUIState hudgui;
	  private Simulation simulation;

		
	  public Router(HUDGUIState hudgui, Simulation sim){
		  this.hudgui = hudgui;
		  this.simulation = sim;
	  }
	
		/**
		 * @param command
		 * routes commands to layers
		 */
		public boolean routeCommand(String command) {
			// get layer to load.
			// TODO use drivingsimulationlogic to determine routes.
			System.out.println("the command to route: "+simulation.getRoute(command));
			System.out.println("the route to route to: "+simulation.getRoute(command));
			this.hudgui.openView(simulation.getRoute(command));
			boolean postRoute = this.simulation.continueAfterRoute();
			return postRoute;
		}

}