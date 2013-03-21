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
	
	public enum Weapon{
		CANNON("30mmHE.png",0.5f),			//0 Grenade, radius of 5m x 5m
		MISSILE("missile.png",1.0f),		//1 Homing missile, moves 2unit towards closest ship
		MORTAR("missile.png",1.0f),			//2 Launches a cluster of mortars 
		NAVALGUN("missile.png",1.5f),		//3 Massive shell fired from big battleships
		PHALANX("missile.png",0.5f);		//4 Gattling gun, fires multiple small rounds at an area
		
		private String file;
		private float radius;
		
		
		private Weapon(String f,float r){
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
	
	
	private Weapon weapon;
	
	public WeaponObject(Weapon weapon,ObjectController controller, ObjectRenderer renderer){
		setController(controller);
		setRenderer(renderer);
		position 		= controller.pollPosition();
		bounds 			= controller.pollBounds();
		
		sprite 			= new Sprite(new Texture(Gdx.files.internal("data/weapons/"+weapon.getFile())));
		sprite.setSize(bounds.getWidth(),bounds.getHeight());
		sprite.setPosition(position.x-bounds.width/2, position.y-bounds.height/2);
	
		WorldObject.objects.add(this);
		this.renderer.addGraphics(sprite);
		this.weapon=weapon;
		((WeaponRenderer) renderer).createAnimation(weapon);
	}
	
	public Weapon getWeapon(){
		return weapon;
	}
	@Override
	public void update() {
		
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
