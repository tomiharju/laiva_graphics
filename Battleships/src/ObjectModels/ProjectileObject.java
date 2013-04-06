package ObjectModels;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import GameLogic.GameLogicHandler;
import ObjectControllers.ObjectController;
import ObjectControllers.ProjectileController;
import ObjectControllers.ShipController;
import ObjectControllers.ShipPlacementView;
import ObjectControllers.ShootingMapView;
import ObjectRenderers.ObjectRenderer;
import ObjectRenderers.ProjectileRenderer;
import ObjectRenderers.WeaponRenderer;
import Utilities.AssetStorage;
import Utilities.DamageCalculator;
import Utilities.Turn;
import WeaponStrategies.GrenadeStrategy;
import WeaponStrategies.MissileStrategy;
import WeaponStrategies.MortarStrategy;
import WeaponStrategies.NalvalGunStrategy;
import WeaponStrategies.PhalanxStrategy;
import WeaponStrategies.WeaponStrategy;

public class ProjectileObject extends ModelObject {

	private ArrayList<Vector2> result;
	private Set<ShipController> ships_hit;
	private float radius;
	private WeaponStrategy strategy;

	public ProjectileObject(ObjectController controller,
			ObjectRenderer renderer, int weaponType) {
		setController(controller);
		setRenderer(renderer);
		position = controller.pollPosition();
		bounds = controller.pollBounds();

		sprite = new Sprite(AssetStorage.manager.get("data/weapons/w"
				+ weaponType + ".png", Texture.class));
		sprite.setSize(bounds.getWidth(), bounds.getHeight());
		sprite.setPosition(position.x - bounds.width / 2, position.y
				- bounds.height / 2);

		WorldObject.objects.add(this);
		this.renderer.addGraphics(sprite);

		((ProjectileRenderer) this.renderer).createAnimation(0);
		setVisible();
		setStrategy(weaponType);
	}

	public void setStrategy(int weapon_type) {
		switch (weapon_type) {
		case 0:
			strategy = new GrenadeStrategy();
			break;
		case 1:
			strategy = new MissileStrategy();
			break;
		case 2:
			strategy = new MortarStrategy();
			break;
		case 3:
			strategy = new NalvalGunStrategy();
			break;
		case 4:
			strategy = new PhalanxStrategy();
			break;

		}
	}

	public void setTarget(Vector3 target, float radius) {
		strategy.setTarget(target);
		this.radius = radius;
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		ships_hit = new HashSet<ShipController>();
		result = new ArrayList<Vector2>();
	}

	

	public void dealDamage(Vector3 point, float radius) {
		getShipsInRange(position, radius);
		for (ShipController ship : ships_hit) {
			((ShipObject) ship.getObject()).dealDamage(new DamageCalculator(
					point, radius, (ShipObject) ship.getObject()).run());

		}
		((ProjectileRenderer) renderer).animateExplosion(position);
	}

	public void getShipsInRange(Vector3 pos, float radius) {
		Vector3 hitIndicator = new Vector3();
		ships_hit.clear();
		result.clear();

		for (ShipController sc : ShipPlacementView.shipControllers) {
			if (!ships_hit.contains(sc)) {
				hitIndicator.set(pos.x, pos.y, 0);
				if (sc.pollBounds().contains(hitIndicator.x, hitIndicator.y)) {
					result.add(new Vector2(hitIndicator.x, hitIndicator.y));
					ships_hit.add(sc);
					continue;
				}
				for (int a = 0; a <= 360; a++) {
					float x = (float) (pos.x + Math.cos(Math.toRadians(a))
							* radius);
					float y = (float) (pos.y + Math.sin(Math.toRadians(a))
							* radius);
					hitIndicator.set(x, y, 0);
					if (sc.pollBounds()
							.contains(hitIndicator.x, hitIndicator.y)
							&& !ships_hit.contains(sc)) {
						ships_hit.add(sc);
						result.add(new Vector2(hitIndicator.x, hitIndicator.y));

					}
				}
			}
		}
		GameLogicHandler.sendResult((new Turn(Turn.TURN_RESULT, result)));
	}

	@Override
	public void update() {
		if(isVisible()){
		sprite.setRotation((float) Math.toDegrees(Math.atan2(strategy.getPos().y
					- position.y, strategy.getPos().x - position.x)
					- Math.PI / 2));
		if (strategy.update(position)) {
				dealDamage(position, radius);
				setHidden();
			}
			controller.setPosition(position);
		
		}
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
