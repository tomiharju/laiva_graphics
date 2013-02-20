package ObjectControllers;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

import ObjectModels.ModelObject;

public class TopGuiController extends ObjectController {

	public static ArrayList<ObjectController> shipControllers;
	
	
	public TopGuiController(){
		shipControllers = new ArrayList<ObjectController>();
	}
	public static void touchDown(float x,float y){
		System.out.println("Touch down at top gui");
		Vector2 touchPoint = new Vector2(x,y);
		for(ObjectController sc: shipControllers){
			if(touchPoint.dst(sc.pollPosition())<15){
				WorldController.activeController=sc;
			}
			
		}
	}
	
	
}
