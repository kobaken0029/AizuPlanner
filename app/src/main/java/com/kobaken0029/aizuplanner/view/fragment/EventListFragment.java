package com.kobaken0029.aizuplanner.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kobaken0029.aizuplanner.R;
import com.kobaken0029.aizuplanner.model.Event;
import com.kobaken0029.aizuplanner.view.adapter.MyEventRecyclerViewAdapter;
import com.kobaken0029.aizuplanner.view.adapter.dummy.DummyContent;
import com.kobaken0029.aizuplanner.view.adapter.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventListFragment extends BaseFragment {
    public static final String TAG = EventListFragment.class.getSimpleName();

    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_TITLES = "titles";
    private static final String ARG_PLACES = "places";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    public static EventListFragment newInstance(int columnCount) {
        EventListFragment fragment = new EventListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
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
                adapter = new MyEventRecyclerViewAdapter(events, mListener);
            } else {
                adapter = new MyEventRecyclerViewAdapter(Arrays.asList(new Event(), new Event()), mListener);
            }
            recyclerView.setAdapter(adapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Event item);
    }
}
