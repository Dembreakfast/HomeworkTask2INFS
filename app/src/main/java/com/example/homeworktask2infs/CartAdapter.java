package com.example.homeworktask2infs;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CheckoutViewHolder>{
    private ArrayList<CartItem> cartList;
    private Context cartContext;

//  setting up an array to put the food items into the cart

    public CartAdapter(Context context, ArrayList<CartItem> list) {
        cartList = list;
        cartContext = context;
    }

    @NonNull
    @Override
    public CheckoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CheckoutViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutViewHolder holder, int position) {

        // making the view holder
        CartItem cartItem = cartList.get(position);
        String itemImage = cartItem.getItemImage();
        String itemName = cartItem.getItemName();
        Double itemPrice = cartItem.getItemPrice();
        Integer itemQuantity = cartItem.getItemAmount();

        // using picasso class to get the image from asset and placing it into the checkout image holder
        Picasso.get().load("file:///android_asset/" + itemImage).fit().centerInside().into(holder.itemImage);
        holder.itemName.setText(itemName);
        holder.itemPrice.setText(NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(itemPrice * itemQuantity));
        holder.itemQuantity.setText(cartContext.getString(R.string.quantity, itemQuantity));


    }

    public class CheckoutViewHolder extends RecyclerView.ViewHolder {
        // setting up the views for the holder
        public ImageView itemImage;
        public TextView itemName;
        public TextView itemPrice;
        public TextView itemQuantity;


        public CheckoutViewHolder(View itemView) {
            super(itemView);
            // linking the views to the xml files
            itemImage = itemView.findViewById(R.id.item_image);
            itemName = itemView.findViewById(R.id.item_name);
            itemPrice = itemView.findViewById(R.id.item_price);
            itemQuantity = itemView.findViewById(R.id.item_quantity);

                }




    }

}