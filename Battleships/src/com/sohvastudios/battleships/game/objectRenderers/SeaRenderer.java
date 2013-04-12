package com.sohvastudios.battleships.game.objectRenderers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SeaRenderer extends ObjectRenderer {

	public static ArrayList<ObjectRenderer> objectsAtSea = new ArrayList<ObjectRenderer>();
	

	
	public SeaRenderer() {
		objectsAtSea.add(this);
	}

	@Override
	public void draw(SpriteBatch batch) {
		graphics.draw(batch);
		
	}
	@Override
	public void addGraphics(Sprite s) {
		graphics = s;

	}

}
