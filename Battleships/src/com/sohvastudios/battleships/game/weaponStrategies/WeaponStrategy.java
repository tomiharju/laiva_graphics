package com.sohvastudios.battleships.game.weaponStrategies;

import com.badlogic.gdx.math.Vector3;

public interface WeaponStrategy {
	
	public Vector3 setTarget(Vector3 target);
	public boolean update(Vector3 position);
	public void getShipsInRange(Vector3 target, float radius);
	public Vector3 getPos();

}
