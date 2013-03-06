package ObjectControllers;

import ObjectModels.WorldObject;


public class WorldController extends ObjectController{
	
		
		int state;
		private final  int SHIP_VIEW=1;
		private final  int MAP_VIEW=2;
		private ShipPlacementView shipView;
		private ShootingMapView mapView;
		private ObjectController active_view;
		public WorldController(){
			
		}
		public void initialize(){
			shipView=(ShipPlacementView) ((WorldObject) object).shipView().getController();
			mapView=(ShootingMapView) ((WorldObject) object).mapView().getController();
			active_view=shipView;
			shipView.show();
			mapView.hide();
			state=1;
		}
		
		public void changePlayerView(){
			if(state==2)
				state=1;
			else
				state=2;
			
			switch(state){
				case SHIP_VIEW:{
					shipView.show();
					mapView.hide();
					active_view=shipView;
				break;
			}
				case MAP_VIEW:{
					mapView.show();
					shipView.hide();
					active_view=mapView;
				break;
			}
				
			}
		}
		
		public void touchDown(float x,float y){
			active_view.handleInputDown(x, y);
			
		}
		public void touchUp(float x,float y){
			active_view.handleInputUp(x, y);
	
		}
		public void touchDragged(float x,float y){
			active_view.handleInputDrag(x, y);
		
		}
		

		

		
		
		
		

		
		
		
		
		
		
		
		

		
	
	
}
