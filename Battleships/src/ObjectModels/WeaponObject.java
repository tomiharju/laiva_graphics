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
		CANNON("cannon.png",1),
		TORPEDO("cannon.png",1),
		CROSSHAIR("crosshair.png",1);
		
		private String file;
		private int radius;
		
		private WeaponType(String f,int r){
			file=f;
			radius=r;
		}
		private String getFile(){
			return file;
		}
		public int getRadius(){
			return radius;
		}
	
		
	}
	
	
	private WeaponType weapon;
	
	public WeaponObject(WeaponType weapon,ObjectController controller, ObjectRenderer renderer){
		setController(controller);
		setRenderer(renderer);
		
		position		= new Vector3(controller.pollPosition());
		bounds 			= new Rectangle(controller.pollBounds());
		
		
		sprite 			= new Sprite(new Texture(Gdx.files.internal("data/"+weapon.getFile())));
		sprite.setSize(bounds.getWidth(),bounds.getHeight());
		sprite.setPosition(position.x, position.y);
		WorldObject.objects.add(this);
		
		this.weapon=weapon;
	}
	
	public WeaponType getWeapon(){
		return weapon; 
	}
	@Override
	public void update() {
		position.lerp(controller.pollPosition(),1f);
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
