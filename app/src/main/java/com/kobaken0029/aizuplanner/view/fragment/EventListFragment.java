package com.kobaken0029.aizuplanner.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kobaken0029.aizuplanner.R;
import com.kobaken0029.aizuplanner.view.adapter.MyEventRecyclerViewAdapter;
import com.kobaken0029.aizuplanner.view.adapter.dummy.DummyContent;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class EventListFragment extends BaseFragment {
    public static final String TAG = EventListFragment.class.getSimpleName();

    private static final String ARG_TITLES = "titles";
    private static final String ARG_PLACES = "places";

    public static EventListFragment newInstance() {
        return new EventListFragment();
    }

    public static EventListFragment newInstance(ArrayList<String> titles, ArrayList<String> places) {
        EventListFragment fragment = new EventListFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_TITLES, titles);
        args.putStringArrayList(ARG_PLACES, places);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);

        if (view instanceof RecyclerView) {
            MyEventRecyclerViewAdapter adapter;
            adapter = new MyEventRecyclerViewAdapter(DummyContent.ITEMS);
            mEventController.init(getContext(), adapter);
            ButterKnife.bind(mEventController, view);
        }

        return view;
    }

    @Override
    public void onStart() {
        mEventController.onStart();
        super.onStart();
    }

    @Override
    public void onDetach() {
        ButterKnife.unbind(mEventController);
        super.onDetach();
    }
}
