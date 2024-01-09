package com.jyh.sixthspace.ui.activity;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jyh.sixthspace.uilibrary.base.BaseActivity;

/**
 * Created by Administrator on 2017/5/22.
 */

public class MovieDetailsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView=new TextView(this);
        textView.setText("MovieDetailsActivity");
        setContentView(textView);
    }
}
