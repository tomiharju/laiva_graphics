package Models;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class World {

	private Matrix4 seaMatrix = new Matrix4();
	private Matrix4 mapMatrix = new Matrix4();
	private Texture seaTile;
	private Texture mapTile;
	private ArrayList<Cell> seaCells = new ArrayList<Cell>();
	private ArrayList<Cell> mapCells = new ArrayList<Cell>();
	private char letters[] = {'A','B','C','D','E','F','G','H','I','J'};
	
	
	
	public World(){
		loadTextures();
		createBoard();
		
	}
	
	public void createBoard(){
		seaMatrix.setToRotation(new Vector3(1,0,0),90);
		for(int z = 0; z < 10; z++) {
			for(int x = 0; x < 10; x++) {
				seaCells.add(new Cell(z+1,letters[x],10*x,10*z,new Sprite(seaTile)));
				mapCells.add(new Cell(11-(z+1),letters[x],10*x,10*z,new Sprite(mapTile)));
			}
		}
		
		
		
	}
	
	public void loadTextures(){
		seaTile = new Texture(Gdx.files.internal("data/seaTile.jpg"));
		mapTile = new Texture(Gdx.files.internal("data/mapTile.jpg"));
	}
	
	public void selectSeaTile(int num,char ch){
		
		for(Cell c : seaCells){
			if(c.getNum()==num && c.getChar()==ch){
				if(!c.isSelected())
					c.getSprite().setColor(1,0,0,1);
				else
					c.resetColor();
				c.deSelect();
				
			}
		}
		
	}
	public void selectMapTile(int num, char ch){
		for(Cell c : mapCells){
			if(c.getNum()==num && c.getChar()==ch){
				if(!c.isSelected())
					c.getSprite().setColor(0,0,0,1);
				else
					c.resetColor();
				c.deSelect();
				
			}
		}
	}
	public ArrayList<Cell> getSea(){
		return seaCells;
	}
	public ArrayList<Cell> getMap(){
		return mapCells;
	}
	
	public Matrix4 seaOrientation(){
		return seaMatrix;
	}
	public Matrix4 mapOrientation(){
		return mapMatrix;
	}
	
}
