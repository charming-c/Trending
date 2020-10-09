package com.example.trend.base;

import com.example.trend.bean.ListBean;
import com.example.trend.view.DevelopersView;
import com.example.trend.view.View;

import java.util.List;

public abstract class BasePrenster<V extends BaseActivity,M extends BaseModel>{
    public V mView;
    public M mModel;

    public BasePrenster(){
        this.mModel=getModelInstance();
    }

    public void bindView(V mView){
        this.mView=mView;
    }

    public void unBindView(){
        this.mView = null;
    }

    public abstract M getModelInstance();
}
