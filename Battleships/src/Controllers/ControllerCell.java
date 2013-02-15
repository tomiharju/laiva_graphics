package Controllers;

public class ControllerCell extends Controller{
	
	private char letterCoordinates[] = {'x','A','B','C','D','E','F','G','H','I','J'};
	private final int MAP = 1;
	private final int SEA = 0;
	
	public ControllerCell(WorldController wc){
		worldController=wc;
	}

	@Override
	public void handleTouch(float x, float z,int axis) {
		switch(axis){
		case SEA:{
			int coordinateNumber = (int)Math.ceil(z/10);
			int coord2 = (int)Math.ceil(x/10);
			char coordinateCharacter = letterCoordinates[coord2];
			worldController.getWorld().selectSeaTile(coordinateNumber, coordinateCharacter);
			System.out.println("Firing at "+coordinateCharacter+" "+coordinateNumber);
			break;
		}
		case MAP:{
			int coordinateNumber = (int)Math.ceil(z/10);
			int coord2 = (int)Math.ceil(x/10);
			char coordinateCharacter = letterCoordinates[coord2];
			worldController.getWorld().selectMapTile(coordinateNumber, coordinateCharacter);
			System.out.println("Firing at "+coordinateCharacter+" "+coordinateNumber);
			break;
		}
		
		
	}
	
}

	@Override
	public void handleTouch(float x, float y) {
		// TODO Auto-generated method stub
		
	}
}