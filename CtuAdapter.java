package com.hfad.qpay.lout;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.hfad.qpay.R;

import java.util.ArrayList;

public class CtuAdapter extends BaseAdapter {
    private ArrayList<CtuAdapter_Item> listViewItemList = new ArrayList<CtuAdapter_Item>();

    public CtuAdapter() {

    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.productlayout, parent, false);
        }

        TextView nameTextView = (TextView) convertView.findViewById(R.id.textname);
        TextView priceTextView = (TextView) convertView.findViewById(R.id.textprice);

        CtuAdapter_Item listviewitem = listViewItemList.get(position);

        nameTextView.setText(listviewitem.getItem());
        priceTextView.setText(listviewitem.getPrice());

        return convertView;
    }

    public void addItem(String name, String price) {
        CtuAdapter_Item item = new CtuAdapter_Item();

        item.setItem(name);
        item.setPrice(price);

        listViewItemList.add(item);
    }
}

