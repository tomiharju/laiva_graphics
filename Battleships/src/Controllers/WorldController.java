package Controllers;

import Models.World;

//Handle input while in GameScreen

public class WorldController {
	
	private World world;
	private char letterCoordinates[] = {'x','A','B','C','D','E','F','G','H','I','J'};
	
	public WorldController(World w){
		this. world=w;
	}
	public void update(float delta){
		

		
	
	}
	
	public void handleTouchAtSea(float x,float z){
		if((x>0 && x < 100)&& (z>0 && z<100)){
			int coordinateNumber = (int)Math.ceil(z/10);
			int coord2 = (int)Math.ceil(x/10);
			char coordinateCharacter = letterCoordinates[coord2];
			System.out.println("Firing at ["+coordinateNumber+"]["+coordinateCharacter+"]");
			world.selectSeaTile(coordinateNumber, coordinateCharacter);
		}
	}
	public void handleTouchAtMap(float x,float z){
		if((x>0 && x < 100)&& (z>0 && z<100)){
			int coordinateNumber = (int)Math.ceil(z/10);
			int coord2 = (int)Math.ceil(x/10);
			char coordinateCharacter = letterCoordinates[coord2];
			System.out.println("Firing at ["+coordinateNumber+"]["+coordinateCharacter+"]");
			world.selectMapTile(coordinateNumber, coordinateCharacter);
		}
	}
	
	

}
