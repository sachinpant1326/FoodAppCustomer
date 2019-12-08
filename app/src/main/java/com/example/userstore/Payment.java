package com.example.userstore;

import android.app.Activity;

import com.razorpay.Checkout;
import org.json.JSONObject;

import java.util.ArrayList;

public class Payment {
    String uname, uaddress, uphone;
    int price = 0;

    public Payment(String uname, String uaddress, String uphone) {
        this.uname = uname;
        this.uaddress = uaddress;
        this.uphone = uphone;
    }


    public void startPayment(Activity av) {
        calculatePrice();
        Home.uname = uname;
        Home.uaddress = uaddress;
        Home.uphone = uphone;
        Checkout checkout = new Checkout();
        final Activity activity = av;
        try {
            JSONObject options = new JSONObject();
            options.put("name", uname);
            options.put("description", "Testing");
            options.put("currency", "INR");
            options.put("amount", price * 100 + "");
            checkout.open(activity, options);
        } catch (Exception e) {
            System.out.print("Error");
        }
    }

    public void calculatePrice() {
        ArrayList<ModelOrder> arr = SingletonCart.getInstance().arr;
        for (int i = 0; i < arr.size(); i++) {
            int q = Integer.parseInt(arr.get(i).getItem_quantity());
            int p = Integer.parseInt(arr.get(i).getItem_price());
            price = price + p * q;
        }
    }
}
