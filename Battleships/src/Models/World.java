package Models;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class World {

	final Sprite[][] seaGrid = new Sprite[10][10];
	final Sprite[][] mapGrid = new Sprite[10][10];
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
		for(int z = 0; z < 10; z++) {
			for(int x = 0; x < 10; x++) {
				seaGrid[x][z] = new Sprite(seaTile);
				seaGrid[x][z].setPosition(10*x,10*z);
				seaGrid[x][z].setSize(10, 10);
				seaCells.add(new Cell(z+1,letters[x],seaGrid[x][z]));
				mapGrid[x][z] = new Sprite(mapTile);
				mapGrid[x][z].setPosition(10*x,10*z);
				mapGrid[x][z].setSize(10, 10);
				mapCells.add(new Cell(z+1,letters[x],mapGrid[x][z]));
			}
		}
		
		seaMatrix.setToRotation(new Vector3(1,0,0),90);
	
	}
	
	public void loadTextures(){
		seaTile = new Texture(Gdx.files.internal("data/seaTile.jpg"));
		mapTile = new Texture(Gdx.files.internal("data/mapTile.jpg"));
	}
	
	public void selectSeaTile(int num,char ch){
		
		for(Cell c : seaCells){
			if(c.getNum()==num && c.getChar()==ch){
				c.getSprite().setColor(1,0,0,1);
				System.out.println("Recoloring sea..");
			}
		}
		
	}
	public void selectMapTile(int num, char ch){
		for(Cell c : mapCells){
			if(c.getNum()==num && c.getChar()==ch){
				c.getSprite().setColor(1,0,0,1);
				
			}
		}
	}
	public Sprite[][] getSea(){
		return seaGrid;
	}
	public Sprite[][] getMap(){
		return mapGrid;
	}
	
	public Matrix4 seaOrientation(){
		return seaMatrix;
	}
	public Matrix4 mapOrientation(){
		return mapMatrix;
	}
	
}
