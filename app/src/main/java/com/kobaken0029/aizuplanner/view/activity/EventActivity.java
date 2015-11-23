package com.kobaken0029.aizuplanner.view.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.Toolbar;

import com.kobaken0029.aizuplanner.R;
import com.kobaken0029.aizuplanner.view.adapter.dummy.DummyContent;
import com.kobaken0029.aizuplanner.view.fragment.EventDetailFragment;
import com.kobaken0029.aizuplanner.view.fragment.EventListFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EventActivity extends BaseActivity
        implements EventDetailFragment.OnFragmentInteractionListener, EventListFragment.OnListFragmentInteractionListener {
    public static final String TAG = EventActivity.class.getSimpleName();

    private String referer;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        replaceFragment(R.id.container, EventDetailFragment.newInstance(item.id, item.content), EventDetailFragment.TAG);
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, EventActivity.class));
    }

    public static void start(Context context, Parcelable date) {
        Intent intent = new Intent(context, EventActivity.class);
        intent.putExtra("date", date);
        context.startActivity(intent);
    }

    public static void start(Context context, DummyContent.DummyItem item) {
        Intent intent = new Intent(context, EventActivity.class);
        intent.putExtra("id", item.id);
        intent.putExtra("content", item.content);
        intent.putExtra("referer", CalendarActivity.TAG);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);
        mToolbarHelper.init(this, mToolbar, R.string.fragment_index_events, true);

        if (getIntent().getStringExtra("id") != null) {
            String id = getIntent().getStringExtra("id");
            String content = getIntent().getStringExtra("content");
            referer = getIntent().getStringExtra("referer");
            addFragment(R.id.container, EventDetailFragment.newInstance(id, content), EventDetailFragment.TAG);
            return;
        }

        if (savedInstanceState == null) {
            addFragment(R.id.container, EventListFragment.newInstance(1), EventListFragment.TAG);
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag(EventDetailFragment.TAG) != null
                && !referer.equals(CalendarActivity.TAG)) {
            replaceFragment(R.id.container, EventListFragment.newInstance(1), EventListFragment.TAG);
            return;
        }
        super.onBackPressed();
    }
}
