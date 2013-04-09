package com.sohvastudios.battleships.game.objectModels;



public class WeaponObject{

	public enum Weapon {
		GRENADE(0.5f), // 0 Grenade, radius of 5m x 5m
		MISSILE(1.0f), // 1 Homing missile, moves 2unit towards closest ship
		MORTAR(0.5F),
		NAVALGUN(1.5f), // 3 Massive shell fired from big battleships
		PHALANX(0.5f); // 4 Gattling gun, fires multiple small rounds at an area

		private float radius;

		private Weapon(float r) {
			radius = r;
		}

		public float getRadius() {
			return radius / 2;
		}

	}

	
}
