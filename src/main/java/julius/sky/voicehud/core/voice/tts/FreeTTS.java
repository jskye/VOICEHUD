////package julius.sky.voicehud.core.voice.tts;
////
////
////import com.sun.speech.freetts.Voice;
////import com.sun.speech.freetts.VoiceManager;
//// 
////public class FreeTTS {
//// 
//// private static final String VOICENAME_kevin = "kevin";
//// private String text; // string to speech
//// 
//// public FreeTTS(String text) {
////  this.text = text;
//// }
//// 
//// public void speak() {
////  Voice voice;
////  VoiceManager voiceManager = VoiceManager.getInstance();
////  voice = voiceManager.getVoice(VOICENAME_kevin);
//////  voice.allocate();
////  voice.speak(text);
//// }
//// 
//// public static void main(String[] args) {
////  String text = "FreeTTS was written by the Sun Microsystems Laboratories "
////    + "Speech Team and is based on CMU's Flite engine.";
////  FreeTTS freeTTS = new FreeTTS(text);
////  freeTTS.speak();
//// }
////}
//
//package julius.sky.voicehud.core.voice.tts;
//
//import com.sun.speech.freetts.VoiceManager;
//
//public class FreeTTS {
//
//    public static void main(String[] args) {
//        Voice voiceKevin16 = new Voice("kevin16");
//
//        String[] thingsToSay = new String[]
//        {
//            "hi everybody",
//            "my name is kevin sixteen",
//            "my voice is built into free t t s",
//            "but it isn't very mellifluous",
//            "it could be worse, though",
//            "every time my friend alan tries to talk",
//            "about anything more exciting than what time it is",
//            "he barfs up a bunch of exceptions",
//            "and passes out",
//        };
//
//        voiceKevin16.say(thingsToSay);
//    }
//}
//
//class Voice
//{
//    private String name;
//    private com.sun.speech.freetts.Voice systemVoice;
//
//    public Voice(String name)
//    {
//        this.name = name;
//        this.systemVoice = VoiceManager.getInstance().getVoice(this.name);
//        this.systemVoice.allocate();
//    }
//
//    public void say(String[] thingsToSay)
//    {
//        for (int i = 0; i < thingsToSay.length; i++)
//        {
//            this.say( thingsToSay[i] );
//        }
//    }
//
//    public void say(String thingToSay)
//    {
//        this.systemVoice.speak(thingToSay);
//    }
//
//    public void dispose()
//    {
//        this.systemVoice.deallocate();
//    }
//}