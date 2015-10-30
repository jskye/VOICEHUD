package julius.sky.voicehud.core.event;

import java.util.List;

import julius.sky.voicehud.core.hud.HUDGUIState;
import julius.sky.voicehud.core.router.Router;
import julius.sky.voicehud.simulation.Simulation;

public class EventManager {
	
	private HUDGUIState hudguistate;
	private Router router;
	private Simulation sim;
	private EventList eventList;
	
	public EventManager(HUDGUIState hudguistate, Simulation sim, Router router){
		this.hudguistate = hudguistate;
		this.router = router;
		this.sim = sim;
		
		this.eventList.add(
		new Event("speeding40zone", 35,"alertView","slow down children about"));
		this.eventList.add(
		new Event("detectedanimals", "alertView", "careful: animals about"));
		
	}
	
//	public void manageEvent(Event e){
//		
//		router.routeEvent(e);
//	}
	
	public void setSimulationsEventList(EventList eventlist){
		this.eventList = eventlist;
		
	}
	
	
	
}
