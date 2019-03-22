package com.hfad.qpay;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView registerButton = (TextView) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(registerIntent);
            }
        });

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final Button loginButton = (Button) findViewById(R.id.loginButton);


        loginButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String message = "임시";
                String userID = idText.getText().toString();
                String userPassword = passwordText.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        String[] res = new String[2];
                        String token = "";
                        res = response.split(" ");
                        Log.d("응답",response);

                        if(res[0].equals("로그인완료")) {
                            Log.d("가입","가입");
                            Toast.makeText(getApplicationContext(),"로그인 완료", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, ReaderActivity.class);
                            intent.putExtra("token",res[1]);
                            MainActivity.this.startActivity(intent);
                        }
                        else{
                            Log.d("실패","실패");
                            Toast.makeText(getApplicationContext(),"로그인 실패", Toast.LENGTH_LONG).show();
                        }



                    }
                };

                LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
