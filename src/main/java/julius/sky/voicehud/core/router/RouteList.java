/**
 * University of Newcastle
 * CSSE, Sofware Engineering 
 * FINAL INDIVIDUAL THESIS PROJECT 
 * VoiceHud
 * Author: Julius Myszkowski
 * Student Id: c3155112
 * Email: julius.skye@gmail.com
 */
package julius.sky.voicehud.core.router;

import java.util.ArrayList;
import java.util.List;

/**
 * RouteList.java class
 */
public interface RouteList {
	
	List<Route> routeList = new ArrayList<Route>();
	
	public default void addRoute(Route r){
		routeList.add(r);
	}

}
