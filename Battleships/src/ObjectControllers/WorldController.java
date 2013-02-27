package ObjectControllers;

import java.util.ArrayList;

import ObjectModels.WorldObject;


public class WorldController extends ObjectController{
	


		
		public static ArrayList<ObjectController> controllers;
		public ObjectController activeController;
		
		private final int MAP_CONTROLLER=1;
		private final int SEA_CONTROLLER=2;
		
		public WorldController(){
			controllers = new ArrayList<ObjectController>();
		
			
		}
		public void changeController(int c){
			
			switch(c){
			case MAP_CONTROLLER:{
				activeController = ((WorldObject)object).getMap_o().getController();
				break;
			}
			case SEA_CONTROLLER:{
				activeController = ((WorldObject)object).getSea_o().getController();
			}
				
			}
		}
		
		public void touchDown(float x,float y){
			activeController.handleInputDown(x, y);
		}
		public void touchUp(float x,float y){
			activeController.handleInputUp(x, y);
		}
		public void touchDragged(float x,float y){
			activeController.handleInputDrag(x, y);
		}
		

		@Override
		public void handleInputDown(float x, float y) {
			
			
		}
		@Override
		public void handleInputUp(float x, float y) {
			
			
		}
		@Override
		public void handleInputDrag(float x, float y) {
			
			
		}

		@Override
		public void hide() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void show() {
			// TODO Auto-generated method stub
			
		}
		
		
		

		
		
		
		
		
		
		
		

		
	
	
}
