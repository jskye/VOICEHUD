/**
 * University of Newcastle
 * CSSE, Sofware Engineering 
 * FINAL INDIVIDUAL THESIS PROJECT 
 * VoiceHud
 * Author: Julius Myszkowski
 * Student Id: c3155112
 * Email: julius.skye@gmail.com
 */
package julius.sky.voicehud.simulation.driving;

import java.util.ArrayList;

import julius.sky.voicehud.core.router.Route;
import julius.sky.voicehud.core.router.Router;
import julius.sky.voicehud.simulation.SimulationRouteList;
import julius.sky.voicehud.simulation.Simulation;
import julius.sky.voicehud.simulation.driving.DrivingSimulationRouteList;

/**
 * DrivingSimulation.java class
 */
public class DrivingSimulation implements Simulation{
	
	private int[] speeds = {10,20,30,40,50,60,70,80,80,80,80,90,100,100,100,80,60,40,30,20};
	private int[] speedLimits = {40,40,60,60,60,60,80,80,80,100,100,80,80,80,60,60,60,40,40,40};
	private SimulationRouteList drivingSimulationRoutes;
	private boolean postroute;
	private String route;
	
	public DrivingSimulation(){
		this.drivingSimulationRoutes = new DrivingSimulationRouteList();
	}
	
	
	// given a command, this method returns the HUD layer to load 
	// and returns continue if recognition should continue, stop if not.
	// 
	@Override
	public Route getRoute(String command){
		  
		 Route route = null;
		  
		  if (command.startsWith("exit") || command.startsWith("stop") ){
			  setPostRoute(false);
		  }
          
          if (command.equals("hud") || command.equals("hide")) {
			  setPostRoute(true);
        	  route = drivingSimulationRoutes.getRoute("DRIVERHUD");
          }
          
          if (command.equals("time") || command.equals("clock")) {
			  setPostRoute(true);
        	  route = drivingSimulationRoutes.getRoute("CLOCK");
          }
          
          if (command.equals("date")) {
        	  setPostRoute(true);
        	  route = drivingSimulationRoutes.getRoute("DATE");
          }
          
//          if (command.equals("music")) {
//        	  setPostRoute(true);
//        	  return DrivingSimulationRoutes.GuiLayer.MUSIC;
//          }
          
          if (command.equals("messages")) {
        	  setPostRoute(true);
        	  route = drivingSimulationRoutes.getRoute("MESSAGES");
          }
          
          if (command.equals("fuel")) {
        	  setPostRoute(true);
        	  route = drivingSimulationRoutes.getRoute("FUEL");
          }
          
          
          
		return route;

	}
	
	
	/**
	 * @param b
	 */
	private void setPostRoute(boolean pr) {
		this.postroute = pr;
	}


	/* (non-Javadoc)
	 * @see julius.sky.voicehud.simulation.Simulation#getPostRoute()
	 */
	@Override
	public boolean getPostRoute() {
		// TODO Auto-generated method stub
		return postroute;
	}


	/* (non-Javadoc)
	 * @see julius.sky.voicehud.simulation.Simulation#getSimulationRouteList(java.lang.String)
	 */
	@Override
	public SimulationRouteList getSimulationRouteList(String command) {
		// TODO Auto-generated method stub
		return null;
	}


}
