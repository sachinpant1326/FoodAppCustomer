package com.example.userstore;

import java.util.ArrayList;

public class ModelCart
{
    public ArrayList<ModelOrder> arr;
    private static ModelCart ob=null;

    private ModelCart()
    {
        arr=new ArrayList<>();
    }

    public static ModelCart getInstance()
    {
        if(ob==null)
            ob=new ModelCart();
        return ob;
    }
}
