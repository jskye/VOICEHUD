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

/**
 * Route.java class
 */
public class Route {
	
	private String viewname;
	private String viewid;
	private String viewcontroller;
	
	public Route(String viewname, String viewid, String viewcontroller){
		this.viewname = viewname;
		this.viewid = viewid;
		this.viewcontroller = viewcontroller;
	}

	/**
	 * @return
	 */
	public Object getViewId() {
		return this.viewid;
	}

	/**
	 * @return the viewname
	 */
	public String getViewname() {
		return viewname;
	}

	/**
	 * @return the viewcontroller
	 */
	public String getViewcontroller() {
		return viewcontroller;
	}
	
	

	
}
