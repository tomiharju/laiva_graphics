package ObjectModels;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import ObjectControllers.ObjectController;
import ObjectControllers.ShipPlacementView;
import ObjectControllers.ShipController;
import ObjectRenderers.ObjectRenderer;
import ObjectRenderers.ShipRenderer;
import ObjectRenderers.WorldRenderer;

public class ShipObject extends ModelObject{

	public enum ShipType{
		ROWBOAT(1,1,"rowboat.png"),
		MOTORBOAT(2,1,"motorboat.png"),
		BATTLESHIP(5,1,"battleship.png");
		private int lenght,width,hp;
		private String file;
		private ShipType(int l,int w,String f){
			lenght=l;
			width=w;
			hp=l*w*10000;
			file=f;
		}
		public int getHp(){
			return hp;
		}
		public int getLenght(){
			return lenght;
		}
		public int getWidth(){
			return width;
		}
		private String getFile(){
			return file;
		}
		
	}
	
	
	private int hitpoints;
	
	
	
	
	public ShipObject(ShipType ship,ShipController controller,ShipRenderer renderer){
		setController(controller);
		setRenderer(renderer);
		position		= new Vector3(controller.pollPosition());
		bounds 			= new Rectangle(controller.pollBounds());
		
		sprite 			= new Sprite(new Texture(Gdx.files.internal("data/"+ship.getFile())));
		sprite.setSize(bounds.getWidth(),bounds.getHeight());
		sprite.setPosition(position.x,position.y);
		
		WorldObject.objects.add(this);
		hitpoints=ship.getHp();
		
	}

	
	public void dealDamage(int dmg){
		hitpoints-=dmg;
		
	}
	
	@Override
	public void update() {
		position.set(controller.pollPosition());
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
