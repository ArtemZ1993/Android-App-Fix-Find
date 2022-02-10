package com.example.user_password_application_1;

import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FixFindSecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FixFindSecondFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FixFindSecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FixFindSecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FixFindSecondFragment newInstance(String param1, String param2) {
        FixFindSecondFragment fragment = new FixFindSecondFragment();
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

    TextView textViewDistanceBetweenTwoPointsTemp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View viewNew = inflater.inflate(R.layout.fragment_fix_find_second, container, false);
         Integer i = this.getArguments().getInt("key");
         ImageView image = (ImageView)viewNew.findViewById(R.id.imageView_New);
         TextView textViewName =  (TextView) viewNew.findViewById(R.id.textViewTitle_ViewCard_New);
         TextView textViewDescription = (TextView)viewNew.findViewById(R.id.textViewDescription_ViewCard_New);
         TextView textViewphoneAddress = (TextView)viewNew.findViewById(R.id.textViewAddress_CardView_New);
         TextView textViewphoneNumber = (TextView)viewNew.findViewById(R.id.textViewTel_number_CardView_New);

         textViewDistanceBetweenTwoPointsTemp = (TextView)viewNew.findViewById(R.id.textViewDistanceBetweenTwoPoints);

         image.setImageResource(MyData.drawableArray[i]);
         textViewName.setText(MyData.Title[i]);
         textViewDescription.setText("תיאור העסק:" + "\n" +MyData.Description[i]);
         textViewphoneAddress.setText("כתובת בית העסק: "+ "\n" +MyData.ShopAddress[i]);
         textViewphoneNumber.setText("מספר טלפון ליצירת קשר: " + "\n" + MyData.TelephoneNumber[i]);

         SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);
         extracted(supportMapFragment,i);

        return viewNew;
    }

    private void extracted(SupportMapFragment supportMapFragment,Integer i) {
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {

                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.GetMyLocation();

                LatLng MyLocationNow = new LatLng(MainActivity.latitude, MainActivity.longitude);
                googleMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                        .position(MyLocationNow).title("My Location")).showInfoWindow();

                String[] StringLocationArr = MyData.ShopLocation[i].split(",");
                LatLng loction = new LatLng(Float.parseFloat(StringLocationArr[0]), Float.parseFloat(StringLocationArr[1]));
                googleMap.addMarker(new MarkerOptions()
                        .position(loction).title(MyData.Title[i]));


                float[] distance = new float[1];
                Location.distanceBetween(loction.latitude,loction.longitude,MyLocationNow.latitude,MyLocationNow.longitude,distance);
                Float newdistance = (distance[0]/1000);

                textViewDistanceBetweenTwoPointsTemp.setText("מרחק ממקומי:" +"\n" +String.format(" %.02f ", newdistance) +"ק\"מ " );

                googleMap.moveCamera(CameraUpdateFactory.newLatLng(MyLocationNow));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MyLocationNow, 14));

            }
        });
    }
}