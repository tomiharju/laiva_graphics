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
		
		public void lockToShipView(){
			shipView.show();
			mapView.hide();
			active_view=shipView;
		}
		public void lockToMapView(){
			mapView.show();
			shipView.hide();
			active_view=mapView;
		}
	
		
		
		public void calculateDamageTaken(Vector3 point, int weapon_type){
			float radius=0;
			for(WeaponType w : WeaponType.values())
				if(w.ordinal()==weapon_type){
					radius = w.getRadius();
					System.out.println("Radius "+radius);
				}
			for(ShipController sc : ShipPlacementView.shipControllers){
				System.out.println("Bounds at "+sc.getObject().getBounds().toString());
				
				if(sc.position.dst(point)<radius){
					((ShipObject) sc.getObject()).dealDamage(
							new DamageCalculator(point,radius,(ShipObject) sc.getObject()).run());
				}
					
			}
			System.out.println("Calculation finished");
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
