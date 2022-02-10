package com.example.user_password_application_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>  {

    private OnRecyclerViewClickListener listener;

    public interface OnRecyclerViewClickListener{
        void OnItemClick(int position);
    }

    public void OnRecyclerViewClickListener (OnRecyclerViewClickListener listener){
        this.listener = listener;
    }

    private ArrayList<DataModel> dataSet;

    public CustomAdapter(ArrayList<DataModel> dataSet) {

        this.dataSet = dataSet;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder  {
        CardView cardView;
        TextView textViewName;
        TextView textViewDescription;
        ImageView imageViewIcon;
        TextView textViewphoneAddress;
        TextView textViewphoneNumber;

        public MyViewHolder (View itemView, OnRecyclerViewClickListener listener)
        {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.card_view);
            textViewName = ( TextView) itemView.findViewById(R.id.textViewTitle_ViewCard);
            imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
            textViewphoneAddress = ( TextView) itemView.findViewById(R.id.textViewAddress_CardView);
            textViewphoneNumber= ( TextView) itemView.findViewById(R.id.textViewTel_number_CardView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null && getAdapterPosition() != RecyclerView.NO_POSITION){
                        listener.OnItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext() ).inflate(R.layout.layout_card_view, parent ,false);
        MyViewHolder myViewHolder = new MyViewHolder(view, listener);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder,  int listPosition) {

        TextView textViewName = viewHolder.textViewName;
        ImageView imageView = viewHolder.imageViewIcon;
        CardView cardView = viewHolder.cardView;
        TextView textViewphoneNumber = viewHolder.textViewphoneNumber;
        TextView textViewaddress = viewHolder.textViewphoneAddress;

        textViewName.setText(dataSet.get(listPosition).getTitle());
        imageView.setImageResource(dataSet.get(listPosition).getImage());
        textViewphoneNumber.setText(  "מספר טלפון ליצירת קשר: " + "\n" + dataSet.get(listPosition).getPhoneNumber());
        textViewaddress.setText( "כתובת בית העסק: "+ "\n" + dataSet.get(listPosition).getAddress());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
