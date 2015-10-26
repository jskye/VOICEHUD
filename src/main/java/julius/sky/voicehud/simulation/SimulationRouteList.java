/**
 * University of Newcastle
 * CSSE, Sofware Engineering 
 * FINAL INDIVIDUAL THESIS PROJECT 
 * VoiceHud
 * Author: Julius Myszkowski
 * Student Id: c3155112
 * Email: julius.skye@gmail.com
 */
package julius.sky.voicehud.simulation;

import java.util.List;

import julius.sky.voicehud.core.router.Route;
import julius.sky.voicehud.core.router.RouteList;

/**
 * Routes.java class
 */
public abstract class SimulationRouteList implements RouteList{
     public List<Route> simRoutes;

	/**
	 * @return the simRoutes
	 */
	public List<Route> getSimRoutes() {
		return simRoutes;
	}

	/**
	 * @param string
	 * @return
	 */
	public abstract Route getRoute(String string);
}
