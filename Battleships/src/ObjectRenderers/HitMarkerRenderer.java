package ObjectRenderers;

import ObjectModels.HitMarkerObject;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HitMarkerRenderer extends ObjectRenderer{

	
	public HitMarkerRenderer(){
		WorldRenderer.renderers.add(this);
	}
	@Override
	public void draw(SpriteBatch batch) {
		if(object.isVisible())
			graphics.draw(batch,((HitMarkerObject)object).alpha);
		
	}

	@Override
	public void addGraphics(Sprite s) {
		graphics=s;
		
	}

}
