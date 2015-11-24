package com.kobaken0029.aizuplanner.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kobaken0029.aizuplanner.R;
import com.kobaken0029.aizuplanner.view.OnFragmentInteractionListener;
import com.kobaken0029.aizuplanner.view.controller.impl.EventControllerImpl;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EventDetailFragment extends BaseFragment {
    public static final String TAG = EventDetailFragment.class.getSimpleName();

    private static final String ARG_EVENT_ID = "event_id";
    private static final String ARG_CONTENT_TEXT = "content_text";

    private String mEventId;
    private String mContentText;
    private OnFragmentInteractionListener mListener;

    @Bind(R.id.id)
    TextView mIdView;
    @Bind(R.id.content)
    TextView mContentView;

    public static EventDetailFragment newInstance(String eventId, String contentText) {
        EventDetailFragment fragment = new EventDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_EVENT_ID, eventId);
        args.putString(ARG_CONTENT_TEXT, contentText);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mEventId = getArguments().getString(ARG_EVENT_ID);
            mContentText = getArguments().getString(ARG_CONTENT_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);
        ButterKnife.bind(this, view);
        mIdView.setText(mEventId);
        mContentView.setText(mContentText);

        mEventController.init(getContext(), null);
        ButterKnife.bind(mEventController, view);
        return view;
    }

    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }

    @Override
    public void onDetach() {
        ButterKnife.unbind(mEventController);
        mListener = null;
        super.onDetach();
    }
}
