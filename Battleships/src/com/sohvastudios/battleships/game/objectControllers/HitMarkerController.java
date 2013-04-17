package com.sohvastudios.battleships.game.objectControllers;

public class HitMarkerController extends ObjectController {
	
	public HitMarkerController(float x, float y, float width, float height) {
		super(x, y, width, height);
		

	}
	public void initialize(ObjectController parent){
		this.parent=parent;
		parent.addlist.add(this);
	}

	public void hide() {
		object.setHidden();
	}

	public void show() {
		object.setVisible();
	}
	
	
	}
