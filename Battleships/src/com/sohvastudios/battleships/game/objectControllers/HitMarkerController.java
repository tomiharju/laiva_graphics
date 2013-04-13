package com.sohvastudios.battleships.game.objectControllers;

public class HitMarkerController extends ObjectController {
	
	public HitMarkerController(float x, float y, float width, float height) {
		super(x, y, width, height);
		

	}
	public void initialize(){
		RadarController.markerControllers.add(this);
	}

	public void hide() {
		object.setHidden();
	}

	public void show() {
		object.setVisible();
	}
	@Override
	public void removeObject(ObjectController obj) {
		RadarController.markerControllers.remove(this);
		object.dispose();
		
	}
	@Override
	public void cleanTrash() {
		guiControllers.removeAll(removeList);
		removeList.clear();
		
	}
}
