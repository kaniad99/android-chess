package com.mindorks.framework.chess;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class GameActivity extends AppCompatActivity {

    private static final String TAG = "GameActivity";

    private ImageView board;
    private GameActivityViewModel viewModel;
    private TextView fieldCors;

    private static final char[] COLUMNS = "ABCDEFGH".toCharArray();
    private static final char[] ROWS = "87654321".toCharArray();

    private static int boardHeight;
    private static int boardWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        board = findViewById(R.id.board);
        fieldCors = findViewById(R.id.fieldName);

        viewModel = new ViewModelProvider(this).get(GameActivityViewModel.class);
        viewModel.getFieldLiveData().observe(this,
                this::onChanged);

    }

    private void setBoard() {
        boardHeight = board.getMeasuredHeight();
        boardWidth = board.getMeasuredWidth();
        Log.i(TAG, "boardSize: " + boardHeight + ", " + boardWidth);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        setBoard();

        int x = (int) event.getX();
        int y = (int) event.getY();
        int[] touchLocation = {x, y};

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int[] boardLocation = new int[2];
                board.getLocationOnScreen(boardLocation);
                Log.i(TAG, "Board position: ( " + boardLocation[0] + " , " + boardLocation[1] + " )");
                Log.i(TAG, "Touch position: ( " + x + " , " + y + " )");
                getSquareFromPosition(touchLocation, boardLocation);

            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:

        }
        return false;
    }

    public void getSquareFromPosition(int[] touchLocation, int[] boardLocation) {
        if (touchLocation[0] < boardLocation[0] || touchLocation[1] < boardLocation[1]
                || touchLocation[0] - boardLocation[0] > boardWidth || touchLocation[1] - boardLocation[1] > boardHeight) {
            Log.i(TAG, "Touch outside the board");
        } else {
            int x = (touchLocation[0] - boardLocation[0]) / (boardWidth / 8);
            int y = (touchLocation[1] - boardLocation[1]) / (boardHeight / 8);

            Log.i(TAG, x + ", " + y);
            int[] cors = {x, y};

            viewModel.clickedField(cors);
        }
    }

    private void onChanged(String field) {
        fieldCors.setText(field);
    }
}
