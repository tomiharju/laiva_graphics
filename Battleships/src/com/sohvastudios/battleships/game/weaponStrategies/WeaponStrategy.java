package com.sohvastudios.battleships.game.weaponStrategies;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectRenderers.ProjectileRenderer;

public interface WeaponStrategy {
	
	public void calculatePathAndHits(Vector3 target);
	public void setRadarAnimationData(ArrayList<Vector3> path, ArrayList<Vector3> hits,ObjectController parent);
	public boolean animate(Sprite sprite,Vector3 position);
	public void dealDamage(Vector3 point,ProjectileRenderer renderer);
	public void findBlastAffectedShips();
	public Vector3 simulate(Vector3 position);
	public void getShipsInRange(Vector3 target);

}
