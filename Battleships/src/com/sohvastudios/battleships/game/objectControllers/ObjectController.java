package com.sohvastudios.battleships.game.objectControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.sohvastudios.battleships.game.objectModels.ModelObject;
import com.sohvastudios.battleships.game.objectRenderers.ObjectRenderer;


public abstract class ObjectController {

	
	protected ModelObject object;
	protected Vector3 position;
	protected Rectangle bounds;
	protected boolean orientation_changed;
	protected boolean selected;
	protected boolean isHidden;
	protected Rectangle clear_bounds;
	protected Rectangle area_bounds;
	
	public ObjectController(float x, float y,float width, float height){
		position = new Vector3(x,y,0);
		bounds = new Rectangle(x - width / 2 , y - height / 2 , width, height);
		clear_bounds = new Rectangle();
		area_bounds=new Rectangle(0,2.5f,10,10);
	}
	
	
	
	
	public ModelObject getObject() {
		return object;
	}

	public void setObject(ModelObject object) {
		this.object = object;
	}
	public void setPosition(Vector3 vector3) {
		position.set(vector3);
		bounds.x=position.x-bounds.width/2;
		bounds.y=position.y-bounds.height/2;
		Sprite sprite = object.getSprite();
		sprite.setPosition(position.x-sprite.getWidth()/2, position.y-sprite.getHeight()/2);
	}

	public Vector3 pollPosition() {
		return position;
	}
	public Rectangle pollBounds() {
		return bounds;
	}


	public void select(){
		selected=true;
	}
	public void deSelect(){
		selected=false;
	}
	public boolean isSelected(){
		return selected;
	}
	public void setBounds(float w,float h){
		bounds.width=w;
		bounds.height=h;
		setPosition(position);
		object.getSprite().setSize(bounds.getWidth(),bounds.getHeight());
	}
	public void setBounds(float x){
		bounds.height=x*2;
		bounds.width=x*2;
		setPosition(position);
		object.getSprite().setSize(bounds.getWidth(),bounds.getHeight());
	}
	
	public void hide(){};
	public void show(){};
	public void handleInputDown(Vector3 touchPoint){};
	public void handleInputUp(Vector3 pos){};
	public void handleInputDrag(Vector3 pos){};
	public void rotate90(){};
	
	
	
	

	
	
}
