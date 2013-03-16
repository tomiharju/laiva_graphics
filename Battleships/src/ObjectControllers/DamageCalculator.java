package ObjectControllers;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import ObjectModels.ShipObject;
import ObjectModels.WeaponObject.WeaponType;

public class DamageCalculator{


	Rectangle ship_bounds;
	int hits_total;
	Vector3 center,hit_marker;
	float duration;
	boolean running;
	float radius;
	
	
	public DamageCalculator(Vector3 point,float radius,ShipObject ship){
		this.radius = radius;
		ship_bounds	= new Rectangle(ship.getBounds());
		System.out.println("Ship bounds are "+ship_bounds.toString());
		center 		= new Vector3(point);
		hit_marker 	= new Vector3();
	}
	
	public int run() {
		
		duration=System.currentTimeMillis();
		float radius_factor=radius;
		for(float i = 0 ; i<=radius ; i+=0.1){	//10 circles in 1 unit
			for(int a = 0 ; a<=360 ; a+=2){
				float x = (float) (center.x+Math.cos(Math.toRadians(a))*radius_factor);
				float y = (float) (center.y+Math.sin(Math.toRadians(a))*radius_factor);
				hit_marker.set(x, y, 0);
				if(ship_bounds.contains(hit_marker.x,hit_marker.y))
						hits_total++;
				
			}
			radius_factor-=0.1;
		}
		float finaltime = System.currentTimeMillis()-duration;
		System.out.println("Algorithm took "+finaltime+" seconds.");
		System.out.println("Total hits "+ hits_total);
		return hits_total;
	}
	

	
		
	
		
	
	
	

}
