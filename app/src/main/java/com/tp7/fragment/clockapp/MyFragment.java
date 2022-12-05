package com.tp7.fragment.clockapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFragment extends Fragment {

    boolean dateOK = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (dateOK) {
            return (inflater.inflate(R.layout.frag_date, container, false));
        }else{
            return (inflater.inflate(R.layout.frag_time, container, false));
        }

    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        dateOK = args.getBoolean("dateOK");
    }


}