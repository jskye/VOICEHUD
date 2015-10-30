package julius.sky.voicehud.core.event;

import java.util.ArrayList;
import java.util.List;

public class EventList {
	private List<Event> list;
	
	public EventList(){
		this.list = new ArrayList<Event>();
	}
	
	public void add(Event e){
		list.add(e);
	}
	
	public List<Event> getList(){
		return list;
	}

}
