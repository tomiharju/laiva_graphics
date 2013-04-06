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
import ObjectRenderers.ProjectileRenderer;
import ObjectRenderers.ShipRenderer;
import ObjectRenderers.WeaponRenderer;
import ObjectRenderers.WorldRenderer;
import Utilities.AssetStorage;

public class WeaponObject extends ModelObject {
	
	

	
	public enum Weapon{
		GRENADE(0.5f),			//0 Grenade, radius of 5m x 5m
		MISSILE(1.0f),		//1 Homing missile, moves 2unit towards closest ship
		MORTAR(1.0f),			//2 Launches a cluster of mortars 
		NAVALGUN(1.5f),		//3 Massive shell fired from big battleships
		PHALANX(0.5f);		//4 Gattling gun, fires multiple small rounds at an area
		
		
		private float radius;
		
		
		private Weapon(float r){
			radius=r;
		}
		
		public float getRadius(){
			return radius/2;
		}
	
		
	}
	
	
	private Weapon weapon;
	
	public WeaponObject(Weapon weapon,ObjectController controller, ObjectRenderer renderer){
		setController(controller);
		setRenderer(renderer);
		position 		= controller.pollPosition();
		bounds 			= controller.pollBounds();
		
		sprite 			= new Sprite(AssetStorage.manager.get("data/weapons/w"+weapon.ordinal()+".png",Texture.class));
		sprite.setSize(bounds.getWidth(),bounds.getHeight());
		sprite.setPosition(position.x-bounds.width/2, position.y-bounds.height/2);
	
		WorldObject.objects.add(this);
		this.renderer.addGraphics(sprite);
		this.weapon=weapon;
		
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
