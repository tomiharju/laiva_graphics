package ObjectModels;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import ObjectControllers.ObjectController;
import ObjectControllers.ShipPlacementView;
import ObjectControllers.ShipController;
import ObjectRenderers.ObjectRenderer;
import ObjectRenderers.ShipRenderer;
import ObjectRenderers.WorldRenderer;

public class ShipObject extends ModelObject{

	public enum ShipType{
		ROWBOAT(10,10,10,"rowboat.png"),
		MOTORBOAT(20,10,20,"motorboat.png"),
		BATTLESHIP(50,10,50,"battleship.jpg");
		private int lenght,width,hp;
		private String file;
		private ShipType(int l,int w,int h,String f){
			lenght=l;
			width=w;
			hp=h;
			file=f;
		}
	
		private int getLenght(){
			return lenght;
		}
		private int getWidth(){
			return width;
		}
		private String getFile(){
			return file;
		}
		
	}
	
	
	
	
	
	
	
	public ShipObject(ShipType ship,ShipController controller,ShipRenderer renderer){
		setController(controller);
		setRenderer(renderer);
		bounds = new Rectangle(0,0,ship.getWidth()*WorldRenderer.ppux,ship.getLenght()*WorldRenderer.ppuy);
		
		
		position=new Vector2(controller.pollPosition());
		
		
		sprite 	=  new Sprite(new Texture(Gdx.files.internal("data/"+ship.getFile())));
		sprite.setSize(bounds.getWidth(),bounds.getHeight());
		
		WorldObject.objects.add(this);
		
	}
	
	
	
	
	@Override
	public void update() {
		position.lerp(controller.pollPosition(),0.1f);
		bounds.x=position.x-bounds.width/2;
		bounds.y=position.y-bounds.height/2;
		if(controller.pollOrientation()){
			float temp;
			temp = bounds.width;
			bounds.width=bounds.height;
			bounds.height=temp;
			sprite.rotate90(true);
			sprite.setSize(bounds.getWidth(),bounds.getHeight());
			controller.orientationConfirmed();
		}
		sprite.setPosition(position.x-sprite.getWidth()/2, position.y-sprite.getHeight()/2);
		
		
	}

	@Override
	public void setController(ObjectController controller) {
		this.controller=(ShipController)controller;
		this.controller.setObject(this);
		
	}

	@Override
	public void setRenderer(ObjectRenderer renderer) {
		this.renderer=(ShipRenderer)renderer;
		this.renderer.setObject(this);
		
	}

}
