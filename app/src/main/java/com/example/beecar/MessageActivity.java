package com.example.beecar;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beecar.Adapter.MessageAdapter;
import com.example.beecar.Model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {
    private EditText edtMessage;
    private Button btnSend;

    private RecyclerView rcvMessage;
    private MessageAdapter messageAdapter;
    private List<Message> mListMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        edtMessage = findViewById(R.id.edt_message);
        btnSend = findViewById(R.id.btn_send);
        rcvMessage = findViewById(R.id.rcv_message);



        mListMessage = new ArrayList<>();
        messageAdapter = new MessageAdapter();
        messageAdapter.setData(mListMessage);


        rcvMessage.setAdapter(messageAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvMessage.setLayoutManager(linearLayoutManager);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }


        });

        edtMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkKeyBoard();
            }
        });
    }
    private void sendMessage() {
        String strMessage = edtMessage.getText().toString().trim();
        if(TextUtils.isEmpty(strMessage)){
            return;
        }

        mListMessage.add(new Message(strMessage));
        messageAdapter.notifyDataSetChanged();

        rcvMessage.scrollToPosition(mListMessage.size() - 1);

        edtMessage.setText("");
    }

    private void checkKeyBoard(){
        final View activityRootView = findViewById(R.id.activityRoot);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();

                activityRootView.getWindowVisibleDisplayFrame(r);

                int heightDiff = activityRootView.getRootView().getHeight() - r.height();
                if(heightDiff > 0.25*activityRootView.getRootView().getHeight()){
                    if(mListMessage.size() > 0){
                        rcvMessage.scrollToPosition(mListMessage.size() - 1);
                        activityRootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            }
        });
    }
}
