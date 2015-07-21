package com.book.pong_v2;

import com.book.simplegameengine_v2.SGWorld;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;

public class GameModel extends SGWorld {
	
	public static final int BALL_ID = 0;
	public static final int OPPONENT_ID = 1;
	public static final int PLAYER_ID = 2;
	
	public final static int BALL_SIZE = 16;
	public final static int DISTANCE_FROM_EDGE = 16;
	public final static int PADDLE_HEIGHT = 98;
	public final static int PADDLE_WIDTH = 23;
	public final static int PADDLE_BBOX_PADDING = 3;
	
	private EntBall mBall;
	private EntOpponent mOpponent;
	private EntPlayer mPlayer;
	
	public GameModel(Point worldDimensions) {
		super(worldDimensions);
	}
	
	public void setup() {
		Point worldDimensions = getDimensions();
		
		// Ball
		PointF tempPosition = new PointF(worldDimensions.x /2 - (BALL_SIZE / 2),
				(worldDimensions.y / 2) - (BALL_SIZE / 2));
		PointF tempDimensions = new PointF(BALL_SIZE, BALL_SIZE);
		mBall = new EntBall(this, tempPosition, tempDimensions);
		
		// Player's paddle
		tempPosition.set(DISTANCE_FROM_EDGE, (worldDimensions.y / 2) - (PADDLE_HEIGHT / 2));
		tempDimensions.set(PADDLE_WIDTH, PADDLE_HEIGHT);
		mPlayer = new EntPlayer(this, tempPosition, tempDimensions);
		mPlayer.setDebugColor(Color.GREEN);
		
		RectF bboxPadding = new RectF(0, 0, PADDLE_BBOX_PADDING, PADDLE_BBOX_PADDING);
		mPlayer.setBBoxPadding(bboxPadding);
		
		// Opponent's paddle
		tempPosition.set(worldDimensions.x - (DISTANCE_FROM_EDGE + PADDLE_WIDTH), 
				(worldDimensions.y / 2) - (PADDLE_HEIGHT / 2));
		tempDimensions.set(PADDLE_WIDTH, PADDLE_HEIGHT);
		mOpponent = new EntOpponent(this, tempPosition, tempDimensions);
		mOpponent.setDebugColor(Color.CYAN);
		mOpponent.setBBoxPadding(bboxPadding);
	}
	
	public void movePlayer(float x, float y) {
		mPlayer.move(0,  y);
		PointF playerPosition = mPlayer.getPosition();
		PointF playerDimensions = mPlayer.getDimensions();
		Point worldDimensions = getDimensions();
		RectF tempBoundingBox = mPlayer.getBoundingBox();
		if(playerPosition.y < 0) {
			mPlayer.setPosition(playerPosition.x, 0);
		}
		else if(tempBoundingBox.bottom > worldDimensions.y) {
			mPlayer.setPosition(playerPosition.x, worldDimensions.y - (playerDimensions.y - PADDLE_BBOX_PADDING));
		}
	}
	
	@Override
	public void step(float elapsedTimeInSeconds) {
				
		// Apenas para a depura��o. Caso ocorra a paraliza��o em um breakpoint e o tempo decorrido
		// desde a �ltima itera��o seja muito grande, "fingimos" que o tempo transcorrido � de 1 d�cimo
		// de segundo.
		if(elapsedTimeInSeconds > 1.0f) {
			elapsedTimeInSeconds = 0.1f;
		}
		mBall.step(elapsedTimeInSeconds);
		mOpponent.step(elapsedTimeInSeconds);
		mPlayer.step(elapsedTimeInSeconds);
	}
	
	public EntBall getBall() {
		return mBall;
	}
	
	public EntOpponent getOpponent() {
		return mOpponent;
	}
	
	public EntPlayer getPlayer() {
		return mPlayer;
	}

}