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

import julius.sky.voicehud.core.router.Route;


/**
 * GenericSimulation.java class
 */
public interface Simulation {
	
	public boolean continueAfterRoute = false;


	// at the moment a simulation doesnt really define much.
	// it couldve rather been a SimulationRouter
	// but there could be other methods that are common to simulation worth adding.
	// TODO: Refactor
	// to that end, it makes sense to refactor SimpleMovieState into Simulation
	// instead of having App holding both a simulation and movie state independently.
	// SimRouter could still be abstracted out to implement router.
	
	
	/**
	 * @returns the postroute boolean
	 */
	public boolean continueAfterRoute();


	/**
	 */
	public SimulationRouteList getSimulationRouteList();


	/**
	 * @param command
	 * @return
	 */
	public abstract Route getRoute(String command);
	
	
}
