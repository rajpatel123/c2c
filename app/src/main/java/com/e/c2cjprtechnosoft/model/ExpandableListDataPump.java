package com.e.c2cjprtechnosoft.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> profile = new ArrayList<String>();
        profile.add("Bhawa Prakash");
        profile.add("+918979975567");
        profile.add("7677672728828278");
        profile.add("bhawa@gmail.com");


        expandableListDetail.put("PROFILE", profile);

        return expandableListDetail;
    }
}