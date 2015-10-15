package julius.sky.voicehud.core.voice.tts;

import java.beans.PropertyVetoException;  
import java.util.Locale;  
import javax.speech.AudioException;  
import javax.speech.Central;  
import javax.speech.EngineException;  
import javax.speech.EngineStateError;
import javax.speech.synthesis.SpeakableAdapter;
import javax.speech.synthesis.Synthesizer;  
import javax.speech.synthesis.SynthesizerModeDesc;  
import javax.speech.synthesis.Voice;  

public class TTS implements Runnable{

	  SynthesizerModeDesc desc;  
	  Synthesizer synthesizer;  
	  Voice voice;  
	  String texttospeak;
	 
	  public TTS() throws EngineException, AudioException, EngineStateError, PropertyVetoException {
		  this.init("kevin16");
//		  this.texttospeak = text;
	  }
	  
	  
	  public void setTextToSpeak(String text){
		  this.texttospeak = text;
	  }
	  
		public void run() {
			
			try {
				this.doSpeak(texttospeak);
			} catch (EngineException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AudioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}  
	  
	  public void init(String voiceName)  
	      throws EngineException, AudioException, EngineStateError,  
	      PropertyVetoException {  
	   if (desc == null) {  
	    System.setProperty("freetts.voices",  
	        "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");  
	    desc = new SynthesizerModeDesc(Locale.US);  
	    Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");  
	    synthesizer = Central.createSynthesizer(desc);  
	    synthesizer.allocate();  
	    synthesizer.resume();  
	    SynthesizerModeDesc smd =  
	        (SynthesizerModeDesc) synthesizer.getEngineModeDesc();  
	    Voice[] voices = smd.getVoices();  
	    Voice voice = null;  
	    for (int i = 0; i < voices.length; i++) {  
	     if (voices[i].getName().equals(voiceName)) {  
	      voice = voices[i];  
	      break;  
	     }  
	    }  
	    synthesizer.getSynthesizerProperties().setVoice(voice);  
	   }  
	  }  
	  
	  public void doSpeak(String speakText)  
		      throws EngineException, AudioException, IllegalArgumentException,  
		      InterruptedException {  
			   synthesizer.speakPlainText(speakText, null);  
		   synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);  
		   
		  }  
	  
	  // terminates the TTS
	  public void terminate() throws EngineException, EngineStateError {  
	   synthesizer.deallocate();  
	  }  


	 } 
