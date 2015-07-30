package com.book.pong_v2;

import com.book.simplegameengine_v2.SGWorld;

import android.R.dimen;
import android.graphics.PointF;

public class EntOpponent extends EntPaddle {
	
	private float mSpeed;
	private float mReaction;
	
	public static final float MIN_REACTION = 30;
	public static final float MAX_SPEED = 300;
	
	public EntOpponent(SGWorld world, PointF position, PointF dimensions) {
		super(world, GameModel.OPPONENT_ID, position, dimensions);
		//mSpeed = 120f;
		calculateSpeed(0);
		mReaction = dimensions.y / 2;
	}
	
	@Override
	public void step(float elapsedTimeInSeconds) {		
		
		//move(0, mSpeed * elapsedTimeInSeconds);
		PointF position = getPosition();
		PointF dimensions = getDimensions();
		//SGWorld world = getWorld();
		
		GameModel model = (GameModel)getWorld();
		float paddleCenterY = position.y + (dimensions.y / 2);
		float reactionTop = paddleCenterY - mReaction;
		float reactionBottom = paddleCenterY + mReaction;
		
		EntBall ball = model.getBall();
		float ballCenterY = ball.getPosition().y + (ball.getDimensions().y / 2);
		
		if(reactionTop > ballCenterY) {
			move(0, -(mSpeed * elapsedTimeInSeconds));
		}
		else if(reactionBottom < ballCenterY){
			move(0, mSpeed * elapsedTimeInSeconds);
		}
		
	}
	
	public float getSpeed() {
		return mSpeed;
	}
	
	public void setSpeed(float speed) {
		mSpeed = speed;
	}
	
	public float getReaction() {
		return mReaction;
	}
	
	public void setReaction(float reaction) {
		mReaction = reaction;
	}
	
	public void decreaseReaction() {
		if(--mReaction < MIN_REACTION) {
			mReaction = MIN_REACTION;
		}
	}
	
	public void calculateSpeed(int playerScore) {	
		float playerScoreSqr = (playerScore + 5) * (playerScore + 5);
		mSpeed = (playerScoreSqr / (150 + playerScoreSqr)) * MAX_SPEED;
	}
	
	public void increaseSpeed() {
		mSpeed += 10;
	}
}
