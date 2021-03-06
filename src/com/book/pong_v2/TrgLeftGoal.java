package com.book.pong_v2;

import com.book.simplegameengine_v2.SGEntity;
import com.book.simplegameengine_v2.SGTrigger;
import com.book.simplegameengine_v2.SGWorld;

import android.graphics.PointF;
import android.util.Log;

public class TrgLeftGoal extends SGTrigger {
	
	public TrgLeftGoal(SGWorld world, PointF position, PointF dimension) {
		super(world, GameModel.TRG_LEFT_GOAL_ID, position, dimension);
	}
	
	@Override
	public void onHit(SGEntity entity, float elapsedTimeInSeconds) {
		GameModel model = (GameModel) getWorld();
		model.increaseOpponentScore();
		Log.d("PongV2", "Opponent marca um ponto!");
		model.logScore();
		model.setCurrentState(GameModel.STATE_GOAL);
	}

}
