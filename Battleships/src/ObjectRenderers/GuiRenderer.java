package ObjectRenderers;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class GuiRenderer extends ObjectRenderer{

	
	public GuiRenderer(){
		WorldRenderer.renderers.add(this);
		
	}
	@Override
	public void draw() {
		if(object.isVisible())
			graphics.draw(WorldRenderer.batch);
		else
			return;
		
	}
	@Override
	public void addGraphics(Sprite s) {
		graphics=s;
		
	}
	

}
