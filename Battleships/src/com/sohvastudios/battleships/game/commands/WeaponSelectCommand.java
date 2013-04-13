package com.sohvastudios.battleships.game.commands;

import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.RadarController;

public class WeaponSelectCommand implements Command {

	private int weaponNumber;
	private RadarController controller;
	public WeaponSelectCommand(RadarController controller, int w){
		this.weaponNumber=w;
		this.controller=controller;
	}
	@Override
	public void execute(ObjectController target) {
		controller.selectWeapon(weaponNumber);
		
	}

}
