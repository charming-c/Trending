package com.example.trend.main;

import android.util.Log;

import com.example.trend.base.BasePrenster;
import com.example.trend.bean.ListBean;
import com.example.trend.main.MainActivity;
import com.example.trend.main.MainModel;
import com.example.trend.view.DevelopersView;
import com.example.trend.view.View;

import java.util.List;

class MainPresnter extends BasePrenster<MainActivity, MainModel> {

    public List<ListBean> mListBeans;

    @Override
    public MainModel getModelInstance() {
        return new MainModel(this);
    }



    public void getList(String language, String since, String spoken_language_code){

        mModel.getList(language,since,spoken_language_code);

    }

    public void setListBeans(List<ListBean> listBeans) {
        this.mListBeans = listBeans;
        Log.d("1","2"+mListBeans.size());
    }
    public void setData(){
        mView.setData(mListBeans);
    }

    public void NonList() {
        mView.changeView();
    }
}
