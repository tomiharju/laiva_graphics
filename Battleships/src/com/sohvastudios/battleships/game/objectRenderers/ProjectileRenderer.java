package com.sohvastudios.battleships.game.objectRenderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.utilities.AssetStorage;

public class ProjectileRenderer extends ObjectRenderer {

	Animation explosionAnimation;
	Texture explosionSheet;
	TextureRegion[] explosionFrames;
	TextureRegion currentFrame;
	
	Animation splashAnimation;
	Texture splashSheet;
	TextureRegion[] splashFrames;
	
	
	private boolean animate;
	private boolean animateMiss;
	private float stateTime;
	private Vector3 animPos;

	public ProjectileRenderer() {
		
	}
	public void createSplashAnimation(){
		animateMiss = false;
		stateTime = 0;
		splashSheet = AssetStorage.manager.get("data/effects/splash.png");

		TextureRegion[][] tmp = TextureRegion.split(splashSheet,
				splashSheet.getWidth() / 6, splashSheet.getHeight() / 4);

		splashFrames = new TextureRegion[6 * 4];
		int index = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				splashFrames[index++] = tmp[i][j];
			}
		}
		splashAnimation = new Animation(0.05f, splashFrames);
	}

	public void createAnimation(int weaponType) {

		animate = false;
		stateTime = 0;
		explosionSheet = AssetStorage.manager.get("data/explosion/exp0.png");

		TextureRegion[][] tmp = TextureRegion.split(explosionSheet,
				explosionSheet.getWidth() / 6, explosionSheet.getHeight() / 5);

		explosionFrames = new TextureRegion[6 * 5];
		int index = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				explosionFrames[index++] = tmp[i][j];
			}
		}
		explosionAnimation = new Animation(0.05f, explosionFrames);

	}

	@Override
	public void draw(SpriteBatch batch) {
		if (object.isVisible()) {
			graphics.draw(batch);
		}
		if (animate) {
			stateTime += Gdx.graphics.getDeltaTime();
			currentFrame = explosionAnimation.getKeyFrame(stateTime, false);
			batch.draw(currentFrame, (animPos.x - 1f), (animPos.y - 1f), 2, 2);
			if (explosionAnimation.isAnimationFinished(stateTime)) {
				animate = false;
				object.dispose();
			}
		}
		else if(animateMiss){
			stateTime += Gdx.graphics.getDeltaTime();
			currentFrame = splashAnimation.getKeyFrame(stateTime, false);
			batch.draw(currentFrame, (animPos.x - 1f), (animPos.y - 1f), 1, 1);
			if (splashAnimation.isAnimationFinished(stateTime)) {
				animateMiss = false;
				object.dispose();
			}
		}
	}

	@Override
	public void addGraphics(Sprite s) {
		graphics = s;
	}

	public void animateExplosion(Vector3 pos) {
		animate = true;
		stateTime = 0;
		animPos = pos;
	}
	public void animateSplash(Vector3 pos){
		animateMiss = true;
		stateTime = 0;
		animPos = pos;
	}

}
