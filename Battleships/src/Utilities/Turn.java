package Utilities;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Turn {
	
	
	public final static int TURN_READY = 0;
	public final static int TURN_SHOOT = 1;
	public final static int TURN_START = 2;
	public final static int TURN_WAIT  = 3;
	public final static int TURN_RESULT = 4;
	public final static int TURN_BEGINNING = 5;
	
	public float x;
	public float y;
	public int weapon;
	public int type;
	public float[][] hits;
	
	public Turn(int type,float x, float y, int weapon){
		this.type=type;
		this.x=x;
		this.y=y;
		this.weapon=weapon;
	}
	public Turn(int type){
		this.type=type;
	}
	public Turn(int type, ArrayList<Vector2> result){
		this.hits=hits;
	}
	

}
