package com.funtory.edgeswipe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

/**
 * Created by JuL on 16. 11. 10..
 */
public abstract class ESAppCompatActivity extends AppCompatActivity{
    private EdgeSwipeDelegate edgeSwipeDelegate = new EdgeSwipeDelegate();

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(edgeSwipeDelegate.delegate(this, ev)){
            return true;
        } else{
            return super.dispatchTouchEvent(ev);
        }
    }
}
