package com.book.pong_v2;

import com.book.simplegameengine_v2.SGEntity;
import com.book.simplegameengine_v2.SGWorld;

import android.graphics.PointF;

public class EntPaddle extends SGEntity {
	
	public static final int NUM_OF_SECTORS = 10;
	
	private float mSectorSize;
	
	public EntPaddle(SGWorld world, int id, PointF position, PointF dimensions) {
		super(world, id, "paddle", position, dimensions);
		// O décimo setor fica fora do paddle, por isso ele não pode entrar nesta conta.
		mSectorSize = dimensions.y / (NUM_OF_SECTORS - 1); 
	}
	
	public float getSectorSize() {
		return mSectorSize;
	}

}
