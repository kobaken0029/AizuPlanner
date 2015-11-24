package com.kobaken0029.aizuplanner.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kobaken0029.aizuplanner.R;
import com.kobaken0029.aizuplanner.model.Event;
import com.kobaken0029.aizuplanner.view.adapter.MyEventRecyclerViewAdapter;
import com.kobaken0029.aizuplanner.view.controller.impl.EventControllerImpl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
            if (getArguments() != null && getArguments().getStringArrayList(ARG_TITLES) != null) {
                List<String> titles = getArguments().getStringArrayList(ARG_TITLES);
                List<String> places = getArguments().getStringArrayList(ARG_PLACES);
                List<Event> events = new ArrayList<>();
                for (int i = 0; i < titles.size(); i++) {
                    Event e = new Event();
                    e.setTitle(titles.get(i));
                    e.setPlace(places.get(i));
                    events.add(e);
                }
                adapter = new MyEventRecyclerViewAdapter(events);
            } else {
                adapter = new MyEventRecyclerViewAdapter(new ArrayList<Event>());
            }
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
