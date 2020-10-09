package com.example.trend.view;

import com.example.trend.bean.ListBean;

import java.util.List;

public interface DevelopersView extends View {
    void success(List<ListBean> listBeans);
    void onError(String result);
}
