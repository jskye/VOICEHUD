package julius.sky.voicehud.core.router;


public class Router {
		
		// the router class contains a map of commands to their views and controllers
		public enum GuiLayer 
		{
			// MAPS COMMAND(VIEW_NAME, VIEW_ID, VIEW_CONTROLLER)
			HUD("HUD_VIEW", "HUD", "HUDGUIController"), 
			MESSAGES("MESSAGES_VIEW", "MESSAGES", "MessagesController"), 
			TIME("CLOCK_VIEW","CLOCK", "ClockController"),
			DATE("DATE_VIEW", "DATE", "DateController");
			
			private String layerName;
			private String layerId;
			private String controller;
			
			
			GuiLayer(String layerName)
			{
				System.out.println("thisgetcalled");
				this.layerName = layerName;
			}
			
			GuiLayer(String layerName, String layerId, String controller)
			{
				this.layerName = layerName;
				this.layerId = layerId;
				this.controller = controller;
			}
			
			public String getLayerName()
			{
				return layerName;
			}
			
			public String getLayerId()
			{
				return layerId;
			}
			
			public String getControllerName()
			{
				return controller;
			}
		}
		
		
}