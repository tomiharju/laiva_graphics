package ObjectRenderers;

public class MapRenderer extends ObjectRenderer{

	
	public MapRenderer(){
		WorldRenderer.renderers.add(this);
	}
	@Override
	public void draw() {
		object.getSprite().draw(WorldRenderer.batch);
	}

}
