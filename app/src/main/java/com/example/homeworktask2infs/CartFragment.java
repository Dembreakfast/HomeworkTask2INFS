package com.example.homeworktask2infs;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class CartFragment extends Fragment {
    private RecyclerView checkoutRecyclerView;
    private CartAdapter cartAdapter;
    private RecyclerView.LayoutManager cartLayoutManager;
    private TextView cartTotal;
    private Double total;
    private ArrayList<CartItem> cartItemArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cartt, container, false);

        cartItemArrayList = load("orders", cartItemArrayList);
        view = buildRecyclerView(view);

        return view;
    }



    private ArrayList<CartItem> load(String key, ArrayList<CartItem> list) {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(key, null);
        Type menuJson = new TypeToken<ArrayList<CartItem>>(){}.getType();
        list = gson.fromJson(json, menuJson);

        if (list == null) {
            list = new ArrayList<>();
        }

        return list;
    }



    private Double orderTotal(ArrayList<CartItem> list) {
        int count = 0;
        double sum = 0.0;
        while(list.size() > count) {
            CartItem item = list.get(count);
            sum += (item.getItemAmount() * item.getItemPrice());
            count++;
        }
        return sum;
    }

    public View buildRecyclerView(View view) {
        checkoutRecyclerView = view.findViewById(R.id.cart_recyclerView);

        cartLayoutManager = new LinearLayoutManager(view.getContext());
        cartAdapter = new CartAdapter(view.getContext(), cartItemArrayList);

        checkoutRecyclerView.setLayoutManager(cartLayoutManager);
        checkoutRecyclerView.setAdapter(cartAdapter);

        cartTotal = view.findViewById(R.id.cart_total);

        total = orderTotal(cartItemArrayList);
        cartTotal.setText(this.getActivity().getString(R.string.total, NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(total)));


        return view;
    }
}
