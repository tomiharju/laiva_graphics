package Controllers;

abstract class Controller {
	
	protected WorldController worldController;
	
	public abstract void handleTouch(float x, float y);
	public abstract void handleTouch(float x, float y,int axis);
	
}
