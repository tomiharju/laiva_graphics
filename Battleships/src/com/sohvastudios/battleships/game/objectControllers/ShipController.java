package com.sohvastudios.battleships.game.objectControllers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class ShipController extends ObjectController {

	public ShipController(float x, float y, float w, float h) {
		super(x, y, w, h);
		ShipPlacementView.shipControllers.add(this);

	}

	@Override
	public void handleInputUp(Vector3 pos) {

	}

	@Override
	public void handleInputDrag(Vector3 pos) {
		if (!area_bounds.contains(pos.x, pos.y))
			return;
		boolean legalmove = true;
		clear_bounds.set(pos.x - bounds.width / 2, pos.y - bounds.height / 2,
				bounds.width, bounds.height);
		for (ShipController sc : ShipPlacementView.shipControllers) {
			if (!sc.equals(this)) {
				if (sc.pollBounds().overlaps(clear_bounds)
						|| !area_bounds.contains(clear_bounds)) {
					legalmove = false;
				}

			}

		}

		if (legalmove) {
			setPosition(pos);
		} else {
			legalmove = true;

			// hit on y-axis
			if (pos.y > bounds.y + bounds.height || pos.y < bounds.y) {

				position.x = pos.x;
				clear_bounds.set(position.x - bounds.width / 2, position.y
						- bounds.height / 2, bounds.width, bounds.height);
				for (ShipController sc : ShipPlacementView.shipControllers)
					if (!sc.equals(this))
						if (sc.pollBounds().overlaps(clear_bounds)
								|| !area_bounds.contains(clear_bounds))
							legalmove = false;
				if (legalmove)
					setPosition(position);

			}

			else if (pos.x < bounds.x || pos.x > bounds.x + bounds.width) {
				position.y = pos.y;
				clear_bounds.set(position.x - bounds.width / 2, position.y
						- bounds.height / 2, bounds.width, bounds.height);
				for (ShipController sc : ShipPlacementView.shipControllers)
					if (!sc.equals(this))
						if (sc.pollBounds().overlaps(clear_bounds)
								|| !area_bounds.contains(clear_bounds))
							legalmove = false;
				if (legalmove)
					setPosition(position);

			}

		}

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
		for (ShipController sc : ShipPlacementView.shipControllers) {
			if (!sc.equals(this)) {
				if (sc.pollBounds().overlaps(tempRect)
						|| !area_bounds.contains(tempRect)) {
					legalmove = false;

				}

			}

		}

		if (legalmove) {
			bounds.set(tempRect);
			Sprite sprite = object.getSprite();
			sprite.rotate90(true);
			sprite.setSize(bounds.width, bounds.height);
			setPosition(position);
			System.out.println("Pos " + bounds.x + " " + bounds.y);
		}

	}

	public void hide() {
		object.setHidden();
	}

	public void show() {
		object.setVisible();
	}

	@Override
	public void handleInputDown(Vector3 pos) {
		// TODO Auto-generated method stub

	}

}
