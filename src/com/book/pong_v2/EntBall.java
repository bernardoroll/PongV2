package com.book.pong_v2;

import com.book.simplegameengine_v2.SGEntity;
import com.book.simplegameengine_v2.SGWorld;

import android.R.dimen;
import android.graphics.PointF;

public class EntBall extends SGEntity {
	
	//private float mSpeed;
	
	public PointF mVelocity = new PointF();
	
	
	public EntBall(SGWorld world, PointF position, PointF dimensions) {
		super(world, GameModel.BALL_ID, "ball", position, dimensions);
		//mSpeed = 120.0f;
		mVelocity.set(90.0f, 90.0f);
	}
	
	@Override
	public void step(float elapsedTimeInSeconds) {
		move(mVelocity.x * elapsedTimeInSeconds, mVelocity.y * elapsedTimeInSeconds);

		GameModel world = (GameModel) getWorld();
		PointF position = getPosition();
		PointF dimensions = getDimensions();
		EntOpponent opponent = world.getOpponent();
		EntPlayer player = world.getPlayer();
		
		if(world.collisionTest(getBoundingBox(), opponent.getBoundingBox())) {
			setPosition(opponent.getPosition().x - dimensions.x, position.y);
			mVelocity.x = -mVelocity.x;
		}
		else if(world.collisionTest(getBoundingBox(), player.getBoundingBox())) {
			setPosition(player.getBoundingBox().right, position.y);
			mVelocity.x = -mVelocity.x;
		}
	}
	
	public PointF getVelocity() {
		return mVelocity;
	}
	
	public void setVelocity(float speedX, float speedY) {
		mVelocity.set(speedX, speedY);
	}

}
