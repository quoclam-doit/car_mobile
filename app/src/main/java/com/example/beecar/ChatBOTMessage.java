package com.example.beecar;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beecar.Adapter.ChatRVAdapter;
import com.example.beecar.DAO.RetrofitAPI;
import com.example.beecar.Model.ChatsModel;
import com.example.beecar.Model.MsgModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatBOTMessage extends AppCompatActivity {
    private Toolbar toolbar_acc;
    private RecyclerView chatsRV;
    private ImageButton sendMsgIB;
    private EditText userMsgEdt;
    private final String USER_KEY = "user";
    private final String BOT_KEY = "bot";
    private ArrayList<ChatsModel> chatsModelArrayList;
    private ChatRVAdapter chatRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot_message);

        toolbar_acc = findViewById(R.id.toolbar_acc);
        setSupportActionBar(toolbar_acc);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Trò chuyện");



        chatsRV = findViewById(R.id.idRVChats);
        sendMsgIB = findViewById(R.id.idIBSend);
        userMsgEdt = findViewById(R.id.idEdtMessage);
        chatsModelArrayList = new ArrayList<>();
        chatRVAdapter = new ChatRVAdapter(chatsModelArrayList, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        chatsRV.setLayoutManager(manager);
        chatsRV.setAdapter(chatRVAdapter);

        sendMsgIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userMsgEdt.getText().toString().isEmpty()) {
                    Toast.makeText(ChatBOTMessage.this, "Please enter your message.", Toast.LENGTH_SHORT).show();
                    return;
                }
                getResponse(userMsgEdt.getText().toString());
                chatsRV.scrollToPosition(chatsModelArrayList.size() - 1);

                userMsgEdt.setText("");

            }
        });

        userMsgEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkKeyBoard();
            }
        });


    }


    private void getResponse(String message) {
        chatsModelArrayList.add(new ChatsModel(message, USER_KEY));
        chatRVAdapter.notifyDataSetChanged();

        String url = "http://api.brainshop.ai/get?bid=173634&key=mYuiTYFh8eglHUOC&uid=[uid]&msg=[msg]" + message;
        String BASE_URL = "https://brainshop.ai/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<MsgModel> call = retrofitAPI.getMessage(url);
        call.enqueue(new Callback<MsgModel>() {
            @Override
            public void onResponse(Call<MsgModel> call, Response<MsgModel> response) {
                if(response.isSuccessful()){
                    MsgModel model = response.body();
                    chatsModelArrayList.add(new ChatsModel(model.getCnt(),BOT_KEY));
                    chatRVAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<MsgModel> call, Throwable t) {
                chatsModelArrayList.add(new ChatsModel("Please revert your question", BOT_KEY));
                chatRVAdapter.notifyDataSetChanged();
            }
        });
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
                    if(chatsModelArrayList.size() > 0){
                        chatsRV.scrollToPosition(chatsModelArrayList.size() - 1);
                        activityRootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

}
