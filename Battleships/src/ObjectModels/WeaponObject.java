package ObjectModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import ObjectControllers.ObjectController;
import ObjectControllers.ShipController;
import ObjectControllers.WeaponController;
import ObjectRenderers.ObjectRenderer;
import ObjectRenderers.ShipRenderer;
import ObjectRenderers.WeaponRenderer;
import ObjectRenderers.WorldRenderer;

public class WeaponObject extends ModelObject {
	
	public enum WeaponType{
		CANNON("30mmHE.png",0.5f),			//Basic cannon, radius of 10m x 10m
		TORPEDO("missile.png",1.0f);
		
		
		private String file;
		private float radius;
		
		private WeaponType(String f,float r){
			file=f;
			radius=r;
		}
		private String getFile(){
			return file;
		}
		public float getRadius(){
			return radius;
		}
	
		
	}
	
	
	private WeaponType weapon;
	
	public WeaponObject(WeaponType weapon,ObjectController controller, ObjectRenderer renderer){
		setController(controller);
		setRenderer(renderer);
		
		position		= new Vector3(controller.pollPosition());
		bounds 			= new Rectangle(controller.pollBounds());
		
		sprite 			= new Sprite(new Texture(Gdx.files.internal("data/weapons/"+weapon.getFile())));
		sprite.setSize(bounds.getWidth(),bounds.getHeight());
		sprite.setPosition(position.x, position.y);
		WorldObject.objects.add(this);
		this.renderer.addGraphics(sprite);
		this.weapon=weapon;
		((WeaponRenderer) renderer).createAnimation(weapon);
	}
	
	public int getWeaponType(){
		return weapon.ordinal(); 
	}
	@Override
	public void update() {
		position=controller.pollPosition();
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
