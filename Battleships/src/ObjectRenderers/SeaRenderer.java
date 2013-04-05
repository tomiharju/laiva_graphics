package ObjectRenderers;

import Utilities.AssetStorage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class SeaRenderer extends ObjectRenderer {

	Animation seaAnimation;
	Texture seaSheet;
	TextureRegion[] seaFrames;
	TextureRegion currentFrame;
	private boolean animate;
	private float stateTime;
	private Vector3 animPos;

	public SeaRenderer() {
		WorldRenderer.renderers.add(this);
		
	}

	public void createAnimation() {
		
		animate = false;
		stateTime = 0;
		seaSheet = AssetStorage.manager.get("data/effects/oceansheet.png");

		TextureRegion[][] tmp = TextureRegion.split(seaSheet,
				seaSheet.getWidth() / 4, seaSheet.getHeight() / 4);

		seaFrames = new TextureRegion[4 * 4];
		int index = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				seaFrames[index++] = tmp[i][j];

			}
		}
		seaAnimation = new Animation(0.15f, seaFrames);
		
	}

	@Override
	public void draw(SpriteBatch batch) {
		if (object.isVisible()){
			graphics.draw(batch);
		}
		else
			return;
	}

	@Override
	public void addGraphics(Sprite s) {
		graphics = s;

	}
	public void animateSea(){
		animPos = object.getPosition();
	}

}
