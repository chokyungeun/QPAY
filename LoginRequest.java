package com.hfad.qpay;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;


public class LoginRequest extends StringRequest {
    final static private String URL = "http://192.168.30.107:8000/login";
    private Map<String, String> parameters;

    public LoginRequest(String userID, String userPassword, Response.Listener<String> listener) {
        super(Method.GET, URL+"?userID="+userID+"&userPassword="+userPassword, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;

    }
}
