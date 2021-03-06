package com.book.pong_v2;

import com.book.simplegameengine_v2.SGEntity;
import com.book.simplegameengine_v2.SGWorld;

import android.graphics.PointF;
import android.graphics.RectF;

public class EntBall extends SGEntity {
	
	private static final float MAX_SPEED = 480.0f;	
	private float mSpeed;
	private float mCosTable[] = new float[10]; // Look-Up Table de cosseno
	private float mSinTable[] = new float[10]; // Look-Up Table de seno
	
	public PointF mVelocity = new PointF();
	
	
	public EntBall(SGWorld world, PointF position, PointF dimensions) {
		super(world, GameModel.BALL_ID, "ball", position, dimensions);
		float radianFactor = (float) (Math.PI / 180);
		
		mCosTable[0] = (float) Math.cos(50 * radianFactor);
		mCosTable[1] = (float) Math.cos(40 * radianFactor);
		mCosTable[2] = (float) Math.cos(30 * radianFactor);
		mCosTable[3] = (float) Math.cos(20 * radianFactor);
		mCosTable[4] = (float) Math.cos(10 * radianFactor);
		mCosTable[5] = (float) Math.cos(350 * radianFactor);
		mCosTable[6] = (float) Math.cos(340 * radianFactor);
		mCosTable[7] = (float) Math.cos(330 * radianFactor);
		mCosTable[8] = (float) Math.cos(320 * radianFactor);
		mCosTable[9] = (float) Math.cos(310 * radianFactor);
		
		mSinTable[0] = (float) Math.sin(50 * radianFactor);
		mSinTable[1] = (float) Math.sin(40 * radianFactor);
		mSinTable[2] = (float) Math.sin(30 * radianFactor);
		mSinTable[3] = (float) Math.sin(20 * radianFactor);
		mSinTable[4] = (float) Math.sin(10 * radianFactor);
		mSinTable[5] = (float) Math.sin(350 * radianFactor);
		mSinTable[6] = (float) Math.sin(340 * radianFactor);
		mSinTable[7] = (float) Math.sin(330 * radianFactor);
		mSinTable[8] = (float) Math.sin(320 * radianFactor);
		mSinTable[9] = (float) Math.sin(310 * radianFactor);
		
		calculateSpeed(0);
		mVelocity.x = mSpeed * mCosTable[0];
		mVelocity.y = mSpeed * mSinTable[0];
		
		//mSpeed = 120.0f;
		//mVelocity.set(90.0f, 90.0f);
	}
	
	@Override
	public void step(float elapsedTimeInSeconds) {
		move(mVelocity.x * elapsedTimeInSeconds, mVelocity.y * elapsedTimeInSeconds);

		RectF ballBB = getBoundingBox();
		EntPaddle collidePaddle = null;
		GameModel model = (GameModel) getWorld();
		EntPaddle player = model.getPlayer();
		EntOpponent opponent = model.getOpponent();
		RectF opponentBB = opponent.getBoundingBox();
		RectF playerBB = player.getBoundingBox();
		
		if(model.collisionTest(ballBB, playerBB)) {
			collidePaddle = player;
		} else if(model.collisionTest(ballBB, opponentBB)) {
			collidePaddle = opponent;
		}
		
		if(collidePaddle != null) {
			float ballDimnsionX = getDimensions().x;
			float ballPositionY = getPosition().y;
			RectF paddleBB = collidePaddle.getBoundingBox();
			float paddlePositionY = collidePaddle.getPosition().y;
			float sectorSize = collidePaddle.getSectorSize();
			int sector;
			
			mSpeed += 10;
			
			if(ballPositionY < paddlePositionY) {
				sector = 0;
			}
			else {
				float deltaPosition = ballPositionY - paddlePositionY;
				sector = (int) Math.ceil(deltaPosition / sectorSize);
			}
			
			if(collidePaddle.getId() == GameModel.PLAYER_ID) {
				setPosition(paddleBB.right, ballPositionY);
				mVelocity.x = mSpeed * mCosTable[sector];
			}
			else { // Paddle do oponente
				setPosition(paddleBB.left - ballDimnsionX, ballPositionY);
				mVelocity.x = -(mSpeed * mCosTable[sector]);
			}
			mVelocity.y = -(mSpeed * mSinTable[sector]);
		}
	}
	
	public PointF getVelocity() {
		return mVelocity;
	}
	
	public void setVelocity(float speedX, float speedY) {
		mVelocity.set(speedX, speedY);
	}
	
	public float calculateSpeed(int playerScore) {
		float playerScoreSqr = (playerScore + 8) * (playerScore + 8);
		mSpeed = (playerScoreSqr / (150 + playerScoreSqr)) * MAX_SPEED;
		return mSpeed;
	}

}
