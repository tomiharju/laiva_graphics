package com.sohvastudios.battleships.game.objectModels;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.ProjectileController;
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
			strategy = new AnimateStrategy(controller.parent);
			break;
		case 0:
			strategy = new GrenadeStrategy(controller.parent);
			break;
		case 1:
			strategy = new MissileStrategy(controller.parent);
			break;
		case 2:
			strategy = new MortarStrategy(controller.parent);
			break;
		case 3:
			strategy = new NavalGunStrategy(controller.parent);
			break;
		case 4:
			strategy = new TorpedoStrategy(controller.parent);
			break;
		default:
			break;

		}
	}

	
	public void animateDamage(Vector3 target){
		strategy.calculatePathAndHits(target);
		addToSea();
		setVisible();
	}
	public void simulateDamage(ArrayList<Vector3> path, ArrayList<Vector3> hits,ObjectController parent){
		strategy.setRadarAnimationData(path, hits, parent);
		addToRadar();
		setVisible();
	}
		
	@Override
	public void update() {
		if(isVisible()){
			if (strategy.animate(sprite,position)) {
				setHidden();
				strategy.dealDamage(position,((ProjectileRenderer) renderer));
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
