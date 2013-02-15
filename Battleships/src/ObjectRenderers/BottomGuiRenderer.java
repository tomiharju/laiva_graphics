package ObjectRenderers;

public class BottomGuiRenderer extends ObjectRenderer{

	
	
	
	public BottomGuiRenderer(){
		WorldRenderer.renderers.add(this);
	}
	@Override
	public void draw() {
		object.getSprite().draw(WorldRenderer.batch);
		
	}

}
