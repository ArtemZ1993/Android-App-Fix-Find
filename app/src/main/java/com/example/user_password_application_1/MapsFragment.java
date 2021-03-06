package com.example.user_password_application_1;

import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MapsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapsFragment newInstance(String param1, String param2) {
        MapsFragment fragment = new MapsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);
        showAllShopsInTheMap(supportMapFragment);
        return view;
    }

    private void showAllShopsInTheMap(SupportMapFragment supportMapFragment) {

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {

                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.GetMyLocation();

                LatLng MyLocationNow = new LatLng(MainActivity.latitude, MainActivity.longitude);
                googleMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                        .position(MyLocationNow).title("My Location")).showInfoWindow();

                for (int i :MyData.id_) {

                    String[] StringLocationArr = MyData.ShopLocation[i].split(",");

                    LatLng sydney = new LatLng(Float.parseFloat(StringLocationArr[0]), Float.parseFloat(StringLocationArr[1]));
                    float[] distance = new float[1];

                    Location.distanceBetween(sydney.latitude,sydney.longitude,MyLocationNow.latitude,MyLocationNow.longitude,distance);
                    Float newdistance = (distance[0]/1000);

                    googleMap.addMarker(new MarkerOptions().position(sydney).title(MyData.Title[i])
                            .snippet(MyData.ShopAddress[i] +String.format(" %.02f ", newdistance)+"??\"?? " ));
                }

                googleMap.moveCamera(CameraUpdateFactory.newLatLng(MyLocationNow));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MyLocationNow, 14));
            }
        });
    }
}