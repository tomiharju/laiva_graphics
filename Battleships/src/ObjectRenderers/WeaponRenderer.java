package ObjectRenderers;

import ObjectModels.WeaponObject.Weapon;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class WeaponRenderer extends ObjectRenderer {

	Animation			explosionAnimation;
	Texture				explosionSheet;
	TextureRegion[]		explosionFrames;
	TextureRegion		currentFrame;
	private boolean		animate;
	private float		stateTime;
	private	Vector3		animPos;

	public WeaponRenderer(){
		WorldRenderer.renderers.add(this);
	}
	
	public void createAnimation(Weapon weapon){
	
		animate			= false;
		stateTime		= 0;
		explosionSheet 	= new Texture(Gdx.files.internal("data/explosion/exp"+weapon.ordinal()+".png"));     
		
        TextureRegion[][] tmp = TextureRegion.split(explosionSheet, explosionSheet.getWidth() / 
        		4,explosionSheet.getHeight() / 4);                               
        explosionFrames = new TextureRegion[4 * 4];
        int index = 0;
        for (int i = 0; i <4; i++) {
                for (int j = 0; j < 4; j++) {
                       explosionFrames[index++] = tmp[i][j];
                      
                }
        }
       explosionAnimation = new Animation(0.1f, explosionFrames); 
   }
	@Override
	public void draw(SpriteBatch batch) {
		if(object.isVisible()){
			graphics.draw(batch);
			if(animate){
				stateTime+=Gdx.graphics.getDeltaTime();
				currentFrame = explosionAnimation.getKeyFrame(stateTime,false);
				batch.draw(currentFrame,(animPos.x-1f),(animPos.y-1f),2,2);
				if(explosionAnimation.isAnimationFinished(stateTime)){
					animate=false;
				}
			}
		}
		else
			return;
	}
	@Override
	public void addGraphics(Sprite s) {
		graphics=s;
	}
	public void animate(Vector3 pos){
		animate=true;
		stateTime=0;
		animPos=pos;
	
	
	}
	
	

}
