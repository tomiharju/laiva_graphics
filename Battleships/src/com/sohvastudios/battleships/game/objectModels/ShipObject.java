package com.sohvastudios.battleships.game.objectModels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.ShipController;
import com.sohvastudios.battleships.game.objectModels.WeaponObject.Weapon;
import com.sohvastudios.battleships.game.objectRenderers.ObjectRenderer;
import com.sohvastudios.battleships.game.objectRenderers.ShipRenderer;
import com.sohvastudios.battleships.game.utilities.AssetStorage;

public class ShipObject extends ModelObject {

	public enum ShipType {
		// Lenght, Width, sprite
		ASSAULTBOAT(1, 1, "assaultboat.png"),
		HYDROFOIL(1.5f, 1,"hydrofoil.png"), 
		FRIGATE(2.5f, 1, "frigate.png"), 
		BATTLESHIP(5, 2, "cruiser.png"),
		OILER(3.5f, 1.5f, "oiler.png");

		private float lenght, width, hp;
		private String file;

		private ShipType(float l, float w, String f) {
			lenght = l;
			width = w;
			hp = l * w * 1084; // Damage is based on ship size, 1084 is the dmg
								// done by direct hit.
			file = f;
		}

		public float getHp() {
			return hp;
		}

		public float getLenght() {
			return lenght;
		}

		public float getWidth() {
			return width;
		}

		private String getFile() {
			return file;
		}

	}

	private float hitpoints;
	private float maxHitpoints;
	private float hpPercentage;
	private boolean destroyed = false;
	
	public Weapon shipWeapon;
	
	public ShipObject(ShipType ship, ShipController controller,
			ShipRenderer renderer) {
		setController(controller);
		setRenderer(renderer);
		position = controller.pollPosition();
		bounds = controller.pollBounds();

		sprite = new Sprite(AssetStorage.manager.get(
				"data/ships/" + ship.getFile(), Texture.class));
		sprite.setSize(bounds.getWidth(), bounds.getHeight());
		sprite.setPosition(position.x - bounds.width / 2, position.y
				- bounds.height / 2);

		WorldObject.objects.add(this);
		hitpoints = ship.getHp();
		maxHitpoints = hitpoints;
		hpPercentage = 100;
		this.renderer.addGraphics(sprite);

		installWeapon(ship.ordinal());
	}
	public void installWeapon(int shiptype){
		switch(shiptype){
		case 0:
			shipWeapon = Weapon.GRENADE;
			break;
		case 1:
			shipWeapon = Weapon.MISSILE;
			break;
		case 2:
			shipWeapon = Weapon.MORTAR;
			break;
		case 3:
			shipWeapon = Weapon.NAVALGUN;
			break;
		case 4:
			shipWeapon = Weapon.PHALANX;
			break;
		}
	}

	public void dealDamage(float dmg) {
		hitpoints -= dmg;
		hpPercentage = (hitpoints / maxHitpoints) * 100;
		if (hpPercentage <= 20) {
			destroyed = true;
			((ShipRenderer) renderer).animateSmoke(position);
		}

	}

	public boolean isDestroyed() {
		return destroyed;
	}

	@Override
	public void setController(ObjectController controller) {
		this.controller = (ShipController) controller;
		this.controller.setObject(this);

	}

	@Override
	public void setRenderer(ObjectRenderer renderer) {
		this.renderer = (ShipRenderer) renderer;
		this.renderer.setObject(this);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
