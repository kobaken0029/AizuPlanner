package com.kobaken0029.aizuplanner.view.controller;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kobaken0029.aizuplanner.R;
import com.kobaken0029.aizuplanner.model.Event;
import com.kobaken0029.aizuplanner.view.OnListFragmentInteractionListener;
import com.kobaken0029.aizuplanner.view.activity.EventActivity;
import com.kobaken0029.aizuplanner.view.adapter.MyEventRecyclerViewAdapter;

import butterknife.Bind;

public class EventController {
    @Nullable
    @Bind(R.id.list)
    RecyclerView mRecyclerView;

    private Context mContext;
    @Nullable
    private MyEventRecyclerViewAdapter mAdapter;

    public void init(Context context, MyEventRecyclerViewAdapter adapter) {
        mContext = context;
        if (adapter != null) {
            mAdapter = adapter;
        }
    }

    public void onStart() {
        if (mAdapter != null) {
            mAdapter.setOnListFragmentInteractionListener(mListInteractionListener);
            if (mRecyclerView != null) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                mRecyclerView.setAdapter(mAdapter);
            }
        }
    }

    private OnListFragmentInteractionListener mListInteractionListener =
            new OnListFragmentInteractionListener() {
                @Override
                public void onListFragmentInteraction(Event item) {
                    EventActivity.start(mContext, item);
                }
            };
}
