package com.book.pong_v2;

import com.book.simplegameengine_v2.SGEntity;
import com.book.simplegameengine_v2.SGTrigger;
import com.book.simplegameengine_v2.SGWorld;

import android.graphics.Point;
import android.graphics.PointF;

public class TrgLowerWall extends SGTrigger {
	
	public TrgLowerWall(SGWorld world, PointF position, PointF dimension) {
		super(world, GameModel.TRG_LOWER_WALL_ID, position, dimension);
	}
	
	@Override
	public void onHit(SGEntity entity, float elapsedTimeInSeconds) {
		Point worldDimensions = getWorld().getDimensions();
		
		if(entity.getId() == GameModel.OPPONENT_ID) {
			entity.setPosition(entity.getPosition().x, worldDimensions.y - entity.getDimensions().y);
		}
		else if(entity.getId() == GameModel.OPPONENT_ID) {
			EntOpponent opponent = (EntOpponent) entity;
			opponent.setPosition(entity.getPosition().x, worldDimensions.y - entity.getDimensions().y);
			opponent.setSpeed(-opponent.getSpeed());
		}
		else {
			EntBall ball = (EntBall) entity;
			ball.setPosition(ball.getPosition().x, worldDimensions.y - ball.getDimensions().y);
			ball.setVelocity(ball.getVelocity().x, -ball.getVelocity().y);
			
		}
		
	}

}
