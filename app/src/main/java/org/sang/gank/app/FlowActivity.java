package org.sang.gank.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import org.sang.gank.R;
import org.sang.gank.bean.CategoryBean;
import org.sang.gank.db.CategoryBeanDao;

import java.util.List;

public class FlowActivity extends BaseActivity {

    private CategoryBeanDao cateDao;
    private FlexboxLayout alreadyExistsLayout;
    private FlexboxLayout moreLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);
        alreadyExistsLayout = (FlexboxLayout) findViewById(R.id.already_exists_fb);
        moreLayout = (FlexboxLayout) findViewById(R.id.more_fb);
        cateDao = daoSession.getCategoryBeanDao();
        List<CategoryBean> alreadyList = cateDao.queryBuilder().where(CategoryBeanDao.Properties.IsSelected.eq(true)).list();
        for (int i = 0; i < alreadyList.size(); i++) {
            final TextView tv1 = initTv(alreadyList.get(i).getName());
            alreadyExistsLayout.addView(tv1);
        }
        List<CategoryBean> moreList = cateDao.queryBuilder().where(CategoryBeanDao.Properties.IsSelected.eq(false)).list();
        for (int i = 0; i < moreList.size(); i++) {
            final TextView tv1 = initTv(moreList.get(i).getName());
            moreLayout.addView(tv1);
        }
    }

    @NonNull
    private TextView initTv(final String text) {
        final TextView tv = new TextView(this);
        FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT,
                FlexboxLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        lp.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        lp.bottomMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        tv.setLayoutParams(lp);
        tv.setPadding(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics())
        );
        tv.setGravity(Gravity.CENTER);
        tv.setText(text);
        tv.setBackgroundResource(R.drawable.fb_tv_bg);
        if (!"最新".equals(tv.getText().toString())) {
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CategoryBean bean = cateDao.queryBuilder().where(CategoryBeanDao.Properties.Name.eq(text)).unique();
                    TextView textView = initTv(text);
                    if (bean.getIsSelected()) {
                        alreadyExistsLayout.removeView(view);
                        moreLayout.addView(textView);
                    } else {
                        moreLayout.removeView(view);
                        alreadyExistsLayout.addView(textView);
                    }
                    bean.setIsSelected(!bean.getIsSelected());
                    cateDao.update(bean);
                }
            });
        }
        return tv;
    }

    private TextView getTextView(String text) {
        final TextView tv = new TextView(this);
        FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT,
                FlexboxLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        lp.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        lp.bottomMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        tv.setLayoutParams(lp);
        tv.setPadding(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics())
        );
        tv.setGravity(Gravity.CENTER);
        tv.setText(text);
        tv.setBackgroundResource(R.drawable.fb_tv_bg);
        return tv;
    }

    public void finish(View view) {
        this.finish();
    }
}
