package WeaponStrategies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

public class GrenadeStrategy implements WeaponStrategy {

	private Vector3 currentTarget;
	
	public GrenadeStrategy(){
		currentTarget = new Vector3();
	}
	@Override
	public boolean update(Vector3 position) {
		position.x += ((currentTarget.x - position.x) * 0.4f)
				* Gdx.graphics.getDeltaTime() * 2;
		position.y += ((currentTarget.y - position.y) * 0.6f)
				* Gdx.graphics.getDeltaTime() * 2;
		if(position.dst(currentTarget)<0.2f){
			return true;
		}
		
		return false;

	}

	@Override
	public Vector3 setTarget(Vector3 target) {
		currentTarget.set(target);
		return null;
	}

	@Override
	public void getShipsInRange(Vector3 target, float radius) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Vector3 getPos() {
		return currentTarget;
	}
}