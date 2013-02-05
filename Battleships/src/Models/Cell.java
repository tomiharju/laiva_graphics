package Models;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Cell {
	
	private boolean isOccupied=false;
	private int coordinateNumber;
	private char coordinateChar;
	private Ship ship;
	private final int size=10;
	private Sprite sprite;
	
	public Cell(int n,char c,Sprite s){
		this.coordinateNumber=n;
		this.coordinateChar=c;
		this.sprite=s;
	}
	
	public void placeShip(Ship s){
		this.ship=s;
	}
	public int getNum(){
		return coordinateNumber;
	}
	public char getChar(){
		return coordinateChar;
	}
	public Sprite getSprite(){
		return sprite;
	}

}
