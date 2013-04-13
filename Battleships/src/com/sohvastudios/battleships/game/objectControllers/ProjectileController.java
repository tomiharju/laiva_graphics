package com.sohvastudios.battleships.game.objectControllers;

public class ProjectileController extends ObjectController {

	public ProjectileController(float x, float y, float width, float height) {
		super(x, y, width, height);
	
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeObject(ObjectController obj) {
		object.dispose();
		
	}

	@Override
	public void cleanTrash() {
		guiControllers.removeAll(removeList);
		removeList.clear();
		
	}

}
