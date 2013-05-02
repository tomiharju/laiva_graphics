package com.sohvastudios.battleships.game.objectControllers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class ShipController extends ObjectController {
	

	public ShipController(float x, float y, float w, float h) {
		super(x, y, w, h);
		
	}
	public void initialize(ObjectController parent){
		this.parent=parent;
		parent.addlist.add(this);
		deSelect();
		
	}

	@Override
	public boolean handleInputDrag(Vector3 pos) {

		if(!area_bounds_sea.contains(pos.x,pos.y))
			return false;
		
		boolean legalmove = true;
		clear_bounds.set(pos.x - bounds.width / 2, pos.y - bounds.height / 2,
				bounds.width, bounds.height);
		for (ObjectController oc : parent.controllers) {
			if(oc instanceof ShipController){
				if (!oc.equals(this)) {
					if (oc.pollBounds().overlaps(clear_bounds) || !area_bounds_sea.contains(clear_bounds)) {
						legalmove = false;
						object.baseSprite.setColor(1,0,0,1);
						setPosition(pos);
					}
				}
			}
		}

		if (legalmove) {
			setPosition(pos);
			object.baseSprite.setColor(1,0,0,0);
		} else {
			legalmove = true;

			// hit on y-axis
			if (pos.y > bounds.y + bounds.height || pos.y < bounds.y) {

				position.x = pos.x;
				clear_bounds.set(position.x - bounds.width / 2, position.y
						- bounds.height / 2, bounds.width, bounds.height);
				for (ObjectController oc : parent.controllers)
					if(oc instanceof ShipController)
						if (!oc.equals(this))
							if (oc.pollBounds().overlaps(clear_bounds)|| !area_bounds_sea.contains(clear_bounds)){
								legalmove = false;
								object.baseSprite.setColor(1,0,0,1);
								setPosition(pos);
							}
				
				if (legalmove){
					setPosition(position);
					object.baseSprite.setColor(1,0,0,0);
				}
			}

			else if (pos.x < bounds.x || pos.x > bounds.x + bounds.width) {
				position.y = pos.y;
				clear_bounds.set(position.x - bounds.width / 2, position.y
						- bounds.height / 2, bounds.width, bounds.height);
				for (ObjectController oc : parent.controllers)
					if(oc instanceof ShipController)
						if (!oc.equals(this))
							if (oc.pollBounds().overlaps(clear_bounds)|| !area_bounds_sea.contains(clear_bounds)){
								legalmove = false;
								object.baseSprite.setColor(1,0,0,1);
								setPosition(pos);
							}
				
				if (legalmove){
					setPosition(position);
					object.baseSprite.setColor(1,0,0,0);
				}

			}

		}

	return true;

	}

	public void rotate90() {
		
		Rectangle tempRect = new Rectangle();
		tempRect.set(bounds);
		float temp;
		temp = tempRect.width;
		tempRect.width = tempRect.height;
		tempRect.height = temp;
		tempRect.x = position.x - tempRect.width / 2;
		tempRect.y = position.y - tempRect.height / 2;
		boolean legalmove = true;
		for (ObjectController oc : parent.controllers) {
			if(oc instanceof ShipController)
				if (!oc.equals(this)) {
					if (oc.pollBounds().overlaps(tempRect)	|| !area_bounds_sea.contains(tempRect)) {
						legalmove = false;
						object.baseSprite.setColor(1,0,0,1);
						bounds.set(tempRect);
						Sprite sprite = object.sprite;
						sprite.rotate90(true);
						sprite.setSize(bounds.width, bounds.height);
						setPosition(position);
						sprite = object.baseSprite;
						sprite.rotate90(true);
						sprite.setSize(bounds.width, bounds.height);

				}

			}

		}

		if (legalmove) {
			object.baseSprite.setColor(1,0,0,0);
			bounds.set(tempRect);
			Sprite sprite = object.sprite;
			sprite.rotate90(true);
			sprite.setSize(bounds.width, bounds.height);
			setPosition(position);
			sprite = object.baseSprite;
			sprite.rotate90(true);
			sprite.setSize(bounds.width, bounds.height);
		}

	}

	public void hide() {
		object.setHidden();
	}

	public void show() {
		object.setVisible();
	}

}
