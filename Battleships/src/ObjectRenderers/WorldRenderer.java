package ObjectRenderers;

import java.util.ArrayList;

import ObjectModels.ModelObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;


public class WorldRenderer extends ObjectRenderer{
	
	
	public static ArrayList<ObjectRenderer> renderers;
	public static OrthographicCamera cam;
	public static SpriteBatch batch;
	public static float ppux,ppuy;
	static final float VIEWPORT_WIDTH=10;
	static final float VIEWPORT_HEIGHT=15;
	
	private ShapeRenderer debugrenderer;
	
	
	public WorldRenderer(){
		renderers 	= new ArrayList<ObjectRenderer>();
		batch 		= new SpriteBatch();
		batch.enableBlending();
		setupCamera();
		
		debugrenderer= new ShapeRenderer();
		
	}
	
	public void setupCamera(){
		
		cam = new OrthographicCamera(VIEWPORT_WIDTH,VIEWPORT_HEIGHT); //Dimension of the world, 10x15(km)
		//cam.setToOrtho(true,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(VIEWPORT_WIDTH / 2,VIEWPORT_HEIGHT / 2,0);
	
		
		ppux=Gdx.graphics.getWidth()/100;
		ppuy=Gdx.graphics.getHeight()/100;
		
	}
	
	public void draw(){
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		debugrenderer.setProjectionMatrix(cam.combined);
		batch.begin();
		for(ObjectRenderer o: renderers)
			o.draw();
		batch.end();
	
		debugrenderer.begin(ShapeType.Rectangle);
		for(ObjectRenderer o: renderers){
			Rectangle rect = o.getObject().getBounds();
			debugrenderer.setColor(1, 0, 0, 1);
		    debugrenderer.rect(rect.getX(), rect.getY(), rect.width, rect.height);

		}
		
		debugrenderer.end();
	}
	@Override
	public void setObject(ModelObject object) {
		this.object=object;
		
	}

	@Override
	public ModelObject getObject() {
		return object;
	}

	
	
	
}
