package com.example.userstore;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Cart extends Fragment implements View.OnClickListener
{

    RecyclerView rv1;
    CartAdapter adapter;
    ArrayList<ModelOrder> item;

    Button b1;

    public Cart() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_cart, container, false);
        rv1=v.findViewById(R.id.cart_rv);
        b1=v.findViewById(R.id.cart_b1);
        b1.setOnClickListener(this);
        rv1.setHasFixedSize(true);
        rv1.setLayoutManager(new LinearLayoutManager(getContext()));

        item=ModelCart.getInstance().arr;     // data from singleton Modelcart

        adapter=new CartAdapter(getActivity(),item);
        rv1.setAdapter(adapter);
        return v;
    }

    public void onClick(View v)
    {
        if(v==b1)
        {
            DatabaseReference data_ref=FirebaseDatabase.getInstance().getReference("Orders");
            ArrayList<ModelOrder> arr=ModelCart.getInstance().arr;
            for(int i=0;i<arr.size();i++)
            {
                ModelOrder ob=arr.get(i);
                String uploadid=data_ref.push().getKey();
                data_ref.child(uploadid).setValue(ob);
            }

            ModelCart.getInstance().arr=new ArrayList<>();

        }
    }
}