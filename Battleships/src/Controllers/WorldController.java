package Controllers;

import Models.World;

//Handle input while in GameScreen

public class WorldController {
	
	//Co-operative controllers
	GUIController guiController;
	CellController cellController;
	
	private World world;
	
	
	public WorldController(World w){
		this. world=w;
		guiController = new GUIController(this);
		cellController = new CellController(this);
	}
	public void update(float delta){
		

		
	
	}
	
	public void handleTouchAtBoard(float xy_x,float xy_y,float xz_x,float xz_z){
		if(xz_x!=0)
			cellController.handleTouch(xz_x, xz_z,0);
		else if(xy_x!=0)
			cellController.handleTouch(xy_x,xy_y,1);
		
	}
	
	public void handleTouchAtUpperGUI(float x, float z){
		guiController.handleTouch(x,z,0);
	}
	public void handleTouchAtShipSelector(){
		
	}
	World getWorld(){
		return world;
	}

}
