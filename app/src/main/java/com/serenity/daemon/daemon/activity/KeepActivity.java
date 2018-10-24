package com.serenity.daemon.daemon.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by serenitynanian on 2018/10/24.
 */

public class KeepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.setGravity(Gravity.TOP|Gravity.LEFT);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = 1 ;
        attributes.height = 1 ;

        attributes.x = 1 ;
        attributes.y = 1 ;

        window.setAttributes(attributes);


        KeepManager.getInstance().setContext(this);
    }
}
