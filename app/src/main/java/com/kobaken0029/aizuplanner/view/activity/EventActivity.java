package com.kobaken0029.aizuplanner.view.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.kobaken0029.aizuplanner.R;
import com.kobaken0029.aizuplanner.view.adapter.dummy.DummyContent;
import com.kobaken0029.aizuplanner.view.fragment.EventDetailFragment;
import com.kobaken0029.aizuplanner.view.fragment.EventListFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EventActivity extends BaseActivity
        implements EventDetailFragment.OnFragmentInteractionListener, EventListFragment.OnListFragmentInteractionListener {
    public static final String TAG = EventActivity.class.getSimpleName();

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

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (savedInstanceState == null) {
            addFragment(R.id.container, EventListFragment.newInstance(1), EventListFragment.TAG);
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag(EventDetailFragment.TAG) != null) {
            replaceFragment(R.id.container, EventListFragment.newInstance(1), EventListFragment.TAG);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.search_events:
                SearchActivity.start(this);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
