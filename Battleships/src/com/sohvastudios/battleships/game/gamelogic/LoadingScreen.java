package com.sohvastudios.battleships.game.gamelogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sohvastudios.battleships.game.core.Main;
import com.sohvastudios.battleships.game.utilities.AssetStorage;

public class LoadingScreen implements Screen {

	Main main;
	OrthographicCamera cam;
	static final float VIEWPORT_WIDTH = 10; // *100meters
	static final float VIEWPORT_HEIGHT = 15;// *100meters
	public SpriteBatch batch;

	private BitmapFont font;
	private Sprite loadingImage;
	float percentLoaded = 0;
	private StringBuilder charSequence;

	public LoadingScreen(Main m) {
		this.main = m;
		cam = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT); // Dimension
																		// of
																		// the
																		// world,
																		// 10x15(km)
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"),
				Gdx.files.internal("data/font.png"), false);
		charSequence = new StringBuilder();
		charSequence.append(String.valueOf(percentLoaded));
		font.setScale(0.05f);

		cam.position.set(VIEWPORT_WIDTH / 2, VIEWPORT_HEIGHT / 2, 0);
		AssetStorage.manager.load("data/LoadingScreen.png", Texture.class);
		AssetStorage.manager.finishLoading();
		Texture bg = AssetStorage.manager.get("data/LoadingScreen.png",
				Texture.class);
		loadingImage = new Sprite(bg);
		loadingImage.setSize(10, 15);
		loadAssets();
	}

	public void loadAssets() {
		loadBackgrounds();
		loadExplosion();
		loadShips();
		loadWeapons();
		loadGuiObjects();
		loadEffects();

	}

	public void loadExplosion() {
		AssetStorage.manager.load("data/explosion/exp0.png", Texture.class);
	}

	public void loadEffects() {
		AssetStorage.manager.load("data/effects/smokesheet.png", Texture.class);
		AssetStorage.manager.load("data/effects/sweep.png", Texture.class);
	}

	public void loadShips() {
		AssetStorage.manager.load("data/ships/assaultboat.png", Texture.class);
		AssetStorage.manager.load("data/ships/cruiser.png", Texture.class);
		AssetStorage.manager.load("data/ships/frigate.png", Texture.class);
		AssetStorage.manager.load("data/ships/hydrofoil.png", Texture.class);
		AssetStorage.manager.load("data/ships/oiler.png", Texture.class);
	}

	public void loadGuiObjects() {
		AssetStorage.manager.load("data/guiobjects/button_fire.png",Texture.class);
		AssetStorage.manager.load("data/guiobjects/button_ready.png",Texture.class);
		AssetStorage.manager.load("data/guiobjects/crosshair.png",Texture.class);
		AssetStorage.manager.load("data/hitmarker.png", Texture.class);
	}

	public void loadWeapons() {
		AssetStorage.manager.load("data/weapons/w0.png", Texture.class);
		AssetStorage.manager.load("data/weapons/w1.png", Texture.class);
		AssetStorage.manager.load("data/weapons/w2.png", Texture.class);
		AssetStorage.manager.load("data/weapons/w3.png", Texture.class);
		AssetStorage.manager.load("data/weapons/w4.png", Texture.class);
	}

	public void loadBackgrounds() {
		AssetStorage.manager.load("data/waterTexture.png", Texture.class);
		AssetStorage.manager.load("data/radarTexture.png", Texture.class);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, .0f, .0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		percentLoaded = (float) Math.ceil(AssetStorage.manager.getProgress() * 10);
		charSequence.delete(0, charSequence.length());
		charSequence.append(String.valueOf(percentLoaded));
		charSequence.append("%");
		if (AssetStorage.manager.update()) {
			main.startGame();
		}

		cam.update();
		batch.setProjectionMatrix(cam.combined);

		batch.begin();
		font.setColor(1f, 0f, 0f, 1f);
		loadingImage.draw(batch);
		font.draw(batch, charSequence.toString(), 3, 5);

		batch.end();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
