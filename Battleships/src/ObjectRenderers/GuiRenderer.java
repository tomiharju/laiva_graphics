package ObjectRenderers;

public class GuiRenderer extends ObjectRenderer{

	
	public GuiRenderer(){
		WorldRenderer.renderers.add(this);
	}
	@Override
	public void draw() {
		object.getSprite().draw(WorldRenderer.batch);
		
	}

}
