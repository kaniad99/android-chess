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

    private BoardView board;
    private GameActivityViewModel viewModel;
    private TextView fieldCors;

    private static final char[] COLUMNS = "ABCDEFGH".toCharArray();
    private static final char[] ROWS = "87654321".toCharArray();

    private static int boardHeight;

    public static int getBoardHeight() {
        return boardHeight;
    }

    public static int getBoardWidth() {
        return boardWidth;
    }

    private static int boardWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        board = findViewById(R.id.boardView);
        fieldCors = findViewById(R.id.fieldName);

        viewModel = new ViewModelProvider(this).get(GameActivityViewModel.class);
        viewModel.getFieldLiveData().observe(this,
                this::onChanged);

        setBoard();
    }

    private void setBoard() {
        board.getLayoutParams().height = board.getMeasuredHeight() / 8 * 8;
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
                int[] cors = getSquareFromPosition(touchLocation, boardLocation);
                if(cors[0] != 69){
                    viewModel.clickedField(cors);
                };
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:

        }
        return false;
    }

    public int[] getSquareFromPosition(int[] touchLocation, int[] boardLocation) {
        int[] ints;
        if (touchLocation[0] < boardLocation[0] || touchLocation[1] < boardLocation[1]
                || touchLocation[0] - boardLocation[0] > boardWidth || touchLocation[1] - boardLocation[1] > boardHeight) {
            Log.i(TAG, "Touch outside the board");
            ints = new int[]{69, 69};
        } else {
            int x = (touchLocation[0] - boardLocation[0]) / (boardWidth / 8);
            int y = (touchLocation[1] - boardLocation[1]) / (boardHeight / 8);

            Log.i(TAG, x + ", " + y);
            ints = new int[]{x, y};
        }
        return ints;


    }

    private void onChanged(String field) {
        fieldCors.setText(field);
    }
}
