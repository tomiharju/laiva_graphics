package Controllers;

public class GUIController extends Controller{
	private final int TOP=0;
	private final int BOTTOM=1;
	
	public GUIController(WorldController wc){
		worldController=wc;
	}
	
	
	

	@Override
	public void handleTouch(float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleTouch(float x, float y, int axis) {
		switch(axis){
		case TOP:
			break;
		case BOTTOM:
			break;
		}
		
	}

}
