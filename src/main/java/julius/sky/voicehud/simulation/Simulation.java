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
	
	String viewtoroute = null;
	boolean postroute = false;

	
	public enum POSTROUTES {
		CONT_RECOG("continue"),
		STOP_RECOG("stop");
		
		private final String text;

	    /**
	     * @param text
	     */
	    private POSTROUTES(final String text) {
	        this.text = text;
	    }

	    /* (non-Javadoc)
	     * @see java.lang.Enum#toString()
	     */
	    @Override
	    public String toString() {
	        return text;
	    }
	}


	/**
	 * @returns the postroute boolean
	 */
	public boolean getPostRoute();


	/**
	 * @param command
	 * @return
	 */
	public SimulationRouteList getSimulationRouteList(String command);


	/**
	 * @param command
	 * @return
	 */
	public abstract Route getRoute(String command);
	
	
}
