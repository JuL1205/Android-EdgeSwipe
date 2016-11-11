package com.funtory.edgeswipe;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by JuL on 16. 11. 10..
 */
public class EdgeSwipeDelegate {
    private float transX = 0;
    private float prevTouchX = 0;
    private boolean enableSwipe = false;

    public boolean delegate(final Activity activity, MotionEvent ev){
        float screenWidth = activity.getWindowManager().getDefaultDisplay().getWidth();
        if(ev.getAction() == MotionEvent.ACTION_DOWN){
            prevTouchX = ev.getX();
            transX = 0;
            enableSwipe = false;
            if(prevTouchX < (screenWidth / 4.0f)){    //화면의 일정영역 미만을 터치시작했을 경우 edge swipe기능을 동작시킨다.
                enableSwipe = true;
            }
        } else if(ev.getAction() == MotionEvent.ACTION_MOVE){
            float gap = ev.getX() - prevTouchX;
            gap /= 1.5f;
            if(enableSwipe){
                prevTouchX = ev.getX();

                transX += gap;
                if(transX < 0){
                    transX = 0;
                }

                View myView = activity.getWindow().getDecorView();
                myView.setTranslationX(transX);

                return true;
            }
        } else if(ev.getAction() == MotionEvent.ACTION_UP){
            if(transX > 0){
                if(ev.getX() - prevTouchX < 0 || transX < (screenWidth / 3.0f)){        //다시 닫힘
                    ObjectAnimator endAnim = ObjectAnimator.ofFloat(activity.getWindow().getDecorView(), "translationX", transX, 0.0f);
                    endAnim.setDuration(100);
                    endAnim.setInterpolator(new AccelerateDecelerateInterpolator());
                    endAnim.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            animation.removeListener(this);
                            animation.setTarget(null);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {
                        }
                    });
                    endAnim.start();
                } else{         //열리면서 화면 종료
                    ObjectAnimator endAnim = ObjectAnimator.ofFloat(activity.getWindow().getDecorView(), "translationX", transX, screenWidth);
                    endAnim.setDuration(100);
                    endAnim.setInterpolator(new AccelerateDecelerateInterpolator());
                    endAnim.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            animation.removeListener(this);
                            animation.setTarget(null);

                            activity.finish();
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {
                        }
                    });
                    endAnim.start();
                }

                return true;
            }

        }

        return false;
    }
}
