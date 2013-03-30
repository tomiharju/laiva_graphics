package ObjectRenderers;

import ObjectModels.WeaponObject.Weapon;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class WeaponRenderer extends ObjectRenderer {

	
	public WeaponRenderer(){
		WorldRenderer.renderers.add(this);
	}
	
	
	@Override
	public void draw(SpriteBatch batch) {
		if(object.isVisible())
			graphics.draw(batch);
		else
			return;
	}
	@Override
	public void addGraphics(Sprite s) {
		graphics=s;
	}

	

}
