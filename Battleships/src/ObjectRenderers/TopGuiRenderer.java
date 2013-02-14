package ObjectRenderers;

public class TopGuiRenderer extends ObjectRenderer{

	
	public TopGuiRenderer(){
		WorldRenderer.renderers.add(this);
	}
	@Override
	public void draw() {
		object.getSprite().draw(WorldRenderer.batch);
		
	}

}
