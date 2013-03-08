package ObjectControllers;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import ObjectModels.ShipObject;
import ObjectModels.WeaponObject.WeaponType;

public class DamageCalculator implements Runnable{

	Ellipse2D blast_area;
	Rectangle ship_bounds;
	int hits_total;
	Vector3 center,hit_marker;
	
	int radius;
	public DamageCalculator(Vector3 point,int radius,ShipObject ship){
		this.radius = radius;
		blast_area 	= new Ellipse2D.Double(point.x-radius/2,point.y-radius/2,radius,radius);
		ship_bounds	= new Rectangle(ship.getBounds());
		center 		= new Vector3(point);
		hit_marker 	= new Vector3();
	}
	
	@Override				
	public void run() {
		int radius_factor=radius;
		for(int i = 0 ; i<=radius ; i++){
			for(int a = 0 ; a<=360 ; a+=2){
				float x = (float) (center.x+Math.cos(Math.toRadians(a)*radius_factor));
				float y = (float) (center.y+Math.cos(Math.toRadians(a)*radius_factor));
				hit_marker.set(x, y, 0);
				if(ship_bounds.contains(hit_marker.x,hit_marker.y))
						hits_total++;
				
			}
			radius_factor--;
		}
		
	}
	
	public int start(){
		new Thread(this).run();
		return hits_total;
	}
	
	

}
