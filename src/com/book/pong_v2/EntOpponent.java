package com.book.pong_v2;

import com.book.simplegameengine_v2.SGWorld;

import android.graphics.PointF;

public class EntOpponent extends EntPaddle {
	
	private float mSpeed;
	
	public EntOpponent(SGWorld world, PointF position, PointF dimensions) {
		super(world, GameModel.OPPONENT_ID, position, dimensions);
		mSpeed = 60.0f;
	}
	
	@Override
	public void step(float elapsedTimeInSeconds) {
		move(0, mSpeed * elapsedTimeInSeconds);
		PointF position = getPosition();
		PointF dimensions = getDimensions();
		SGWorld world = getWorld();
		
		float bboxPaddingBottom = getBBoxPadding().bottom;
		
		if(position.y < 0) {
			setPosition(getPosition().x, 0);
			mSpeed = -mSpeed;
		}
		else if(getBoundingBox().bottom >= world.getDimensions().y) {
			position.y = world.getDimensions().y - (dimensions.y - bboxPaddingBottom);
			mSpeed = -mSpeed;
		}
	}
	
	public float getSpeed() {
		return mSpeed;
	}
	
	public void setSpeed(float speed) {
		mSpeed = speed;
	}
	
}
