package com.kobaken0029.aizuplanner.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.kobaken0029.aizuplanner.R;
import com.kobaken0029.aizuplanner.model.Event;
import com.kobaken0029.aizuplanner.view.fragment.EventDetailFragment;
import com.kobaken0029.aizuplanner.view.fragment.EventListFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EventActivity extends BaseActivity {
    public static final String TAG = EventActivity.class.getSimpleName();

    private String referer;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private Toolbar.OnMenuItemClickListener mMenuItemClickListener =
            new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.search_events:
                            SearchActivity.start(EventActivity.this);
                            return true;
                        default:
                            break;
                    }
                    return true;
                }
            };

    public static void start(Context context) {
        context.startActivity(new Intent(context, EventActivity.class));
    }

    public static void start(Context context, ArrayList<String> titles, ArrayList<String> places) {
        Intent intent = new Intent(context, EventActivity.class);
        intent.putStringArrayListExtra("titles", titles);
        intent.putStringArrayListExtra("places", places);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void start(Context context, Parcelable date) {
        Intent intent = new Intent(context, EventActivity.class);
        intent.putExtra("date", date);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void start(Context context, Event item) {
        Intent intent = new Intent(context, EventActivity.class);
        intent.putExtra("id", item.getTitle());
        intent.putExtra("content", item.getPlace());
        intent.putExtra("referer", CalendarActivity.TAG);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);
        mToolbarHelper.init(this, mToolbar, R.string.fragment_index_events, mMenuItemClickListener);

        if (getIntent().getStringExtra("id") != null) {
            String id = getIntent().getStringExtra("id");
            String content = getIntent().getStringExtra("content");
            referer = getIntent().getStringExtra("referer");
            addFragment(R.id.container, EventDetailFragment.newInstance(id, content), EventDetailFragment.TAG);
            return;
        } else if (getIntent().getSerializableExtra("titles") != null) {
            ArrayList<String> titles = getIntent().getStringArrayListExtra("titles");
            ArrayList<String> place = getIntent().getStringArrayListExtra("places");
            addFragment(R.id.container, EventListFragment.newInstance(titles, place), EventListFragment.TAG);
        }

        if (savedInstanceState == null) {
            addFragment(R.id.container, EventListFragment.newInstance(), EventListFragment.TAG);
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag(EventDetailFragment.TAG) != null
                && !referer.equals(CalendarActivity.TAG)) {
            replaceFragment(R.id.container, EventListFragment.newInstance(), EventListFragment.TAG);
            return;
        }
        super.onBackPressed();
    }
}
