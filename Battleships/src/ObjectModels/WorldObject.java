package ObjectModels;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;

import ObjectControllers.BottomGuiController;
import ObjectControllers.ObjectController;
import ObjectControllers.SeaController;
import ObjectControllers.ShipController;
import ObjectControllers.TopGuiController;
import ObjectControllers.WorldController;
import ObjectModels.ShipObject.ShipType;
import ObjectRenderers.BottomGuiRenderer;
import ObjectRenderers.ObjectRenderer;
import ObjectRenderers.SeaRenderer;
import ObjectRenderers.ShipRenderer;
import ObjectRenderers.TopGuiRenderer;
import ObjectRenderers.WorldRenderer;


public class WorldObject extends ModelObject {
	
	public static ArrayList<ModelObject> objects;
	
	//Static objects
	private SeaObject 		sea;
	private TopGuiObject 	topGui;	
	private BottomGuiObject botGui;
		
	//Ships
	ShipObject rowboat = new ShipObject(ShipType.ROWBOAT,new ShipController(),new ShipRenderer());
	
	public WorldObject(){
		objects 		= new ArrayList<ModelObject>();
		sea			 	= new SeaObject(new SeaController(),new SeaRenderer());
		topGui			= new TopGuiObject(new TopGuiController(), new TopGuiRenderer());
		botGui			= new BottomGuiObject(new BottomGuiController(),new BottomGuiRenderer());
		
		
	}
	
	public void update(){
		for(ModelObject o: objects)
			o.update();
		
	}
	



	@Override
	public Sprite getSprite() {
		
		return sprite;
	}
	
	@Override
	public ObjectController getController() {
		return controller;
	}

	@Override
	public void setController(ObjectController controller) {
		this.controller=controller;
		
	}

	@Override
	public ObjectRenderer getRenderer() {
		return renderer;
	}

	@Override
	public void setRenderer(ObjectRenderer renderer) {
		this.renderer=renderer;
		
	}


}
