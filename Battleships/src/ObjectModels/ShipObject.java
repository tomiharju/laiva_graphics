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
			hp=l*w*2*1084;	//Damage is based on ship size, 1084 is the dmg done by direct hit.
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
	
	
	private float hitpoints;
	private float maxHitpoints;
	
	
	
	public ShipObject(ShipType ship,ShipController controller,ShipRenderer renderer){
		setController(controller);
		setRenderer(renderer);
		position 		= controller.pollPosition();
		bounds 			= controller.pollBounds();
		
		sprite 			= new Sprite(new Texture(Gdx.files.internal("data/ships/"+ship.getFile())));
		sprite.setSize(bounds.getWidth(),bounds.getHeight());
		sprite.setPosition(position.x-bounds.width/2, position.y-bounds.height/2);
		
		WorldObject.objects.add(this);
		hitpoints=ship.getHp();
		maxHitpoints=hitpoints;
		this.renderer.addGraphics(sprite);
	}

	
	public void dealDamage(float dmg){
		hitpoints-=dmg;
		System.out.println("Hitpoints left: "+(hitpoints/maxHitpoints)*100 + "%");
		if(hitpoints<=0){
			System.out.println("Ship Destroyed!");
			//render.drawSmoke();
		}
			
		
	}
	
	@Override
	public void update() {
		
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
