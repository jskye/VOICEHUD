package julius.sky.voicehud.plugins.obd;

import java.util.HashMap;

public class OBDMonitor {
	
	// https://en.wikipedia.org/wiki/On-board_diagnostics#SAE_standards_documents_on_OBD-II
	
	private HashMap modeList;
	private int currentMode;
	
	public OBDMonitor(){
		this.modeList = new HashMap();
		modeList.put("01", "powertrain");
		modeList.put("02", "freezeframe");
		modeList.put("03", "emissionfaults");
		modeList.put("04", "cleardiaginfo");
		
		modeList.put("09", "carinfo");

	}
	
	public void setCurrentMode(int mode){
		this.currentMode = mode;
	}
}
