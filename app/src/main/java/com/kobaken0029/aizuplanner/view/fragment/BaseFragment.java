package com.kobaken0029.aizuplanner.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.kobaken0029.aizuplanner.AizuPlannerApplication;
import com.kobaken0029.aizuplanner.view.controller.EventController;
import com.kobaken0029.aizuplanner.view.controller.impl.EventControllerImpl;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class BaseFragment extends Fragment {
    @Inject
    EventController mEventController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInjector();
    }

    @Override
    public void onDetach() {
        ButterKnife.unbind(this);
        super.onDetach();
    }

    private void initializeInjector() {
        ((AizuPlannerApplication) getActivity().getApplication())
                .getApplicationComponent().inject(this);
    }
}
