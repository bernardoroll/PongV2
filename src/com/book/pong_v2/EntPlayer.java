package com.book.pong_v2;

import com.book.simplegameengine_v2.SGWorld;

import android.graphics.PointF;

public class EntPlayer extends EntPaddle {
	
	public EntPlayer(SGWorld world, PointF position, PointF dimensions) {
		super(world, GameModel.PLAYER_ID, position, dimensions);
	}
	
	@Override
	public void step(float elapsedTimeInSeconds) { }

}
