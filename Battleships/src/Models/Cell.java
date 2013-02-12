package Models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Cell implements Object {
	
	private boolean isOccupied=false;
	private boolean isSelected=false;
	private int coordinateNumber;
	private char coordinateChar;
	private Sprite sprite;
	private Vector2 position;
	private Rectangle bounds;
	private Color color;
	
	public Cell(int n,char c,int posx,int posy,Sprite s){
		this.coordinateNumber=n;
		this.coordinateChar=c;
		this.sprite=s;
		this.position=new Vector2(posx,posy);
		bounds = new Rectangle(posx,posy,10,10);
		sprite.setPosition(position.x, position.y);
		sprite.setSize(bounds.width,bounds.height);
		color=sprite.getColor();
		
	}
	
	public void placeShip(){
		
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

	public Vector2 getPosition() {
		return position;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public boolean isSelected(){
		return isSelected;
	}
	public void deSelect(){
		isSelected=!isSelected;
	}
	public void resetColor(){
		sprite.setColor(color);
	}
}
