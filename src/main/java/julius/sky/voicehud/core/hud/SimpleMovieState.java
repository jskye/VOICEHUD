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

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioRenderer;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3x.jfx.media.TextureMovie;
import com.jme3x.jfx.media.TextureMovie.LetterboxMode;
import com.sun.javafx.application.PlatformImpl;

public class SimpleMovieState extends AbstractAppState implements Runnable{

	  // access main application fields
	  private SimpleApplication app;
	  private Node              rootNode;
	  private AssetManager      assetManager;
	  private AppStateManager   stateManager;
	  private InputManager      inputManager;
	  private ViewPort          viewPort;
	  private ViewPort guiViewPort;
	  private AudioRenderer audioRenderer;
	  private RenderManager renderManager;
	  private Camera cam;
	  private FlyByCamera flyCam;
	
	private TextureMovie	textureMovie;
	private MediaPlayer		moviePlayer;
	private ViewPort movieViewPort;

	  private Node movieNode = new Node("movieNode");  // some custom class fields...    
	  public Node getMovieNode(){ return movieNode; }  // some custom methods... 
	
	
	public SimpleMovieState(App app){
		
	      System.out.println( "constructing Simple Movie" );
		  
		  PlatformImpl.startup(() -> {});

		  this.app = app;
		  this.inputManager = this.app.getInputManager();
		  this.assetManager = this.app.getAssetManager();
		  this.audioRenderer = this.app.getAudioRenderer();
		  this.renderManager = this.app.getRenderManager();
		  this.rootNode = this.app.getRootNode();

		  
//		  this.viewPort = this.app.getViewPort();
		  this.cam = this.app.getCamera();
	      this.movieViewPort = renderManager.createMainView("movieView", this.cam);
		  this.guiViewPort = this.app.getGuiViewPort();
		  this.flyCam = this.app.getFlyByCamera();
		
		}
	
	
	 @Override
	  public void initialize(AppStateManager stateManager, Application app) {
		  
	      System.out.println( "initialising Movie" );
		  super.initialize(stateManager, app); 
	      this.app = (SimpleApplication) app;          // cast to a more specific class


	    // init stuff that is independent of whether state is PAUSED or RUNNING
	      
	      // modify scene graph...
	      
	      // attach movie node to root node
	      this.app.getRootNode().attachChild(getMovieNode());
	      // attach root node to the movie viewport
	      this.movieViewPort.attachScene(this.rootNode);

	      this.initMovie();
	 }

	 @Override
	  public void cleanup() {
	    super.cleanup();
	    // unregister all my listeners, detach all my nodes, etc...
//	    this.movieViewPort.clearScenes();
	    this.app.getRootNode().detachChild(getMovieNode()); // modify scene graph...
//	    this.app.doSomethingElse();                 // call custom methods...
	  }

	  @Override
	  public void setEnabled(boolean enabled) {
	    // Pause and unpause
	    super.setEnabled(enabled);
	    if(enabled){
	      // init stuff that is in use while this state is RUNNING
	      this.app.getRootNode().attachChild(getMovieNode()); // modify scene graph...
//	      this.app.doSomethingElse();                 // call custom methods...
	    } else {
	      // take away everything not needed while this state is PAUSED
	    }
	  }

	  // Note that update is only called while the state is both attached and enabled.
	  @Override
	  public void update(float tpf) {
	    // do the following while game is RUNNING
//		this.movieViewPort.getScenes().get(0).scale(tpf);
	    this.app.getRootNode().getChild("movieNode").scale(tpf); // modify scene graph...
//	    x.setUserData(...);                                 // call some methods...
	  }


	public void initMovie() {
		
        System.out.println( "getting media" );

////     setDisplayFps(false);
//		 setDisplayStatView(false);
//		 getInputManager().setCursorVisible(true);
		 this.flyCam.setEnabled(true);
//		 
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
		this.moviePlayer = new MediaPlayer(media);
	    moviePlayer.setAutoPlay(true);
		this.moviePlayer.play();

		this.textureMovie = new TextureMovie(this.app, this.moviePlayer, LetterboxMode.VALID_LETTERBOX);
		this.textureMovie.setLetterboxColor(ColorRGBA.Black);

		final Geometry screenGeometry1 = new Geometry("ScreenGeometry1", new Quad(20, 20));

		final Material s1mat = new Material(this.assetManager, "com/jme3x/jfx/media/MovieShader.j3md");
		s1mat.setTexture("ColorMap", this.textureMovie.getTexture());
		s1mat.setInt("SwizzleMode", this.textureMovie.useShaderSwizzle());
		screenGeometry1.setMaterial(s1mat);
		this.rootNode.attachChild(screenGeometry1);
		
		this.cam.setLocation(new Vector3f(10, 10, 15));

	}

	public void destroy() {
//		super.destroy();
		this.moviePlayer.stop();
		PlatformImpl.exit();
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		this.initialize(stateManager, app);

	}

}