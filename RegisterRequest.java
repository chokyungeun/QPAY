package com.hfad.qpay;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    final static private String URL="http://192.168.30.107:8000/register";
    private Map<String, String> parameters;

    public RegisterRequest(String userID, String userPassword, String userName, int userAge, Response.Listener<String> listener){

        super(Method.GET, URL+"?userID="+userID+"&userPassword="+userPassword, listener,null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userName", userName);
        parameters.put("userAge",userAge+"");

    }

    @Override
    public Map<String,String> getParams(){
        System.out.println("파라미터 확인");
        System.out.println(parameters.get("userID"));
        return parameters;
    }

}
