package ObjectModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import ObjectControllers.ObjectController;
import ObjectControllers.ShipController;
import ObjectControllers.WeaponController;
import ObjectRenderers.ObjectRenderer;
import ObjectRenderers.ShipRenderer;
import ObjectRenderers.WeaponRenderer;
import ObjectRenderers.WorldRenderer;

public class WeaponObject extends ModelObject {
	public enum WeaponType{
		CANNON("cannon.png",10,10),
		CROSSHAIR("crosshair.png",10,10);
		private String file;
		private int height;
		private int width;
		private WeaponType(String f,int h,int w){
			
			file=f;
			height=h;
			width=w;
		}
		private String getFile(){
			return file;
		}
		private int getWidth(){
			return width;
		}
		private int getHeight(){
			return height;
		}
		private int getWeaponNumber(){
			return this.ordinal();
		}
	}
	
	
	private int weaponType;
	
	public WeaponObject(WeaponType weapon,ObjectController controller, ObjectRenderer renderer){
		setController(controller);
		setRenderer(renderer);
		
		position=new Vector2(controller.pollPosition());
		bounds = new Rectangle(position.x,position.y,weapon.getWidth()*WorldRenderer.ppux,weapon.getHeight()*WorldRenderer.ppuy);
		
		weaponType=weapon.getWeaponNumber();
	
		
		
		sprite 	=  new Sprite(new Texture(Gdx.files.internal("data/"+weapon.getFile())));
		sprite.setSize(bounds.getWidth(),bounds.getHeight());
		sprite.setPosition(position.x, position.y);
		WorldObject.objects.add(this);
	}
	
	public int getWeapon(){
		return weaponType; 
	}
	@Override
	public void update() {
	
		position.lerp(controller.pollPosition(),0.1f);
		bounds.x=position.x-bounds.width/2;
		bounds.y=position.y-bounds.height/2;
		sprite.setPosition(position.x-sprite.getWidth()/2, position.y-sprite.getHeight()/2);
		
	}

	@Override
	public void setController(ObjectController controller) {
		this.controller=(WeaponController)controller;
		this.controller.setObject(this);
		
	}

	@Override
	public void setRenderer(ObjectRenderer renderer) {
		this.renderer=(WeaponRenderer)renderer;
		this.renderer.setObject(this);
		
	}

}
