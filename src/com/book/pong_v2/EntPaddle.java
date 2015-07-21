package com.book.pong_v2;

import com.book.simplegameengine_v2.SGEntity;
import com.book.simplegameengine_v2.SGWorld;

import android.graphics.PointF;

public class EntPaddle extends SGEntity {
	
	public EntPaddle(SGWorld world, int id, PointF position, PointF dimensions) {
		super(world, id, "paddle", position, dimensions);
	}

}
