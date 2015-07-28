package com.book.pong_v2;

import com.book.simplegameengine_v2.SGEntity;
import com.book.simplegameengine_v2.SGTrigger;
import com.book.simplegameengine_v2.SGWorld;

import android.graphics.PointF;

public class TrgGap extends SGTrigger {
	
	public static final int GAP_SIZE = 50;
	
	public TrgGap(SGWorld world, PointF position, PointF dimension) {
		super(world, GameModel.TRG_GAP_ID, position, dimension);
	}
	
	@Override
	public void onHit(SGEntity entity, float elapsedTimeInSeconds) {
		entity.setPosition(entity.getPosition().x, GAP_SIZE);
		
		if(entity.getId() == GameModel.OPPONENT_ID) {
			EntOpponent opponent = (EntOpponent) entity;
			opponent.setSpeed(-opponent.getSpeed());
		}
	}
}
