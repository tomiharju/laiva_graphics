package ObjectRenderers;

public class BotGuiRenderer extends ObjectRenderer{

	
	
	
	public BotGuiRenderer(){
		WorldRenderer.renderers.add(this);
	}
	@Override
	public void draw() {
		object.getSprite().draw(WorldRenderer.batch);
		
	}

}
