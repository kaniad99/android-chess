package com.mindorks.framework.chess;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class GameActivityViewModel extends AndroidViewModel {
    private static final String TAG = "GameActivityViewModel";
    private static final char[] COLUMNS = "ABCDEFGH".toCharArray();
    private static final char[] ROWS = "87654321".toCharArray();

    private MutableLiveData<String> fieldLiveData = new MutableLiveData<>();

    public MutableLiveData<String> getFieldLiveData() { return fieldLiveData; }

    public GameActivityViewModel(Application application) {
        super(application);
    }

    public void clickedField(int[] fieldCors){
        char[] fieldName =  { COLUMNS[fieldCors[0]], ROWS[fieldCors[1]] };


        Log.i(TAG, "Clicked field: " + fieldName[0] + fieldName[1]);
        String str = new String(fieldName);
        fieldLiveData.postValue(str);
    }

}
