package com.kibey.android.demo.espresso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.kibey.android.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * EspressoActivity
 * AndroidStudio2.2新加入的功能EspressoTestRecorder
 * 详细参考:
 * <a href="https://developer.android.com/studio/test/espresso-test-recorder.html">https://developer.android.com/studio/test/espresso-test-recorder.html</a>
 *
 * @author xl
 * @version V1.0
 * @see <a href="https://developer.android.com/studio/test/espresso-test-recorder.html">espresso-test-recorder</a>
 * @since 16/9/19
 */
public class EspressoActivity extends AppCompatActivity {

    private LinearLayout mItemContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espresso);
        mItemContainer = (LinearLayout) findViewById(R.id.item_container);
    }

    private int mIndex = 0;

    public void addItem(View view) {
        View itemView = createItemView("新增内容" + mIndex++);
        mItemContainer.addView(itemView);
    }

    public void delItem(View view) {
        List<View> needRemove = new ArrayList<>();
        for (int i = 0, size = mItemContainer.getChildCount(); i < size; i++) {
            View v = mItemContainer.getChildAt(i);
            if (v instanceof CheckBox) {
                if (((CheckBox) v).isChecked()) {
                    needRemove.add(v);
                }
            }
        }

        for (View remove : needRemove) {
            mItemContainer.removeView(remove);
        }
    }

    private View createItemView(String item) {
        return createItemView(item, mItemContainer);
    }

    private View createItemView(String item, ViewGroup parent) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_espresso, parent, false);
        if (view instanceof CheckBox) {
            ((CheckBox) view).setText(item);
        }
        return view;
    }
}
