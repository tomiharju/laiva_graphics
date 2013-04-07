package com.sohvastudios.battleships.game.objectRenderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.utilities.AssetStorage;

public class ShipRenderer extends ObjectRenderer {

	Animation smokeAnimation;
	Texture smokeSheet;
	TextureRegion[] smokeFrames;
	TextureRegion currentFrame;
	private boolean animate;
	private float stateTime;
	private Vector3 animPos;

	public ShipRenderer() {
		WorldRenderer.renderers.add(this);
		createAnimation();
	}

	public void createAnimation() {

		animate = false;
		stateTime = 0;
		smokeSheet = AssetStorage.manager.get("data/effects/smokesheet.png");

		TextureRegion[][] tmp = TextureRegion.split(smokeSheet,
				smokeSheet.getWidth() / 6, smokeSheet.getHeight() / 5);

		smokeFrames = new TextureRegion[6 * 5];
		int index = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				smokeFrames[index++] = tmp[i][j];

			}
		}
		smokeAnimation = new Animation(0.033f, smokeFrames);
	}

	@Override
	public void draw(SpriteBatch batch) {
		if (object.isVisible()) {
			graphics.draw(batch);
			if (animate) {
				stateTime += Gdx.graphics.getDeltaTime();
				currentFrame = smokeAnimation.getKeyFrame(stateTime, true);
				batch.draw(currentFrame, (animPos.x - 0.75f),
						(animPos.y - 0.75f), 1.5f, 1.5f);

			}
		} else
			return;
	}

	@Override
	public void addGraphics(Sprite s) {
		graphics = s;
	}

	public void animateSmoke(Vector3 pos) {
		animate = true;
		stateTime = 0;
		animPos = pos;
	}

}
