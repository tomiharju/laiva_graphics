package com.sohvastudios.battleships.game.commands;

import com.sohvastudios.battleships.game.objectControllers.ObjectController;
import com.sohvastudios.battleships.game.objectControllers.RadarContainer;

public class WeaponSelectCommand implements Command {

	private int weaponNumber;
	private RadarContainer controller;
	public WeaponSelectCommand(RadarContainer controller, int w){
		this.weaponNumber=w;
		this.controller=controller;
	}
	@Override
	public void execute(ObjectController target) {
		controller.selectWeapon(weaponNumber);
		
	}

}
