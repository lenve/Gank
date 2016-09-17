package org.sang.gank.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import org.sang.gank.bean.CategoryBean;
import org.sang.gank.fragment.BlankFragment;

import java.util.List;

/**
 * Created by 王松 on 2016/9/16.
 */
public class FgAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<CategoryBean> titleList;

    public FgAdapter(FragmentManager fm, List<Fragment> fragments, List<CategoryBean> titleList) {
        super(fm);
        this.fragments = fragments;
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BlankFragment fragment = (BlankFragment) super.instantiateItem(container, position);
        fragment.initData(titleList.get(position).getCategory());
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position).getName();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
