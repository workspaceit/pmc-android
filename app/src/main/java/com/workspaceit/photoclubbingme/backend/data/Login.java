package com.workspaceit.photoclubbingme.backend.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workspaceit.photoclubbingme.backend.entity.Config;
import com.workspaceit.photoclubbingme.backend.entity.LoggedInPhotographer;
import com.workspaceit.photoclubbingme.backend.entity.OauthCredential;
import org.json.JSONObject;

import java.io.IOException;

public class Login {
    private String message="0";
    private String message1="1";
    public void getToken(final Context context,final RequestQueue requestQueue, final String[] user,final VolleyCallback callback){
        String url=Config.DOMAIN+"oauth/token?client_id=pmc-app-client&client_secret=f6c3d96bc05036e738f0899ba149f447924b3a09&grant_type=password&username="+user[0]+"&password="+user[1];
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.POST,url,null,new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            final OauthCredential data = mapper.readValue(response.toString(), OauthCredential.class);
                            SharedPreferences.Editor editor = context.getSharedPreferences("MyData",Context.MODE_PRIVATE).edit();
                            editor.putString("access_token", data.getAccess_token());
                            signIn(context,requestQueue,data.getAccess_token(),new VolleyCallback(){
                                @Override
                                public void onSuccess(final String result) {
                                    callback.onSuccess(result);
                                }
                            });
                        } catch (IOException e) { e.printStackTrace(); }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onSuccess("unauthorized");
            }
        });
        Singleton.getInstance(context).addToRequestQueue(jsonObjRequest);
    }
    public void signIn(final Context context,RequestQueue requestQueue,final String token,final VolleyCallback callback){
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.GET,Config.DOMAIN+"auth/api/user-service/get?access_token="+token,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    LoggedInPhotographer loggedInPhotographer = mapper.readValue(response.toString(), LoggedInPhotographer.class);
                    callback.onSuccess("success");
                } catch (IOException e) { e.printStackTrace(); }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onSuccess("unauthorized");
            }
        });
        Singleton.getInstance(context).addToRequestQueue(jsonObjRequest);
    }
}
