package com.example.trend.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.trend.R;
import com.example.trend.base.BaseActivity;
import com.example.trend.bean.ListBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainPresnter> {

    private final int stars=0;
    private final int name=1;
    public RecyclerView mRecyclerView;
    private List<ListBean> mData;
    private List<ListBean> mData2;
    private SwipeRefreshLayout refreshLayout;
    private int flag=0;
    private Button mbtn_retry;

    @Override
    public void initView() {

        mRecyclerView=findViewById(R.id.recyclerview);
        mbtn_retry=findViewById(R.id.retry);
    }

    @Override
    public void initListener() {
        if (flag==1)
        mbtn_retry.setOnClickListener(this);

    }

    @Override
    public void initData() {
//        mPrenster=getPrenster();
        refreshLayout=findViewById(R.id.refresh);
        refreshLayout.setRefreshing(true);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPrenster.getList(null,"daily",null);
                Log.d("TAG","下拉刷新");
            }
        });
        mPrenster.getList(null,"daily",null);
//        Log.d("1","data");
    }

    @Override
    public int getContentViewID() {
        switch (flag){
            case 0:
            return R.layout.activity_main;
            default:
                return R.layout.error_layout;

        }
    }

    @Override
    public MainPresnter getPrenster() {
        return new MainPresnter();
    }

    @Override
    public void destory() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,stars,1,"Sort by stars");
        menu.add(0,name,1,"Sort by name");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mData==null) return false;
        mData2=new ArrayList<>(mData.size());
        switch (item.getItemId()){
            case 0:
                while (mData.size()!=0){
                    int i=0;
                    ListBean single =mData.get(i);
                    for(int j=i+1;j<mData.size();j++){
                        if(mData.get(j).getStars()>single.getStars())
                            single=mData.get(j);
                    }
                    Log.d("1","4 "+i+" " +single.getStars());
                    mData.remove(single);
                    Log.d("1","5 "+i+" " +mData.size());
                    mData2.add(single);
                    single=new ListBean();
//                    mData2.add(single);
                }
                setData(mData2);
                return  true;
            case 1:
                while (mData.size()!=0){
                    int i=0;
                    ListBean single =mData.get(i);
                    for(int j=i+1;j<mData.size();j++){
                        if(Character.toUpperCase(mData.get(j).getName().charAt(0))
                                <Character.toUpperCase(single.getName().charAt(0)))
                            single=mData.get(j);
                    }
                    Log.d("1","4 "+i+" " +single.getName());
                    mData.remove(single);
                    Log.d("1","5 "+i+" " +mData.size());
                    mData2.add(single);
                    single=new ListBean();
//                    mData2.add(single);
                }
                setData(mData2);
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {

            switch (v.getId()) {
                case R.id.retry:
//                    for (int i = 0; i < mData.size(); i++)
                        flag = 0;
                    mPrenster = getPrenster();
                    mPrenster.bindView(this);
                    setContentView(getContentViewID());
                    initView();
                    initListener();
                    initData();
                    Log.d("1", "4" + flag);
                    break;
            }

    }

    public void setData(List<ListBean> list){
        this.mData=list;
        refreshLayout.setRefreshing(false);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        ListViewAdapter adapter=new ListViewAdapter(mData);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ListViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }

    public void changeView() {
        flag=1;

        setContentView(getContentViewID());
       initView();
       initListener();
    }

}
