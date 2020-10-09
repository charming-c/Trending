package com.example.trend.base;

public class BaseModel<P extends  BasePrenster> {

    public P mPresenter;

    public BaseModel(P mPresenter){
        this.mPresenter=mPresenter;
    }
}
