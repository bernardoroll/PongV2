package com.book.pong_v2;

import com.book.simplegameengine_v2.SGInputSubscriber;

import android.view.MotionEvent;

public class GameController implements SGInputSubscriber {
	
	private GameModel mModel;
	
	public GameController(GameModel model) {
		mModel = model;
	}

	@Override
	public void onDown(MotionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScroll(MotionEvent downEvent, MotionEvent moveEvent, float distanceX, float distanceY) {
		mModel.movePlayer(-distanceX, -distanceY);

	}

	@Override
	public void onUp(MotionEvent event) {
		// TODO Auto-generated method stub

	}
	
	public GameModel getModel() {
		return mModel;
	}

}
