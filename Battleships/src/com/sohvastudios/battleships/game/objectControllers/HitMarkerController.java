package com.sohvastudios.battleships.game.objectControllers;

public class HitMarkerController extends ObjectController {

	public HitMarkerController(float x, float y, float width, float height) {
		super(x, y, width, height);
		ShootingMapView.markerControllers.add(this);
		// TODO Auto-generated constructor stub
	}

	public void hide(){
		object.setHidden();
	}
	public void show(){
		object.setVisible();
	}
}
