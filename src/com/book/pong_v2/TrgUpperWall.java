package com.book.pong_v2;

import com.book.simplegameengine_v2.SGEntity;
import com.book.simplegameengine_v2.SGTrigger;
import com.book.simplegameengine_v2.SGWorld;

import android.graphics.PointF;

public class TrgUpperWall extends SGTrigger {
	
	public TrgUpperWall(SGWorld world, PointF position, PointF dimension) {
		super(world, GameModel.TRG_UPPER_WALL_ID, position, dimension);
	}
	
	@Override
	public void onHit(SGEntity entity, float elapsedTimeInSeconds) {
		EntBall ball = (EntBall)entity;
		PointF ballVelocity = ball.getVelocity();
		ball.setPosition(ball.getPosition().x, 0);
		ball.setVelocity(ballVelocity.x,  -ballVelocity.y);
	}

}
