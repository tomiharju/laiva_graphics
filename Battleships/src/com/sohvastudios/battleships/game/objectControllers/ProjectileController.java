package com.sohvastudios.battleships.game.objectControllers;

public class ProjectileController extends ObjectController {

	public ProjectileController(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	@Override
	public void initialize(ObjectController parent) {
		this.parent=parent;
		parent.addlist.add(this);
	}

	

	

}
