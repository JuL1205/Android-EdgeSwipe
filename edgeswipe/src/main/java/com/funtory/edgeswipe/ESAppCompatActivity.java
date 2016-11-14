package com.funtory.edgeswipe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.MotionEvent;

/**
 * Created by JuL on 16. 11. 10..
 */
public abstract class ESAppCompatActivity extends AppCompatActivity{
    private EdgeSwipeDelegate edgeSwipeDelegate = new EdgeSwipeDelegate();
    private ActionMode actionMode;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(edgeSwipeDelegate.delegate(this, ev)){
            return true;
        } else{
            return super.dispatchTouchEvent(ev);
        }
    }

    /**
     * Store action mode if it is started
     * @param mode
     */
    @Override
    public void onActionModeStarted(ActionMode mode) {
        super.onActionModeStarted(mode);
        actionMode = mode;
    }

    /**
     * When activity is paused, make sure action mode is ended properly.
     * This check would feel better to have in onDestroy(), but that seems to be
     * too late down the life cycle and the crash keeps on occurring. The drawback
     * of this solution is that the action mode is finished when app is minimized etc.
     */
    @Override
    protected void onPause() {
        super.onPause();
        endActionMode();
    }

    /**
     * Makes sure action mode is ended
     */
    private void endActionMode() {
        if (actionMode != null) {
            actionMode.finish(); /** immediately calls {@link #onActionModeFinished(ActionMode)} */
        }
    }

    /**
     * Clear action mode every time it finishes.
     * @param mode
     */
    @Override
    public void onActionModeFinished(ActionMode mode) {
        super.onActionModeFinished(mode);
        actionMode = null;
    }
}
