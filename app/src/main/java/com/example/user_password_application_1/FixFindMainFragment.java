package com.example.user_password_application_1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FixFindMainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FixFindMainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FixFindMainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FixFindMainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FixFindMainFragment newInstance(String param1, String param2) {
        FixFindMainFragment fragment = new FixFindMainFragment();
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
        View v =  inflater.inflate(R.layout.fragment_fix_find_main, container, false);
        initRecyclerView(v);
        return v;
    }

    private RecyclerView recycleView;
    private LinearLayoutManager layoutManager;
    private CustomAdapter addapter;
    private ArrayList<DataModel> dataSet;

    private void initRecyclerView(View v) {

        recycleView = (RecyclerView) v.findViewById(R.id.RecyclerViewFixFind);
        layoutManager = new LinearLayoutManager(getContext()); // new GridLayoutManager
        recycleView.setLayoutManager(layoutManager);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        dataSet = new ArrayList<DataModel>();

        for(int i = 0; i<MyData.Title.length ; i++)
        {
            dataSet.add(new DataModel(
                    MyData.Title[i],
                    MyData.Description[i],
                    MyData.id_[i],
                    MyData.drawableArray[i],
                    MyData.TelephoneNumber[i],
                    MyData.ShopAddress[i],
                    MyData.ShopLocation[i]
            ));
        }

        addapter = new CustomAdapter(dataSet);
        recycleView.setAdapter(addapter);

        addapter.OnRecyclerViewClickListener(new CustomAdapter.OnRecyclerViewClickListener() {
            @Override
            public void OnItemClick(int position) {
                Bundle Bundle = new Bundle();
                Bundle.putInt("key",position);
                Navigation.findNavController(v).navigate(R.id.action_fixFindFragment_to_fixFindSecondPageFragment,Bundle);
            }
        });
    }
}