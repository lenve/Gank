package org.sang.gank.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.sang.gank.R;
import org.sang.gank.adapter.FgAdapter;
import org.sang.gank.bean.CategoryBean;
import org.sang.gank.db.CategoryBeanDao;
import org.sang.gank.fragment.BlankFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private CategoryBeanDao cateDao;
    private ViewPager viewpager;
    private TabLayout tabLayout;
    private List<CategoryBean> titleList;
    private List<Fragment> fragments;
    private FgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        titleList.clear();
        fragments.clear();
        titleList.addAll(cateDao.queryBuilder().where(CategoryBeanDao.Properties.IsSelected.eq(true)).list());
        for (CategoryBean categoryBean : titleList) {
            fragments.add(BlankFragment.getInstance(categoryBean.getCategory()));
        }
//        adapter = new FgAdapter(getSupportFragmentManager(), fragments, titleList);
//        viewpager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewpager);
        adapter.notifyDataSetChanged();
    }

    private void initData() {
        cateDao = daoSession.getCategoryBeanDao();
        titleList = cateDao.queryBuilder().where(CategoryBeanDao.Properties.IsSelected.eq(true)).list();
        titleList = new ArrayList<>();
        fragments = new ArrayList<>();
//        for (CategoryBean categoryBean : titleList) {
//            fragments.add(BlankFragment.getInstance(categoryBean.getCategory()));
//        }
        adapter = new FgAdapter(getSupportFragmentManager(), fragments, titleList);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        viewpager = ((ViewPager) findViewById(R.id.viewpager));
        tabLayout = ((TabLayout) findViewById(R.id.tab_layout));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.col) {
            startActivity(new Intent(MainActivity.this, ColActivity.class));
        } else if (id == R.id.settting) {
            Toast.makeText(MainActivity.this, "努力开发中!", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addMore(View view) {
        startActivity(new Intent(this, FlowActivity.class));
    }

    public void loginTv(View view) {

    }
}
