package org.sang.gank.app;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import org.sang.gank.R;
import org.sang.gank.adapter.RvAdapter;
import org.sang.gank.bean.ItemBean;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ColActivity extends BaseActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_col);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = ((RecyclerView) findViewById(R.id.recycler_view));
        BmobQuery<ItemBean.ResultsBean> query = new BmobQuery<>();
        query.findObjects(new FindListener<ItemBean.ResultsBean>() {
            @Override
            public void done(List<ItemBean.ResultsBean> list, BmobException e) {
                if (e == null) {
                    if (list != null && list.size() > 0) {
                        ItemBean itemBean = new ItemBean();
                        itemBean.setResults(list);
                        RvAdapter adapter = new RvAdapter(ColActivity.this, itemBean);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ColActivity.this,LinearLayoutManager.VERTICAL,false));
                        recyclerView.setAdapter(adapter);
                    }
                }
            }
        });
    }
}
