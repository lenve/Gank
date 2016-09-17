package org.sang.gank.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import org.sang.gank.R;
import org.sang.gank.bean.ItemBean;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class DetailActivity extends BaseActivity {

    private WebView webView;
    private Toolbar toobar;
    private ImageView colIv;
    private boolean isCol;
    private ItemBean.ResultsBean resultBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        colIv = ((ImageView) findViewById(R.id.col));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webView = ((WebView) findViewById(R.id.webview));
        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
        Intent intent = getIntent();
        resultBean = ((ItemBean.ResultsBean) intent.getSerializableExtra("resultBean"));
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webView.loadData(resultBean.getReadability(), "text/html;charset='UTF-8'", null);

        BmobQuery<ItemBean.ResultsBean> query = new BmobQuery<>();
        query.addWhereEqualTo("ganhuo_id", resultBean.getGanhuo_id());
        query.findObjects(new FindListener<ItemBean.ResultsBean>() {
            @Override
            public void done(List<ItemBean.ResultsBean> list, BmobException e) {
                if (e == null) {
                    if (list != null && list.size() > 0) {
                        colIv.setImageResource(R.drawable.star);
                        isCol = true;
                    } else {
                        colIv.setImageResource(R.drawable.unstar);
                        isCol = false;
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public void colClick(View view) {
        if (isCol) {
            BmobQuery<ItemBean.ResultsBean> query = new BmobQuery<>();
            query.addWhereEqualTo("ganhuo_id", resultBean.getGanhuo_id());
            query.findObjects(new FindListener<ItemBean.ResultsBean>() {
                @Override
                public void done(List<ItemBean.ResultsBean> list, BmobException e) {
                    if (e == null) {
                        if (list != null && list.size() > 0) {
                            ItemBean.ResultsBean colBean = list.get(0);
                            colBean.delete(new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        colIv.setImageResource(R.drawable.unstar);
                                        Toast.makeText(DetailActivity.this, "取消收藏成功!", Toast.LENGTH_SHORT).show();
                                        DetailActivity.this.isCol = !isCol;
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(DetailActivity.this, "取消收藏失败!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DetailActivity.this, "取消收藏失败!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            ItemBean.ResultsBean colBean = new ItemBean.ResultsBean();
            colBean.setGanhuo_id(resultBean.getGanhuo_id());
            colBean.setReadability(resultBean.getReadability());
            colBean.setDesc(resultBean.getDesc());
            colBean.setPublishedAt(resultBean.getPublishedAt());
            colBean.setType(resultBean.getType());
            colBean.setWho(resultBean.getWho());
            colBean.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        colIv.setImageResource(R.drawable.star);
                        DetailActivity.this.isCol = !isCol;
                        Toast.makeText(DetailActivity.this, "收藏成功!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailActivity.this, "收藏失败!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
