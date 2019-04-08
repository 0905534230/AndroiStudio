package com.example.contact;

import android.content.Context;
import android.gesture.Gesture;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


public class RecycleTouchListener implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener mlistener;
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
        public void onLongItemClick(View view , int position);

    }
    GestureDetector mGestureDetector;

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view,  MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX() , e.getY());
        if(childView != null && mlistener != null && mGestureDetector.onTouchEvent(e)){
            mlistener.onItemClick(childView , view.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent( RecyclerView recyclerView,  MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }


    public  RecycleTouchListener(Context context , final RecyclerView recyclerView , OnItemClickListener listener) {
        mlistener = listener;
        mGestureDetector = new GestureDetector(context , new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && mlistener != null) {
                    mlistener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                }
            }
        });
    }
}

