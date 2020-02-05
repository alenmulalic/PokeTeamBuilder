package com.example.myapplication.FriendsAndChat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class Chat extends AppCompatActivity {

    private Button start;
    private TextView output;
    private OkHttpClient client;
    private TextInputEditText sendText;
    private WebSocket ws;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        start = findViewById(R.id.startWebsocket);
        output =  findViewById(R.id.outputWebsocket);
        sendText = findViewById(R.id.sendTextWebsocket);
        client = new OkHttpClient();
        start();
        start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String data = sendText.getText().toString();
                send(ws, data);

            }
        });
    }
    public void send(WebSocket webSocket, String send){
        webSocket.send(send);
    }

    private final class EchoWebSocketListener extends WebSocketListener
    {
        private static final int NORMAL_CLOSURE_STATUS = 1000;
        @Override
        public void onOpen(WebSocket webSocket, Response response)
        {
            String data = sendText.getText().toString();

            webSocket.send(data);
//            webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");
        }
        @Override
        public void onMessage(WebSocket webSocket, String text) {
            output("Receiving : " + text);
        }
        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes)
        {
            output("Receiving bytes : " + bytes.hex());
        }
        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
            output("Closing : " + code + " / " + reason);
        }
        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response)
        {
            output("Error : " + t.getMessage());
        }
    }

    private void start()
    {
        Request request = new Request.Builder().url("ws://coms-309-sb-1.misc.iastate.edu:8080/chat/4").build();
//        Request request = new Request.Builder().url("wss://echo.websocket.org").build();
//        Request request = new Request.Builder().url("http://coms-309-sb-1.misc.iastate.edu:8080/app/chat.sendMessage").build();
        EchoWebSocketListener listener = new EchoWebSocketListener();
        ws = client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
    }
    private void output(final String txt)
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                output.setText(output.getText().toString() + "\n\n" + txt);
            }
        });
    }
}