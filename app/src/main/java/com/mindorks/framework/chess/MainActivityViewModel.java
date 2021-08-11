package com.mindorks.framework.chess;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class MainActivityViewModel extends AndroidViewModel {
    public MainActivityViewModel(Application application) {
        super(application);
    }

    public void testToast() {
        Toast.makeText(getApplication().getApplicationContext(), "Button test", Toast.LENGTH_SHORT).show();
    }
}
