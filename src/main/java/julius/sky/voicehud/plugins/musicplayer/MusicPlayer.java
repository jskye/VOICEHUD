package julius.sky.voicehud.plugins.musicplayer;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;

public class MusicPlayer implements Runnable {
	private MusicDatabase musicDatabase;
	private AssetManager assetManager;
	private AudioNode currentMusic; 
	
	public MusicPlayer(AssetManager assetManager){
//	public MusicPlayer(){
		this.musicDatabase = new MusicDatabase();
		this.assetManager = assetManager;
	}
	
	public void createMusicNode(String artistName, String songName){
		// jme3 supports ogg and wav
		System.out.println("trying to play filename: " +" Sound/" + artistName + "-" + songName + ".ogg");
		// load streaiming ogg file.
		
		this.currentMusic = new AudioNode(this.assetManager, "Sound/" + artistName + "-" + songName + ".ogg", true); 
		this.currentMusic.setPositional(false); // play in headspace (same volume no matter where node is).
	}
	
	public void playMusic(){
		currentMusic.play();
	}
	
	public void stopMusic(){
		currentMusic.stop();
	}
	
	public void pauseMusic(){
		currentMusic.pause();
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}
}
