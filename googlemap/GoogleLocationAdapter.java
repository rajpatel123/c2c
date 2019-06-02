package com.camtrack.tracker.googlemap;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.camtrack.tracker.R;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePredictionBufferResponse;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Display friend list
 *
 * @author eranga herath(erangaeb@gmail.com)
 */
public class GoogleLocationAdapter extends BaseAdapter implements Filterable {

    private GoogleAutoCompleteActivity activity;
    private LocationFilter friendFilter;
    private GeoDataClient geoDataClient;
    private ArrayList<Place> filteredList;

    private String TAG = GoogleLocationAdapter.class.getSimpleName();

    private AutocompleteFilter.Builder filterBuilder = new AutocompleteFilter.Builder();
    private GoogleApiInterface googleApiInterface;
    private Call<GooglePlace> googleCall;
    private String googleLey ;
    private final String COMPONENTS = "country:IN";


    GoogleLocationAdapter(GoogleAutoCompleteActivity activity, ArrayList<Place> placeList,
                          GoogleApiInterface apiInterface) {

        geoDataClient = Places.getGeoDataClient(activity);
        this.activity = activity;
        googleLey = activity.getString(R.string.google_map_key);
        this.filteredList = placeList;
        googleApiInterface = apiInterface;

        filterBuilder.setCountry("IN");

        getFilter();
    }

    /**
     * Get size of user list
     *
     * @return userList size
     */
    @Override
    public int getCount() {
        return filteredList.size();
    }

    /**
     * Get specific item from user list
     *
     * @param i item index
     * @return list item
     */
    @Override
    public Place getItem(int i) {
        return filteredList.get(i);
    }

    /**
     * Get user list item id
     *
     * @param i item index
     * @return current item id
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    public Place getMyAddress(int pos) {
        if (pos < filteredList.size()) {
            return filteredList.get(pos);
        }
        return null;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {

        final ViewHolder holder;
        final Place user = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(activity).inflate(R.layout.item_google_list, parent, false);
            holder = new ViewHolder();
            holder.name = view.findViewById(R.id.txt_first);
            holder.secondaryName = view.findViewById(R.id.txt_second);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.name.setText(user.getPrimaryName());
        holder.secondaryName.setText(user.getSecondaryName());


        return view;
    }

    /**
     * Get custom filter
     *
     * @return filter
     */
    @Override
    public Filter getFilter() {
        if (friendFilter == null) {
            friendFilter = new LocationFilter();
        }

        return friendFilter;
    }

    /**
     * Keep reference to children view to avoid unnecessary calls
     */
    static class ViewHolder {
        TextView name;
        TextView secondaryName;
    }

    /**
     * Custom filter for friend list
     * Filter content in friend list according to the search text
     */
    private class LocationFilter extends Filter {

        private Object lock = new Object();
        private Object lockTwo = new Object();
        private boolean placeResults = false;

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();
            placeResults = false;
            final List<Place> placesList = new ArrayList<>();

            if (prefix == null || prefix.length() == 0) {
                synchronized (lock) {
                    results.values = new ArrayList<Place>();
                    results.count = 0;
                }
            } else {
                String searchStrLowerCase = prefix.toString().toLowerCase();
//                searchStrLowerCase = searchStrLowerCase.replace(" ","%20");

                if (googleCall != null && googleCall.isExecuted()) {
                    googleCall.cancel();
                }

                googleCall = googleApiInterface.getPlaceList(searchStrLowerCase,COMPONENTS,googleLey);
                googleCall.enqueue(new Callback<GooglePlace>() {
                    @Override
                    public void onResponse(@NonNull Call<GooglePlace> call, @NonNull Response<GooglePlace> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "Auto complete prediction successful");
                            GooglePlace googlePlace = response.body();

                            if (googlePlace != null && googlePlace.getPredictions() != null && !googlePlace.getPredictions().isEmpty()) {
                                Place autoPlace;
                                for (Prediction prediction : googlePlace.getPredictions()) {
                                    if (prediction!=null){
                                        autoPlace = new Place();
                                        autoPlace.setPlaceId(prediction.getPlaceId());
                                        autoPlace.setPlaceText(prediction.getDescription());
                                        if (prediction.getStructuredFormatting() != null && !TextUtils.isEmpty(prediction.getStructuredFormatting().getMainText())) {
                                            autoPlace.setPrimaryName(prediction.getStructuredFormatting().getMainText());
                                            autoPlace.setSecondaryName(prediction.getStructuredFormatting().getSecondaryText());
                                        }
                                        placesList.add(autoPlace);
                                    }

                                }
                            }

                        } else {
                            Log.d(TAG, "Auto complete prediction unsuccessful");
                        }
                        //inform waiting thread about api call completion
                        placeResults = true;
                        synchronized (lockTwo) {
                            lockTwo.notifyAll();
                        }
                    }

                    @Override
                    public void onFailure(Call<GooglePlace> call, Throwable t) {
                        Log.d(TAG, "Auto complete prediction unsuccessful");
                    }
                });


                //wait for the results from asynchronous API call
                while (!placeResults) {
                    synchronized (lockTwo) {
                        try {
                            lockTwo.wait();
                        } catch (InterruptedException e) {

                        }
                    }
                }
                results.values = placesList;
                results.count = placesList.size();
                Log.d(TAG, "Autocomplete predictions size after wait" + results.count);
            }

            return results;
        }

        /**
         * Notify about filtered list to ui
         *
         * @param constraint text
         * @param results    filtered result
         */
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.values != null) {
                filteredList = (ArrayList<Place>) results.values;
            } else {
                if(filteredList!=null){
                    filteredList.clear();
                }
            }
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }

        private Task<AutocompletePredictionBufferResponse> getAutoCompletePlaces(String query) {

            Task<AutocompletePredictionBufferResponse> results =
                    geoDataClient.getAutocompletePredictions(query, null,
                            filterBuilder.build());

            return results;
        }
    }

}