package com.star.app;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.star.app.retrofit.DialogObserver;
import com.star.app.retrofit.RequestCommand;
import com.star.app.retrofit.bean.ConfigBean;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.main_request).setOnClickListener(this);
        mContent = findViewById(R.id.main_content);
    }

    @Override
    public void onClick(View v) {
        RequestCommand.requestConfig(new MainObserver(MainActivity.this));
    }

    private class MainObserver extends DialogObserver<ConfigBean> {
        public MainObserver(Context mContext) {
            super(mContext);
        }

        @Override
        public void success(ConfigBean configBean) {
            mContent.setText(configBean.code() + configBean.msg());
        }
    }
}
