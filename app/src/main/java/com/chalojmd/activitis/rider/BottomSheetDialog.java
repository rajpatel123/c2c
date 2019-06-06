package com.chalojmd.activitis.rider;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chalojmd.R;
import com.chalojmd.fragments.BookYourRideFragment;

public class BottomSheetDialog extends BookYourRideFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.bottomsheet_layout,container,false);
       return v;
    }
}
