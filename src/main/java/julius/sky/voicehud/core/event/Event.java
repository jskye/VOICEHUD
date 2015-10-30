package julius.sky.voicehud.core.event;

public class Event {

	private String metricTriggerName;
	private int metricTriggerValue;

	private String resultingGUIView;
	
	public Event(String metricTriggerName, int metricTriggerValue, String resultingGUIView){
		this.metricTriggerName = metricTriggerName;
		this.metricTriggerValue = metricTriggerValue;
		this.resultingGUIView = resultingGUIView;
	}
	
	// event when there is no trigger value reached.	
	public Event(String metricTriggerName, int metricTriggerValue, String resultingGUIView, String text) {
		// TODO Auto-generated constructor stub
	}
	
	// event when there is no trigger value reached.
	public Event(String metricTriggerName, String resultingGUIView, String text) {
		// TODO Auto-generated constructor stub
	}

	public int getMetricTriggerValue() {
		return metricTriggerValue;
	}

	public void setMetricTriggerValue(int metricTriggerValue) {
		this.metricTriggerValue = metricTriggerValue;
	}
		
	

	
}
