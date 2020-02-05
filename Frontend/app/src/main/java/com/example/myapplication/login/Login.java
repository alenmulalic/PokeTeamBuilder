package com.example.myapplication.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    private Button signUpButton;
    private Button loginInButton;
    private EditText loginUserName;
    private EditText loginPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginmain);

        loginInButton = findViewById(R.id.loginInButton);
        signUpButton = findViewById(R.id.signUpButton);
        loginUserName = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
        loginInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://coms-309-sb-1.misc.iastate.edu:8080/login/" + loginUserName.getText().toString() + "/" + loginPassword.getText().toString();
                loginVerify(url);
            }
        });
    }
    public void loginVerify(String URL){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
//                           JSONArray newArray = response.getJSONArray("PokeTeam");
                            int id = response.getInt("Id");
                            String id2 = "" + id;
                            loginSuccessful(id2);

                        } catch (JSONException e) {
                            System.out.print("failed");

                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println("Fail");
            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }

    public void signUp(){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
    public void loginSuccessful(String id){
        if(id.equals("-1")){
            Toast.makeText(this, "Username or Password is Incorrect", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("result", "success");
            startActivity(intent);
        }

    }

}
