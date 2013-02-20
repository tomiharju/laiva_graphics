package ObjectRenderers;

public class SeaRenderer extends ObjectRenderer
{

	
	
	
	public SeaRenderer(){
		WorldRenderer.renderers.add(this);
	}
	@Override
	public void draw() {
		
		object.getSprite().draw(WorldRenderer.batch);
		
	}

	

}
