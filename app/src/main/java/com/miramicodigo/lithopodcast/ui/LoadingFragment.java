package com.miramicodigo.lithopodcast.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.miramicodigo.lithopodcast.specs.Loading;

public class LoadingFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ComponentContext viewContext = new ComponentContext(getContext());
        return LithoView.create(
                getContext(),
                Loading.create(viewContext)
                        .build());
    }
}
