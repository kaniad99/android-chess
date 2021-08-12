package com.mindorks.framework.chess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class BoardView extends View {
    private int SQUARE_SIZE;
    private static final String TAG = "BoardView";

    float[] miniSquare = new float[8];
    float left, top, right, bottom;
    int[] center;

    private Rect rect;

    public BoardView(Context context) {
        super(context);
        rect = new Rect();
        SQUARE_SIZE = GameActivity.getBoardHeight()/8;
    }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        rect = new Rect();
        SQUARE_SIZE = GameActivity.getBoardHeight()/8;
    }

    public BoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rect = new Rect();
        SQUARE_SIZE = GameActivity.getBoardHeight()/8;
    }

    public BoardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        rect = new Rect();
        SQUARE_SIZE = GameActivity.getBoardHeight()/8;
    }

    @Override
    protected void onDraw(Canvas canvas) {

//        Rect rect = new Rect();
        Log.i(TAG, "SquareSize: "+ SQUARE_SIZE);
        SQUARE_SIZE = GameActivity.getBoardHeight()/8;

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.save();

        canvas.drawColor(Color.WHITE);

//        float[] corners = {0, 0,
//                0, 959,
//                959, 959,
//                959, 0};
//        canvas.drawLines(corners, paint);


        for (int i = 0; i < 8; i += 2) {
            for (int j = 0; j < 8; j += 2) {
                rect.left = SQUARE_SIZE * i;
                rect.right = SQUARE_SIZE * (i + 1);
                rect.top = SQUARE_SIZE * j;
                rect.bottom = SQUARE_SIZE * (j + 1);
                canvas.drawRect(rect, paint);

            }
        }
        for (int i = 1; i < 8; i += 2) {
            for (int j = 1; j < 8; j += 2) {
                rect.left = SQUARE_SIZE * i;
                rect.right = SQUARE_SIZE * (i + 1);
                rect.top = SQUARE_SIZE * j;
                rect.bottom = SQUARE_SIZE * (j + 1);
                canvas.drawRect(rect, paint);

            }
        }

        if (center != null) {
            paint.setColor(Color.BLUE);
            canvas.drawCircle(center[0], center[1], (float) ((float) SQUARE_SIZE * 0.45), paint);
        }


        canvas.restore();


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int xPos = (int) event.getX();
            int yPos = (int) event.getY();

            int[] touchLocation = { xPos, yPos };

            Log.i(TAG, "Touch position: ( " + xPos + " , " + yPos + " )");
            center = getSquareFromPosition(touchLocation, new int[]{0, 0});
            center[0] = SQUARE_SIZE / 2 + center[0] * SQUARE_SIZE;
            center[1] = SQUARE_SIZE / 2 + center[1] * SQUARE_SIZE;

            invalidate();
        }
        Log.d("BoardView", "DUPAAA");
        return super.onTouchEvent(event);
    }

    public int[] getSquareFromPosition(int[] touchLocation, int[] boardLocation) {
        int[] ints;
        if (touchLocation[0] < boardLocation[0] || touchLocation[1] < boardLocation[1]
                || touchLocation[0] - boardLocation[0] > GameActivity.getBoardWidth() || touchLocation[1] - boardLocation[1] > GameActivity.getBoardHeight()) {
            Log.i(TAG, "Touch outside the board");
            ints = new int[]{69, 69};
        } else {
            int x = (touchLocation[0] - boardLocation[0]) / (GameActivity.getBoardWidth() / 8);
            int y = (touchLocation[1] - boardLocation[1]) / (GameActivity.getBoardHeight() / 8);

            Log.i(TAG, x + ", " + y);
            ints = new int[]{x, y};
        }
        return ints;


    }
}
