package com.allen.mobileshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.allen.mobileshop.R;
import com.allen.mobileshop.view.ToolBarX;

/**
 * 封装baseactivity基础类 包含动画效果和布局控制反转
 * 子布局调用getLayoutId设置布局文件
 * Created by Allen on 2016/2/15.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private RelativeLayout rlContent;
    private Toolbar mToolbar;
    private ToolBarX mToolBarX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_layout);
        initView();
        View view = getLayoutInflater().inflate(getLayoutId(), rlContent, false);
        rlContent.addView(view);
    }

    /**
     * 初始化布局
     */
    private void initView() {
        rlContent = (RelativeLayout) findViewById(R.id.rlContent);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    public ToolBarX getToolbar(){
        if (null ==mToolBarX ){
            mToolBarX = new ToolBarX(mToolbar,this);
        }
        return mToolBarX;
    }

    public Toolbar getmToolbar(){
        return mToolbar;
    }

    /**
     *子类继承这个方法传递布局
     * @return
     */
    public abstract int getLayoutId();

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.anim_in_right_left, R.anim.anim_out_right_left);

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_in_left_right, R.anim.anim_out_left_right);
    }
}
