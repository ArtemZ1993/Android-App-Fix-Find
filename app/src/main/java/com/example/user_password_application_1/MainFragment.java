package com.example.user_password_application_1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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


        View viewNew = inflater.inflate(R.layout.fragment_main, container, false);
        sayHelloToUser(viewNew);

        Button Button = viewNew.findViewById(R.id.goToForum);

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_forumFragment);
            }
        });

        Button Button1 = viewNew.findViewById(R.id.goToLogin);

        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_fragmentLogin);
            }
        });

        Button Button2 = viewNew.findViewById(R.id.buttonGoToMaps);
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_mapsFragment);
            }
        });

        Button Button3 = viewNew.findViewById(R.id.buttonFindWhoFix);
        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_fixFindFragment);
            }
        });

        return viewNew;
    }

    private void sayHelloToUser(View viewNew) {

        MainActivity mainActivity = (MainActivity) getActivity();
        TextView TextView2 = ((TextView)viewNew.findViewById(R.id.textViewMainfragmentUserHello2));
        TextView TextView1 = ((TextView)viewNew.findViewById(R.id.textViewMainfragmentUserHello1));

        if(mainActivity.IsLogin){
           try {

               TextView1.setText( " !!! "+ MainActivity.UserName + "שלום ");
               TextView2.setText("טוב שחזרתם :)");


           }catch (Exception e){
               TextView1.setText("שלום משתמש");
               TextView2.setText("נא להיכנס למערכת כדי להנות מהשירותים :) ");
           }


        }else{
            TextView1.setText("שלום משתמש");
            TextView2.setText("נא להיכנס למערכת כדי להנות מהשירותים :) ");
        }
    }
}