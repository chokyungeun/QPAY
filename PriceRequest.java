package com.hfad.qpay;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class PriceRequest extends StringRequest {
    final static private String URL = "http://192.168.30.107:8000/cost";
    private Map<String, String> parameters;

    public PriceRequest(int sum, Response.Listener<String> listener) {
        super(Method.GET, URL+"?CostAmount="+sum, listener, null);
        parameters = new HashMap<>();
        parameters.put("sum",""+sum);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;

    }
}
