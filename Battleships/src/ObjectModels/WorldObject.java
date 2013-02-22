package ObjectModels;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ObjectControllers.BotGuiController;
import ObjectControllers.MapController;
import ObjectControllers.ObjectController;
import ObjectControllers.SeaController;
import ObjectControllers.ShipController;
import ObjectControllers.TopGuiController;
import ObjectControllers.WorldController;
import ObjectModels.ShipObject.ShipType;
import ObjectRenderers.BotGuiRenderer;
import ObjectRenderers.MapRenderer;
import ObjectRenderers.ObjectRenderer;
import ObjectRenderers.SeaRenderer;
import ObjectRenderers.ShipRenderer;
import ObjectRenderers.TopGuiRenderer;
import ObjectRenderers.WorldRenderer;


public class WorldObject extends ModelObject {
	
	public static ArrayList<ModelObject> objects;
	
	//Game components
	private SeaObject 		sea_o;
	private MapObject		map_o;
	private TopGuiObject 	topGui_o;	
	private BottomGuiObject botGui_o;
	
	
	
	public WorldObject(){
		//Create objects with controllers and renderers
		objects 			= new ArrayList<ModelObject>();
		sea_o			 	= new SeaObject(new SeaController(),new SeaRenderer());
		map_o				= new MapObject(new MapController(),new MapRenderer());
		topGui_o			= new TopGuiObject(new TopGuiController(), new TopGuiRenderer());
		botGui_o			= new BottomGuiObject(new BotGuiController(),new BotGuiRenderer());
	
		//Link needed controllers
		((SeaController)sea_o.getController()).linkMapController(map_o.getController());
		
		
		new ShipObject(ShipType.ROWBOAT,new ShipController(100,(float) (Gdx.graphics.getHeight()*0.9)),new ShipRenderer());
		new ShipObject(ShipType.MOTORBOAT,new ShipController(250,(float) (Gdx.graphics.getHeight()*0.9)),new ShipRenderer());
	//	new ShipObject(ShipType.BATTLESHIP,new ShipController(),new ShipRenderer());
	}
	
	
	
	
	public void update(){
		for(ModelObject o: objects)
			o.update();
		
	}
	
	@Override
	public void setController(ObjectController controller) {
		this.controller=controller;
		
	}

	

	@Override
	public void setRenderer(ObjectRenderer renderer) {
		this.renderer=renderer;
		
	}


}