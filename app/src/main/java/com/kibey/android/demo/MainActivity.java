package com.kibey.android.demo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.util.SortedList;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kibey.android.demo.base.BaseActivity;
import com.kibey.android.demo.utils.DividerItemDecoration;

/**
 * 所有Activity列表
 *
 * @author xl
 * @version V1.0
 * @since 16/9/19
 */
public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.content_rv);
        ActivityAdapter adapter = new ActivityAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
            if (null != packageInfo) {
                ActivityInfo[] activities = packageInfo.activities;
                for (ActivityInfo activity : activities) {
                    if (this.getClass().getName().equals(activity.name)) {
                        continue;
                    }
                    adapter.addItem(activity);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {
        private SortedList<ActivityInfo> mSortedList;

        public ActivityAdapter() {
            mSortedList = new SortedList<>(ActivityInfo.class, new SortedListAdapterCallback<ActivityInfo>(ActivityAdapter.this) {
                @Override
                public int compare(ActivityInfo o1, ActivityInfo o2) {
                    // 按类名称排序
                    return getActivityName(o1).compareTo(getActivityName(o2));
                }

                @Override
                public boolean areContentsTheSame(ActivityInfo oldItem, ActivityInfo newItem) {
                    return oldItem.name.equals(newItem.name);
                }

                @Override
                public boolean areItemsTheSame(ActivityInfo item1, ActivityInfo item2) {
                    return item1.equals(item2);
                }
            });
        }

        public void addItem(ActivityInfo activityInfo) {
            mSortedList.add(activityInfo);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final ActivityInfo info = mSortedList.get(position);
            holder.mTitle.setText(getActivityName(info));
            holder.mContent.setText(info.name);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openActivity(info);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mSortedList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView mTitle;
            private TextView mContent;

            public ViewHolder(ViewGroup parent) {
                super(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item, parent, false));
                mTitle = (TextView) itemView.findViewById(R.id.item_title);
                mContent = (TextView) itemView.findViewById(R.id.item_content);
            }
        }
    }

    private static String getActivityName(ActivityInfo activityInfo) {
        String[] split = activityInfo.name.split("\\.");
        return split[split.length - 1];
    }

    private void openActivity(ActivityInfo activityInfo) {
        Intent intent = new Intent();
        intent.setClassName(MainActivity.this, activityInfo.name);
        startActivity(intent);
    }
}