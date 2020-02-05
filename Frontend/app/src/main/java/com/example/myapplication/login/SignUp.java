package com.example.myapplication.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.R;
import com.example.myapplication.app.AppController;
import com.example.myapplication.user.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity {

    private TextView newEmail;
    private TextView newPass1;
    private TextView newPass2;
    private Button register;
    private String verify;
    private String userName;
    private String password1;
    private ArrayList<Users> user;
    private int size;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        newEmail = findViewById(R.id.newEmailButton);
        newPass1 = findViewById(R.id.newPassButton1);
        newPass2 = findViewById(R.id.newPassButton2);
        register = findViewById(R.id.registerButton);
        newEmail.setTextColor(Color.WHITE);
        newPass1.setTextColor(Color.WHITE);
        newPass2.setTextColor(Color.WHITE);
        register.setTextColor(Color.WHITE);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = newEmail.getText().toString();
                password1 = newPass1.getText().toString();
                String password2 = newPass2.getText().toString();
                if(password1.compareTo(password2) == 0){
                    String url = "http://coms-309-sb-1.misc.iastate.edu:8080/users/" + userName;
                    testLogin(url);

                }else{
                    toastMsg("Passwords Do Not Match!");
                }
            }
        });
    }

    private void userSize(){
        String urlz = "http://coms-309-sb-1.misc.iastate.edu:8080/users";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlz, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray newArray = response.getJSONArray("Users");
                            size = newArray.length();
                            createUser(userName, password1);

                        } catch (JSONException e) {
                            System.out.print("failed");

                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();;
                System.out.println("Fail");
//                initPokeArray();
            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }


    private void verifySignUp(String verify2){
        if(verify2.equals("-1")){
            userSize();

        }else{
            toastMsg("Username Already Exist!");
        }

    }
    private void toastMsg(String msg){
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }
    private void testLogin(String URL){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
//                           JSONArray newArray = response.getJSONArray("PokeTeam");
                            int id = response.getInt("Found");
                            verify = "" + id;
                            verifySignUp(verify);
                        } catch (JSONException e) {
                            System.out.print("failed");

                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();;
                System.out.println("Fail");
            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }
    private void createUser(String username, String password){
        String URL = "http://coms-309-sb-1.misc.iastate.edu:8080/user/create";

        JSONObject user = new JSONObject();
        try {
            user.put("userName", username);
            user.put("userAuthorization", "1");
//            user.put("userTeam", "");
//            user.put("userTeamTwo", "");
//            user.put("userTeamThree", "");
            user.put("userId", size);
            user.put("userPassword", password);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String jsonStr = user.toString();

        System.out.println("jsonString: "+jsonStr);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, user, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject objres = response;
                Toast.makeText(getApplicationContext(), objres.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Account Created" , Toast.LENGTH_LONG).show();
                loginReturn();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
            }
        }){


        };
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
    private void loginReturn(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

}

