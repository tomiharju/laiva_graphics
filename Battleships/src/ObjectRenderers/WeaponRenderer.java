package ObjectRenderers;

public class WeaponRenderer extends ObjectRenderer {

	public WeaponRenderer(){
		WorldRenderer.renderers.add(this);
	}
	@Override
	public void draw() {
		object.getSprite().draw(WorldRenderer.batch);
		
	}

}
