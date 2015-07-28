package com.book.pong_v2;

import java.util.ArrayList;

import com.book.simplegameengine_v2.SGEntity;
import com.book.simplegameengine_v2.SGImage;
import com.book.simplegameengine_v2.SGImageFactory;
import com.book.simplegameengine_v2.SGRenderer;
import com.book.simplegameengine_v2.SGView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

public class GameView extends SGView {	
	
	private boolean mIsDebug = false;
	private GameModel mModel;
	
	// Alterado para Rect() ao invés de RectF(). Alteração realizada pois a classe SGRenderer utiliza Rect()
	// para o método drawImage.
	private Rect mTempSrcRect = new Rect();
	private SGImage mBallImage;
	private SGImage mOpponentImage;
	private SGImage mPlayerImage;
	
	// Construtor padrão apenas com o parâmetro Context. Necessário para a compilação, porém é 
	// delcarado como private para evitar o uso, já que o construtor necessário deve receber
	// um modelo como parâmetro.
	private GameView(Context context) {
		super(context);		
	}
	
	public GameView(Context context, GameModel model) {
		super(context);
		mModel = model;
	}
	
	@Override
	public void setup() {
		mModel.setup();
		SGImageFactory imageFactory = getImageFactory();
		mBallImage = imageFactory.createImage("ball.png");
		mPlayerImage = imageFactory.createImage("player.png");
		mOpponentImage = imageFactory.createImage("opponent.png");
	}
	
	@Override
	public void step(Canvas canvas, float elapsedTimeInSeconds) {
		
		mModel.step(elapsedTimeInSeconds);
		
		SGRenderer renderer = getRenderer();
		renderer.beginDrawing(canvas, Color.BLACK);
		ArrayList<SGEntity> entities = mModel.getEntities();
		
		if(mIsDebug == true) {
			for(SGEntity currentEntity : entities) {
				SGEntity.DebugDrawingStyle style = currentEntity.getDebugDrawingStyle();
				if(style == SGEntity.DebugDrawingStyle.FILLED) {
					renderer.drawRect(currentEntity.getBoundingBox(), currentEntity.getDebugColor());
				}
				else {
					renderer.drawOutlineRect(currentEntity.getBoundingBox(), currentEntity.getDebugColor());
				}
			}
		}
		else {
			for(SGEntity currentEntity : entities) {
				if(currentEntity.getCategory() != "trigger") {
					if(currentEntity.getId() == GameModel.PLAYER_ID) {
						mTempSrcRect.set(0, 0, GameModel.PADDLE_WIDTH, GameModel.PADDLE_HEIGHT);
						renderer.drawImage(mPlayerImage,  mTempSrcRect, 
								currentEntity.getPosition(), currentEntity.getDimensions());
					}
					else if(currentEntity.getId() == GameModel.OPPONENT_ID) {
						mTempSrcRect.set(0, 0, GameModel.PADDLE_WIDTH, GameModel.PADDLE_HEIGHT);
						renderer.drawImage(mOpponentImage,  mTempSrcRect, 
								currentEntity.getPosition(), currentEntity.getDimensions());
					}
					else { // if(currentEntity.getId() == GameModel.BALL_ID) {
						mTempSrcRect.set(0, 0, GameModel.BALL_SIZE, GameModel.BALL_SIZE);
						renderer.drawImage(mBallImage, mTempSrcRect, currentEntity.getPosition(), 
								currentEntity.getDimensions());
					}
				}
			}
		}
		renderer.endDrawing();
//		mModel.step(elapsedTimeInSeconds);
//		SGRenderer renderer = getRenderer();
//		renderer.beginDrawing(canvas, Color.BLACK);
//		
//		if(mIsDebug) {
//			SGEntity opponent = mModel.getOpponent();
//			renderer.drawRect(opponent.getPosition(), opponent.getDimensions(), 
//					opponent.getDebugColor());
//			SGEntity player = mModel.getPlayer();
//			renderer.drawRect(player.getPosition(), opponent.getDimensions(), 
//					opponent.getDebugColor());
//			SGEntity ball = mModel.getBall();
//			renderer.drawRect(ball.getPosition(), ball.getDimensions(), ball.getDebugColor());
//		}
//		else {
//			mTempSrcRect.set(0, 0, GameModel.PADDLE_WIDTH, GameModel.PADDLE_HEIGHT);
//			SGEntity opponent = mModel.getOpponent();
//			renderer.drawImage(mOpponentImage, mTempSrcRect, opponent.getPosition(), opponent.getDimensions());
//			
//			mTempSrcRect.set(0, 0, GameModel.PADDLE_WIDTH, GameModel.PADDLE_HEIGHT);
//			SGEntity player = mModel.getPlayer();
//			renderer.drawImage(mPlayerImage, mTempSrcRect, player.getPosition(), player.getDimensions());
//			
//			SGEntity ball = mModel.getBall();
//			mTempSrcRect.set(0, 0, GameModel.BALL_SIZE, GameModel.BALL_SIZE);
//			renderer.drawImage(mBallImage, mTempSrcRect, ball.getPosition(), ball.getDimensions());
//		}
//		
	}

}



