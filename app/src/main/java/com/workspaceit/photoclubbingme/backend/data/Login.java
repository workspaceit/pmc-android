package com.workspaceit.photoclubbingme.backend.data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.workspaceit.photoclubbingme.activity.LoginActivity;
import com.workspaceit.photoclubbingme.backend.entity.Config;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Login {
    String message;

    public String getToken(RequestQueue requestQueue,final Context context){

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest sr = new StringRequest(Request.Method.POST,Config.DOMAIN+"oauth/token", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("JSONerror1",response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("JSONerror1",error.toString());
                try {
                    String body = new String(error.networkResponse.data,"UTF-8");
                    Log.i("volley", body);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }){

           @Override
            protected Map<String,String> getParams(){
                Log.i("volley", "inside param");
                Map<String,String> params = new HashMap<String, String>();
                params.put("client_id", "pmc-app-client");
                params.put("client_secret", "f6c3d96bc05036e738f0899ba149f447924b3a09");
                params.put("grant_type", "password");
                params.put("username", "p@pmc.com");
                params.put("password ", "123456");
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }

        };
        queue.add(sr);
        return "";
    }
}
