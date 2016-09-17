package org.sang.gank.app;

import android.content.Intent;
import android.os.Bundle;

import org.sang.gank.R;
import org.sang.gank.bean.CategoryBean;
import org.sang.gank.db.CategoryBeanDao;

import java.util.List;

public class WelcomeActivity extends BaseActivity {

    private CategoryBeanDao cateDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        cateDao = daoSession.getCategoryBeanDao();
        List<CategoryBean> list = cateDao.queryBuilder().list();
        if (list == null || list.size() == 0) {
            initCateDB();
        }
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }

    private void initCateDB() {
        cateDao.insert(new CategoryBean(null, "最新", "all", true));
        cateDao.insert(new CategoryBean(null, "Android", "Android", false));
        cateDao.insert(new CategoryBean(null, "iOS", "iOS", false));
        cateDao.insert(new CategoryBean(null, "休息视频", "休息视频", false));
        cateDao.insert(new CategoryBean(null, "福利", "福利", false));
        cateDao.insert(new CategoryBean(null, "拓展资源", "拓展资源", false));
        cateDao.insert(new CategoryBean(null, "前端", "前端", false));
        cateDao.insert(new CategoryBean(null, "瞎推荐", "瞎推荐", false));
        cateDao.insert(new CategoryBean(null, "App", "App", false));
    }
}
