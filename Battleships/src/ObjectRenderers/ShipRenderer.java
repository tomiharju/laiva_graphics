package ObjectRenderers;

public class ShipRenderer extends ObjectRenderer{

	public ShipRenderer(){
		WorldRenderer.renderers.add(this);
	}
	@Override
	public void draw() {
		object.getSprite().draw(WorldRenderer.batch);
		
	}

	
}
