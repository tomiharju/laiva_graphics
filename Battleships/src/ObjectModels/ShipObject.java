package ObjectModels;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import ObjectControllers.ObjectController;
import ObjectControllers.SeaController;
import ObjectControllers.ShipController;
import ObjectRenderers.ObjectRenderer;

public class ShipObject extends ModelObject{

	public enum ShipType{
		ROWBOAT(10,10,10,"rowboat.png"),
		MOTORBOAT(20,10,20,"motorboat.png"),
		BATTLESHIP(50,10,50,"battleship.jpg");
		private int lenght,width,hp;
		private String file;
		private ShipType(int l,int w,int h,String f){
			lenght=l;
			width=w;
			hp=h;
			file=f;
		}
		private int getHp(){
			return hp;
		}
		private int getLenght(){
			return lenght;
		}
		private int getWidth(){
			return width;
		}
		private String getFile(){
			return file;
		}
		
	}
	
	
	
	
	
	private Vector2 position;
	private Vector2 angle;
	public ShipObject(ShipType ship,ObjectController controller,ObjectRenderer renderer){
		setController(controller);
		setRenderer(renderer);
		WorldObject.objects.add(this);
		
		
		position=new Vector2();
		angle=new Vector2();
		
		sprite 	=  new Sprite(new Texture(Gdx.files.internal("data/"+ship.getFile())));
		sprite.setSize(ship.getWidth()*2,ship.getLenght()*2);
		System.out.println("Finished creating ship!");
	}
	
	
	
	
	@Override
	public void update() {
		position.lerp(((ShipController)controller).pollPosition(),0.1f);
		angle.setAngle(((ShipController)controller).pollRotation());
		sprite.setPosition(position.x-sprite.getWidth()/2, position.y-sprite.getHeight()/2);
		sprite.setRotation(angle.angle());
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
