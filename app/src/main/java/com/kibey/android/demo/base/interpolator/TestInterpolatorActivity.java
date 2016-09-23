package com.kibey.android.demo.base.interpolator;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.kibey.android.demo.R;


public class TestInterpolatorActivity extends ActionBarActivity {

    private static final String IN_PG_NAME = "com.fichardu.interpolator.";
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private PanelView mPanelView;
    private ValueAnimator mAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_interpolator);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mPanelView = (PanelView) findViewById(R.id.anim_view);
        mDrawerList.setAdapter(
                new ArrayAdapter<String>(this, R.layout.drawer_list_item, mInterpolators));
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectDrawerItem(position);
            }
        });

        mAnimator = ValueAnimator.ofFloat(1.0f);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float timeFraction = animation.getCurrentPlayTime() / (float) animation.getDuration();
                float animFraction = ((Float) animation.getAnimatedValue()).floatValue();
                mPanelView.onAnimate(timeFraction, animFraction);

            }
        });
        mAnimator.setDuration(1000);
    }

    private void selectDrawerItem(int position) {
        String name = mInterpolators[position];
        TimeInterpolator interpolator = null;
        try {
            Class clazz = Class.forName(IN_PG_NAME + name);
            interpolator = (TimeInterpolator) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (interpolator != null) {
            mAnimator.cancel();
            mAnimator.setInterpolator(interpolator);

            mPanelView.reset();
            mAnimator.start();
        }


    }

    private static float dp2px(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }


    private static final String[] mInterpolators = new String[]{
            "EaseOutElasticInterpolator",
            "EaseInQuadInterpolator",
            "EaseOutQuadInterpolator",
            "EaseInOutQuadInterpolator",
            "EaseInQuartInterpolator",
            "EaseOutQuartInterpolator",
            "EaseInOutQuartInterpolator",
            "EaseInQuintInterpolator",
            "EaseOutQuintInterpolator",
            "EaseInOutQuintInterpolator",
            "EaseInCircInterpolator",
            "EaseOutCircInterpolator",
            "EaseInOutCircInterpolator",
            "EaseInCubicInterpolator",
            "EaseOutCubicInterpolator",
            "EaseInOutCubicInterpolator",
            "EaseInExpoInterpolator",
            "EaseOutExpoInterpolator",
            "EaseInOutExpoInterpolator",
            "EaseInBackInterpolator",
            "EaseOutBackInterpolator",
            "EaseInOutBackInterpolator",
            "EaseInElasticInterpolator",
            "EaseInOutElasticInterpolator",
            "EaseInBounceInterpolator",
            "EaseOutBounceInterpolator",
            "EaseInOutBounceInterpolator",
    };
}
