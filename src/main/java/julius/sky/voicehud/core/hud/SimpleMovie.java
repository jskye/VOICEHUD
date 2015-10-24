/**
 * University of Newcastle
 * CSSE, Sofware Engineering 
 * FINAL INDIVIDUAL THESIS PROJECT 
 * VoiceHud
 * Author: Julius Myszkowski
 * Student Id: c3155112
 * Email: julius.skye@gmail.com
 */
package julius.sky.voicehud.core.hud;

/**
 * SimpleMovie.java class
 */
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import julius.sky.voicehud.App;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Quad;
import com.jme3x.jfx.media.TextureMovie;
import com.jme3x.jfx.media.TextureMovie.LetterboxMode;
import com.sun.javafx.application.PlatformImpl;

public class SimpleMovie extends SimpleApplication implements Runnable{

	private TextureMovie	textureMovie;
	private MediaPlayer		mp;
	private App app;

//	public static void main(final String[] args) {
//
//		PlatformImpl.startup(() -> {
//		});
//
//		final SimpleMovie app = new SimpleMovie();
//		app.start();
//		
//	}
	
	public SimpleMovie(App app){
		PlatformImpl.startup(() -> {});
		this.app = app;
	}

	@SuppressWarnings({ "restriction", "restriction" })
	@Override
	public void simpleInitApp() {
//		 setDisplayFps(false);
		 setDisplayStatView(false);
		 getInputManager().setCursorVisible(true);
		 getFlyByCamera().setEnabled(true);
		 
		@SuppressWarnings("restriction")
		final Media media = new Media("file:////Users/juliusskye/Desktop/FYPI/simplevoicehud/assets/Video/sydriveHQ.flv");
//		final Media media = new Media("http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv");
		// final Media media = new Media("file:///archive/movies/kamera/kotywalka1.mp4");
		// final Media media = new Media("http://techslides.com/demos/sample-videos/small.mp4");
		// final Media media = new Media(new File("/home/dwayne/tmp/small.mp4").toURI().toASCIIString());
		// final Media media = new Media(new File("/home/dwayne/tmp/output.mp4").toURI().toASCIIString());

		media.errorProperty().addListener(new ChangeListener<MediaException>() {

			@Override
			public void changed(final ObservableValue<? extends MediaException> observable, final MediaException oldValue, final MediaException newValue) {
				newValue.printStackTrace();
			}
		});
		this.mp = new MediaPlayer(media);
	    mp.setAutoPlay(true);
		this.mp.play();
//
		this.textureMovie = new TextureMovie(this, this.mp, LetterboxMode.VALID_LETTERBOX);
		this.textureMovie.setLetterboxColor(ColorRGBA.Black);

		final Geometry screen1 = new Geometry("Screen1", new Quad(20, 20));

		final Material s1mat = new Material(this.assetManager, "com/jme3x/jfx/media/MovieShader.j3md");
		s1mat.setTexture("ColorMap", this.textureMovie.getTexture());
		s1mat.setInt("SwizzleMode", this.textureMovie.useShaderSwizzle());
		screen1.setMaterial(s1mat);
		this.rootNode.attachChild(screen1);

		this.cam.setLocation(new Vector3f(10, 10, 15));

	}

	@Override
	public void destroy() {
		super.destroy();
		this.mp.stop();
		PlatformImpl.exit();
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		start();

	}

}