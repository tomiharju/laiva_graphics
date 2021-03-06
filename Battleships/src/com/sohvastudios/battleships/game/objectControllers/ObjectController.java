package com.sohvastudios.battleships.game.objectControllers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.objectModels.ModelObject;

public abstract class ObjectController {

	protected ModelObject object;
	public ObjectController parent;
	protected Vector3 position;
	protected Rectangle bounds;
	protected boolean orientation_changed;
	protected boolean selected;
	protected boolean isHidden;
	protected Rectangle clear_bounds;
	protected Rectangle area_bounds_sea;
	protected Rectangle area_bounds_radar;
	
	public ArrayList<ObjectController> controllers;
	public ArrayList<ObjectController> addlist;
	public ArrayList<ObjectController> removelist;
	
	public ObjectController(float x, float y, float width, float height) {
		position 			= new Vector3(x, y, 0);
		bounds				= new Rectangle(x - width / 2, y - height / 2, width, height);
		clear_bounds 		= new Rectangle();
		area_bounds_sea 	= new Rectangle(-10,-10, 20, 20);
		area_bounds_radar 	= new Rectangle(-3f,-3f,6,6);
		parent				= null;
	}

	
	public abstract void initialize(ObjectController parent);
		
	
	
	
	
	public ModelObject getObject() {
		return object;
	}

	public void setObject(ModelObject object) {
		this.object = object;
	}

	public void setPosition(Vector3 vector3) {
		position.set(vector3);
		bounds.x = position.x - bounds.width / 2;
		bounds.y = position.y - bounds.height / 2;
		Sprite sprite = object.sprite;
		sprite.setPosition(bounds.x,bounds.y);
			
	}

	public Vector3 pollPosition() {
		return position;
	}

	public Rectangle pollBounds() {
		return bounds;
	}

	public void select() {
		selected = true;
	}

	public void deSelect() {
		selected = false;
	}

	public boolean isSelected() {
		return selected;
	}



	public void hide() {
	};

	public void show() {
	};

	
	public boolean handleInputDown(Vector3 touchPoint) {
		return false;
	};

	public void handleInputUp(Vector3 pos) {
	};

	public boolean handleInputDrag(Vector3 pos) {
		return false;
	};

	public void rotate90() {
	}


}
