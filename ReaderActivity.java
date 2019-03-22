package com.hfad.qpay;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.hfad.qpay.lout.CtuAd;
import com.hfad.qpay.lout.CtuAdapter;
import com.hfad.qpay.lout.CtuAdapter_Item;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class ReaderActivity extends AppCompatActivity {
    private Button scan_btn;
    private Button buy_btn;
    CtuAd adapter;

    ArrayList<CtuAdapter_Item> listViewItemList = null;
    Activity context = null;
    int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        context = this;
        Intent intent = getIntent();
        String token = intent.getExtras().getString("token");
        Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
        scan_btn = (Button) findViewById(R.id.scan_btn);

        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(context);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

        buy_btn = (Button) findViewById(R.id.buy_btn);

        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < listViewItemList.size(); i++) {
                    sum += Integer.parseInt(listViewItemList.get(i).getPrice());
                }


                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        int size = listViewItemList.size();
                        Log.d("costresult",response);
                        Log.d("개수", String.valueOf(listViewItemList.size()));
                        if(response.equals("결제완료"))
                        {
                            for (int i = 0; i < size; i++) {
                                listViewItemList.remove(0);
                                Log.d("개수", "실행");
                            }
                            sum = 0;
                            Toast.makeText(getApplicationContext(),"결제완료",Toast.LENGTH_LONG).show();
                            adapter.notifyDataSetChanged();

                        }
                    }
                };

                PriceRequest PriceRequest = new PriceRequest(sum, responseListener);
                RequestQueue queue = Volley.newRequestQueue(ReaderActivity.this);
                queue.add(PriceRequest);

            }
        });

        //ListView
        ListView listView;
        listViewItemList = new ArrayList<>();

        adapter = new CtuAd(getApplicationContext(), R.layout.productlayout, listViewItemList);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                String message = "해당 데이터를 삭제하시겠습니까?" +

                        "position : " + position + " : " + adapter.getname(position);

                DialogInterface.OnClickListener deleteListner = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                };

                new AlertDialog.Builder(context)
                        .setTitle("삭제")
                        .setMessage(message)
                        .setPositiveButton("삭제", deleteListner)
                        .show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            } else {
                String res = result.getContents();
                String[] a = new String[2];

                a = res.split(" ");
                adapter.addItem(a[0], a[1]);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, res, Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
