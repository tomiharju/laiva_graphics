package ObjectModels;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.badlogic.gdx.Gdx;

import ObjectControllers.ObjectController;
import ObjectRenderers.ObjectRenderer;

public class ShipObject extends ModelObject{

	public enum ShipType{
		ROWBOAT(1,10),
		MOTORBOAT(2,20),
		BATTLESHIP(5,50);
		private int size,hp;
		
		private ShipType(int s,int h){
			size=s;
			hp=h;
		}
		private int getHp(){
			return hp;
		}
		private int getSize(){
			return size;
		}
		
	}
	private int health;
	private int shipsize;
	
	
	public ShipObject(ShipType ship,ObjectController controller,ObjectRenderer renderer){
		setController(controller);
		setRenderer(renderer);
		health=ship.getHp();
		shipsize=ship.getSize();
		System.out.println(health+"  "+shipsize);
	}
	
	
	
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setController(ObjectController controller) {
		this.controller=controller;
		this.controller.setObject(this);
		
	}

	@Override
	public void setRenderer(ObjectRenderer renderer) {
		this.renderer=renderer;
		this.renderer.setObject(this);
		
	}

}
