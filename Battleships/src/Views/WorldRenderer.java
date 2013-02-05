package Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import Models.World;

public class WorldRenderer implements Renderer{

	private static final float CAMERA_WIDTH = 10f;
	private static final float CAMERA_HEIGHT = 7f;
	private World world;
	private final PerspectiveCamera cam;
	private SpriteBatch batch;

	
	//TestVariables
	public WorldRenderer(World w){
		
		this. world=w;
		
		cam = new PerspectiveCamera(67,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		cam.position.set(50,150,150);
		cam.direction.set(0,-1,-1);
		cam.near=1;
		cam.far=250;
		batch = new SpriteBatch();
		
	}
	
	public void render(){
	
		cam.update();		
		 
		batch.setProjectionMatrix(cam.combined);
		batch.setTransformMatrix(world.seaOrientation());
		batch.begin();
		for(Sprite[] s:world.getSea()){
			for(Sprite sprite:s){
				sprite.draw(batch);
			}
		}
		batch.setTransformMatrix(world.mapOrientation());
		
		for(Sprite[] s:world.getMap()){
			for(Sprite sprite:s){
				sprite.draw(batch);
			}
		}
		
		
		batch.end();
	
	}
	
	public void setSize(int w, int h){
		
	}
	
	public PerspectiveCamera getCamera(){
		return cam;
	}
	
	
	
}
