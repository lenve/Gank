package org.sang.gank.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sang.gank.R;
import org.sang.gank.adapter.RvAdapter;
import org.sang.gank.bean.ItemBean;
import org.sang.gank.net.ApiService;
import org.sang.gank.util.NetUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 王松 on 2016/9/16.
 */
public class BlankFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private String category;

    public static BlankFragment getInstance(String category) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString("category", category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category = getArguments().getString("category");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.blank_fg_layout, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("sang", "onResume: ------");
        initData(category);
    }

    private void initView(View view) {
        recyclerView = ((RecyclerView) view.findViewById(R.id.recycler_view));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    public void initData(String category) {
        Log.d("sang", "initData: ++++++++++++++++");
        ApiService apiService = NetUtils.getApiService();
        Call<ItemBean> call = apiService.getItemBeen(category, String.valueOf(10), String.valueOf(1));
        call.enqueue(new Callback<ItemBean>() {
            @Override
            public void onResponse(Call<ItemBean> call, Response<ItemBean> response) {
                if (response.isSuccessful()) {
                    ItemBean itemBean = response.body();
                    RvAdapter adapter = new RvAdapter(getActivity(), itemBean);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ItemBean> call, Throwable t) {
                Log.d("sang", "onFailure: " + t.getMessage());
            }
        });
    }
}
