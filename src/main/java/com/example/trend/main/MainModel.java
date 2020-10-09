package com.example.trend.main;


import android.util.Log;

import com.example.trend.API;
import com.example.trend.base.BaseModel;
import com.example.trend.bean.ListBean;

import java.util.List;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class MainModel extends BaseModel<MainPresnter> {
    public List<ListBean> mListBeans;
    public MainModel(MainPresnter mPresenter) {
        super(mPresenter);
    }


    public void getList(String language,String since,String spoken_language_code){
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl("https://ghapi.huchen.dev")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        API api=retrofit.create(API.class);
        Log.d("2","1");

        Observable<List<ListBean>> observable= api.getList(language, since, spoken_language_code);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ListBean> Data) {
                        mListBeans=Data;
                        Log.d("request","1"+mListBeans);
                        mPresenter.setListBeans(mListBeans);
                        mPresenter.setData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d("TAG", "拉去请求失败");
                        mPresenter.NonList();

                    }

                    @Override
                    public void onComplete() {
                        if(mListBeans!=null);
                        Log.d("2","请求成功"+mListBeans.get(0).getLanguage());
                    }
                });


    }

}
