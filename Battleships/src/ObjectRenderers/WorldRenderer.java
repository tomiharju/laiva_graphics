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
	
	private ShapeRenderer debugrenderer;
	
	
	public WorldRenderer(){
		renderers 	= new ArrayList<ObjectRenderer>();
		batch 		= new SpriteBatch();
		batch.enableBlending();
		setupCamera();
		
		debugrenderer= new ShapeRenderer();
		
	}
	
	public void setupCamera(){
		
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.setToOrtho(true,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(5,5,0);
		cam.update();
		
		ppux=Gdx.graphics.getWidth()/100;
		ppuy=Gdx.graphics.getHeight()/100;
		
	}
	
	public void draw(){
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
