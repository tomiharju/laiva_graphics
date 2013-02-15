package ObjectModels;

import java.io.FileInputStream;
import java.util.Properties;

import ObjectControllers.ObjectController;
import ObjectRenderers.ObjectRenderer;

public class ShipObject extends ModelObject{

	private int size;
	private int hp;
	
	public ShipObject(String shiptype){
		Properties prop = new Properties();
		try{
			prop.load(new FileInputStream("//ships.properties"));
			System.out.println("Size: "+prop.getProperty(shiptype+"size")); 
			System.out.println("Hp :"+prop.getProperty(shiptype+"hp"));
			System.out.println("Properties listed...");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
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
