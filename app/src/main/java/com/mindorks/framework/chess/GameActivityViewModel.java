package com.mindorks.framework.chess;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class GameActivityViewModel extends AndroidViewModel {

    private MutableLiveData<String> fieldLiveData = new MutableLiveData<>();

    public MutableLiveData<String> getFieldLiveData() { return fieldLiveData; }

    public GameActivityViewModel(Application application) {
        super(application);
    }

    public void setFieldName(String fieldName){
        fieldLiveData.postValue(fieldName);
    }
}
