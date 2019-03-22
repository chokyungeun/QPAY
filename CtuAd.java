package com.hfad.qpay.lout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CtuAd extends ArrayAdapter<CtuAdapter_Item>
{
    private Context context;
    private int mResource;
    private ArrayList<CtuAdapter_Item> mList;
    private LayoutInflater mInflater;

    public CtuAd(Context context, int layoutResource, ArrayList<CtuAdapter_Item> arylist_main)
    {
        super(context, layoutResource, arylist_main);
        this.context = context;
        this.mResource = layoutResource;
        this.mList = arylist_main;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        CtuAdapter_Item oneData = mList.get(position);
        if(convertView == null)
        {   convertView = mInflater.inflate(mResource, null);}

        if(oneData != null)
        {
            ((TextView)convertView.findViewById(context.getResources().getIdentifier("textname", "id", context.getPackageName()))).setText((String)oneData.getItem());
            ((TextView)convertView.findViewById(context.getResources().getIdentifier("textprice", "id", context.getPackageName()))).setText((String)oneData.getPrice());

        }
        else
        {
            ((TextView)convertView.findViewById(context.getResources().getIdentifier("textname", "id", context.getPackageName()))).setText("NULL");
        }

        return convertView;
    }

    public void addItem(String name, String price)
    {
        CtuAdapter_Item item = new CtuAdapter_Item();

        item.setItem(name);
        item.setPrice(price);

        mList.add(item);
    }

    public void remove(int position) {
        mList.remove(position);
    }

    public String getname(int position)
    {
        return mList.get(position).getItem();
    }
}
