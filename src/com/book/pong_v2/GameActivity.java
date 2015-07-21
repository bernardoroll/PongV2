package com.book.pong_v2;

import com.book.simplegameengine_v2.SGActivity;
import com.book.simplegameengine_v2.SGInputPublisher;
import com.book.simplegameengine_v2.SGPreferences;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;

public class GameActivity extends SGActivity {
	
	private GameController mController;
	private GameModel mModel;
	private GameView mView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		enableFullScreen();
		enableKeepScreenOn();
		SGPreferences preferences = getPreferences();
		if(preferences.getInt("first_time", -1) == -1) {
			preferences.begin().putInt("first_time", 1).putInt("difficulty", 1)
					.putInt("high_score", 15).end();
			Log.d("PongV2", "Primeira inicialização.");
		}
		
		Point worldDimensions = new Point(480, 320);
		mModel = new GameModel(worldDimensions);
		mView = new GameView(this, mModel);
		setContentView(mView);
		SGInputPublisher inputPublisher = new SGInputPublisher(this);
		mController = new GameController(mModel);
		inputPublisher.registerSubscriber(mController);
		setInputPublisher(inputPublisher);
		
	}
		
}

