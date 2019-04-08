package com.e.c2cjprtechnosoft.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.e.c2cjprtechnosoft.R;
import com.e.c2cjprtechnosoft.adapter.CustomExpandableListAdapter;
import com.e.c2cjprtechnosoft.model.ExpandableListDataPump;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        ButterKnife.bind(this);

    }

}