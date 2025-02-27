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
import java.util.List;

import julius.sky.voicehud.core.router.Route;
import julius.sky.voicehud.simulation.SimulationRouteList;

/**
 * DrivingSimulationRoutes.java class
 */
public class DrivingSimulationRouteList extends SimulationRouteList{
	
	List<Route> drivingSimRouteList = new ArrayList<Route>();
		
	public DrivingSimulationRouteList(){

		drivingSimRouteList.add(new Route("DRIVER_HUD_VIEW", "DRIVERHUD", "HUDGUIController"));
		drivingSimRouteList.add(new Route("HUD_VIEW", "HUD", "HUDGUIController"));
		drivingSimRouteList.add(new Route("MESSAGES_VIEW", "MESSAGES", "MessagesController"));
		drivingSimRouteList.add(new Route("DATE_VIEW", "DATE", "DateController"));
		drivingSimRouteList.add(new Route("CLOCK_VIEW", "CLOCK", "ClockController"));
		drivingSimRouteList.add(new Route("FUEL_VIEW", "FUEL", "FuelController"));
		drivingSimRouteList.add(new Route("MAP_VIEW", "MAP", "MapController"));
		super.setRouteList(drivingSimRouteList);
	}

	/**
	 * @param route
	 */
	private void add(Route route) {
		super.addRoute(route);
	}
	
	public List<Route> getRouteList(){
		return super.getSimRoutes();
	}

	/**
	 * @param string
	 * @return
	 */
	public Route getRoute(String drivingSimCommand) {
		System.out.println(super.getSimRoutes());
		Route found = null;
		for(Route r : super.getSimRoutes()){
			System.out.println("r: " + r + r.getViewId());
			if(r.getViewId().equals(drivingSimCommand))
				found = r;
		}
		return found;
	}
}
