package ObjectControllers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import ObjectModels.ShipObject;
import ObjectModels.WeaponObject;
import ObjectModels.WorldObject;
import ObjectModels.WeaponObject.WeaponType;


public class WorldController extends ObjectController{
	
		
		int state;
		private final  int SHIP_VIEW=1;
		private final  int MAP_VIEW=2;
		private ShipPlacementView shipView;
		private ShootingMapView mapView;
		private ObjectController active_view;
		public WorldController(float x, float y, float w, float h){
			super(x,y,w,h);
			
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
		
		
		public void calculateDamageTaken(Vector3 point, int weapon_type){
			int radius=0;
			for(WeaponType w : WeaponType.values())
				if(w.ordinal()==weapon_type){
					radius = w.getRadius();
				}
			for(ShipController sc : ShipPlacementView.shipControllers){
				if(sc.position.dst(point)<radius){
					((ShipObject) sc.getObject()).dealDamage(
							new DamageCalculator(point,radius,(ShipObject) sc.getObject()).start());
				}
					
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		public void touchDown(Vector3 touchPoint){
			active_view.handleInputDown(touchPoint);
			
		}
		public void touchUp(Vector3 pos){
			active_view.handleInputUp(pos);
	
		}
		public void touchDragged(Vector3 pos){
			active_view.handleInputDrag(pos);
		
		}
		

		

		
		
		
		

		
		
		
		
		
		
		
		

		
	
	
}
