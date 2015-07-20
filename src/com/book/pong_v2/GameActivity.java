package com.book.pong_v2;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.book.simplegameengine_v2.SGActivity;
import com.book.simplegameengine_v2.SGInputPublisher;
import com.book.simplegameengine_v2.SGInputSubscriber;
import com.book.simplegameengine_v2.SGPreferences;

public class GameActivity extends SGActivity implements SGInputSubscriber
{
	public static final String TAG = "PongV1";
	
	private SGInputPublisher mInputPublisher;
	private GameView mView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		enableFullScreen();
		enableKeepScreenOn();
		
		mView = new GameView(this);
		setContentView(mView);
		
		SGPreferences preferences = getPreferences();
		if(preferences.getInt("first_time", -1) == -1) {
			preferences.begin()
				.putInt("first_time", 1)
				.putInt("difficulty", 0)
				.putInt("high_score", 15)
				.end();
			
			Log.d(TAG, "Primeira inicialização.");
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("Difficulty: ");
		stringBuilder.append(preferences.getInt("difficulty", 0));
		Log.d(TAG, stringBuilder.toString());
		
		stringBuilder.setLength(0);
		stringBuilder.append("High score: ");
		stringBuilder.append(preferences.getInt("high_score", 0));
		Log.d(TAG, stringBuilder.toString());
		
		mInputPublisher = new SGInputPublisher(this);
		mInputPublisher.registerSubscriber(this);
		
		setInputPublisher(mInputPublisher);
	}
	
	@Override
	public void onDown(MotionEvent event) 
	{
	}
	
	@Override
	public void onScroll(MotionEvent downEvent, MotionEvent moveEvent, float distanceX, float distanceY) 
	{
		mView.movePlayer(-distanceX, -distanceY);
	}
	
	@Override
	public void onUp(MotionEvent event) 
	{
	}
}

