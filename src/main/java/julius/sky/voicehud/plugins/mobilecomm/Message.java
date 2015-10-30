package julius.sky.voicehud.plugins.mobilecomm;

public class Message {
	
	private String sender;
	private String intendedRecepient;
	private String messageContent;
	private String timeSent;
	
	public Message(String messageContent, String sender, String intendedRecepient){
		this.messageContent = messageContent;
		this.sender = sender;
		this.intendedRecepient = intendedRecepient;
		
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getIntendedRecepient() {
		return intendedRecepient;
	}

	public void setIntendedRecepient(String intendedRecepient) {
		this.intendedRecepient = intendedRecepient;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getTimeSent() {
		return timeSent;
	}

	public void setTimeSent(String timeSent) {
		this.timeSent = timeSent;
	}
	
	

}
