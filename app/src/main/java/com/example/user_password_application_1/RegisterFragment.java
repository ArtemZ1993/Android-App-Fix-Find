package com.example.user_password_application_1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        View viewNew = inflater.inflate(R.layout.fragment_register, container, false);

        TextView textViewRegisterTextEmail = viewNew.findViewById(R.id.RegisterTextEmail);
        textViewRegisterTextEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus == true){
                    textViewRegisterTextEmail.setText("");
                }

                if(textViewRegisterTextEmail.length() == 0 && hasFocus == false){
                    textViewRegisterTextEmail.setText("Email");
                }
            }
        });

        TextView textViewRegisterTextPhone = viewNew.findViewById(R.id.RegisterTextPhone);
        textViewRegisterTextPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus == true){
                    textViewRegisterTextPhone.setText("");
                }

                if(textViewRegisterTextPhone.length() == 0 && hasFocus == false){
                    textViewRegisterTextPhone.setText("Phone");
                }
            }
        });

        TextView textViewRegisterTextPassword = viewNew.findViewById(R.id.RegisterTextPassword);
        textViewRegisterTextPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus == true){
                    textViewRegisterTextPassword.setText("");
                }

                if(textViewRegisterTextPassword.length() == 0 && hasFocus == false){
                    textViewRegisterTextPassword.setText("Password");
                }
            }
        });

        TextView textViewRegisterTextUserName = viewNew.findViewById(R.id.RegisterTextUserName);
        textViewRegisterTextUserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus == true){
                    textViewRegisterTextUserName.setText("");
                }

                if(textViewRegisterTextUserName.length() == 0 && hasFocus == false){
                    textViewRegisterTextUserName.setText("User Name");
                }
            }
        });

        Button RegisterButton = viewNew.findViewById(R.id.RegisterButtonRegister);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.RegisterAccount();
            }
        });

        return viewNew;
    }
}