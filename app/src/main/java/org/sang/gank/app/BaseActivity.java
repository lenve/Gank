package org.sang.gank.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import org.sang.gank.db.DaoMaster;
import org.sang.gank.db.DaoSession;

/**
 * Created by 王松 on 2016/9/16.
 */
public class BaseActivity extends AppCompatActivity {
    protected DaoSession daoSession;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "sang.db", null);
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDb());
        daoSession = daoMaster.newSession();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
