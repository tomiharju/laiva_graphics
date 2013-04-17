package com.sohvastudios.battleships.game.objectModels;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.ProjectileController;
import com.sohvastudios.battleships.game.objectControllers.SeaContainer;
import com.sohvastudios.battleships.game.objectControllers.ShipController;
import com.sohvastudios.battleships.game.objectRenderers.ObjectRenderer;
import com.sohvastudios.battleships.game.objectRenderers.ProjectileRenderer;
import com.sohvastudios.battleships.game.objectRenderers.RadarRenderer;
import com.sohvastudios.battleships.game.objectRenderers.SeaRenderer;
import com.sohvastudios.battleships.game.utilities.AssetStorage;
import com.sohvastudios.battleships.game.weaponStrategies.AnimateStrategy;
import com.sohvastudios.battleships.game.weaponStrategies.GrenadeStrategy;
import com.sohvastudios.battleships.game.weaponStrategies.MissileStrategy;
import com.sohvastudios.battleships.game.weaponStrategies.MortarStrategy;
import com.sohvastudios.battleships.game.weaponStrategies.NavalGunStrategy;
import com.sohvastudios.battleships.game.weaponStrategies.TorpedoStrategy;
import com.sohvastudios.battleships.game.weaponStrategies.WeaponStrategy;


public class ProjectileObject extends ModelObject {


	private Set<ShipController> ships_hit;
	private float radius;
	private WeaponStrategy strategy;

	public ProjectileObject(ObjectController controller,ObjectRenderer renderer,ObjectController parent, int weaponType) {
		setController(controller);
		setRenderer(renderer);
		position = controller.pollPosition();
		bounds = controller.pollBounds();

		sprite = new Sprite(AssetStorage.manager.get("data/weapons/w"
				+ weaponType + ".png", Texture.class));
		sprite.setSize(bounds.getWidth(), bounds.getHeight());
		sprite.setPosition(position.x - bounds.width / 2, position.y
				- bounds.height / 2);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		

		((ProjectileRenderer) this.renderer).createAnimation(0);
		((ProjectileRenderer) this.renderer).createSplashAnimation();
		
		
		controller.initialize(parent);
		WorldObject.addlist.add(this);
		setStrategy(weaponType);
		this.renderer.addGraphics(sprite);
	
		
	}

	public void setStrategy(int weapon_type) {
		switch (weapon_type) {
		case -1:
			strategy = new AnimateStrategy();
			break;
		case 0:
			strategy = new GrenadeStrategy();
			break;
		case 1:
			strategy = new MissileStrategy(controller.parent);
			break;
		case 2:
			strategy = new MortarStrategy();
			break;
		case 3:
			strategy = new NavalGunStrategy();
			break;
		case 4:
			strategy = new TorpedoStrategy(controller.parent);
			break;
		default:
			break;

		}
	}

	
	public void calculateDamage(Vector3 target, float radius){
		//Calculate damage, send result, animate damage, send true when finished calculating
		ships_hit = new HashSet<ShipController>();
		strategy.setFlightPath(((SeaContainer)controller.parent).flightpath,target);
		this.radius=radius;
		getShipsInRange(strategy.simulate(target),radius);
		addToSea();
		animateDamage();
	}
	public void animateOnRadar(ArrayList<Vector3> path, ArrayList<Vector3> hits,ObjectController parent){
		strategy.setRadarAnimationData(path, hits, parent);
		addToRadar();
		animateDamage();
	}
	public void animateDamage(){
		setVisible();
	}
	public void getShipsInRange(Vector3 pos, float radius) {
		Vector3 hitIndicator = new Vector3();
		for (ObjectController oc : controller.parent.controllers) {
			if(oc instanceof ShipController){
			if (!ships_hit.contains(oc)) {
				hitIndicator.set(pos.x, pos.y, 0);
				if (oc.pollBounds().contains(hitIndicator.x, hitIndicator.y)) {
					((SeaContainer)controller.parent).hitspot.add(new Vector3(hitIndicator.x, hitIndicator.y,0));
					ships_hit.add((ShipController) oc);
					continue;
				}
				for (int a = 0; a <= 360; a++) {
					float x = (float) (pos.x + Math.cos(Math.toRadians(a))
							* radius);
					float y = (float) (pos.y + Math.sin(Math.toRadians(a))
							* radius);
					hitIndicator.set(x, y, 0);
					if (oc.pollBounds().contains(hitIndicator.x, hitIndicator.y)&& !ships_hit.contains(oc)) {
						ships_hit.add((ShipController) oc);
						((SeaContainer)controller.parent).hitspot.add(new Vector3(hitIndicator.x, hitIndicator.y,0));

					}
				}
			}
		}
		}
	
	}

	@Override
	public void update() {
		if(isVisible()){
			if (strategy.animate(sprite,position)) {
				setHidden();
				strategy.dealDamage(ships_hit, position, radius,((ProjectileRenderer) renderer));
			}
			controller.setPosition(position);
		
		}
	}

	public void addToSea(){
		SeaRenderer.objectsAtSea.add(renderer);
	}
	public void addToRadar(){
		RadarRenderer.objectsAtRadar.add(renderer);
	}
	@Override
	public void setController(ObjectController controller) {
		this.controller = (ProjectileController) controller;
		this.controller.setObject(this);

	}

	@Override
	public void setRenderer(ObjectRenderer renderer) {
		this.renderer = (ProjectileRenderer) renderer;
		this.renderer.setObject(this);

	}

}
