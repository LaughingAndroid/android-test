package com.kibey.android.demo.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.kibey.android.demo.R;
import com.kibey.android.demo.base.BaseActivity;

/**
 * SampleActivity
 * 示例Activity添加新Activity可以参考这里的代码
 * 写完Activity后,向manifest文件注册下就可以了.MainActivity会自动把当前类添加进去
 * <activity android:name=".sample.SampleActivity"/>
 *
 * @author xl
 * @version V1.0
 * @since 16/9/19
 */
public class SampleActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText(R.string.sample_activity_content);
        textView.setTextSize(48);
        setContentView(textView);
    }
}
