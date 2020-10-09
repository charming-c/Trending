package com.example.trend.base;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trend.bean.ListBean;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

public abstract class BaseActivity<P extends BasePrenster> extends AppCompatActivity implements View.OnClickListener {

    public P mPrenster;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);

//        setStatusBar();


        mPrenster=getPrenster();
        mPrenster.bindView(this);
        setContentView(getContentViewID());
        initView();
        initListener();
        initData();
    }

    protected void setStatusBar() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    public abstract void initView();

    public abstract void initListener();

    public abstract void initData();

    public abstract int getContentViewID();

    public abstract P getPrenster();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destory();
    }
    public abstract void destory();
}
