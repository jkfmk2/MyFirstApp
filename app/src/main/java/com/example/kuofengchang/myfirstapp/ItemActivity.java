package com.example.kuofengchang.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ItemActivity extends AppCompatActivity {

    private EditText title_text, content_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        processViews();

        Intent intent = getIntent();
        String action = intent.getAction();

        if (action.equals("android.intent.action.EDIT_ITEM")) {
            String titleText = intent.getStringExtra("titleText");
            title_text.setText(titleText);
        }
    }

    private void processViews() {
        title_text = (EditText) findViewById(R.id.title_text);
        content_text = (EditText) findViewById(R.id.content_text);
    }

    public void onSubmit(View view) {
        if (view.getId() == R.id.ok_item) {
            String titleText = title_text.getText().toString();
            String contentText = content_text.getText().toString();

            Intent result = getIntent();

            result.putExtra("titleText", titleText);
            result.putExtra("contentText", contentText);

            setResult(Activity.RESULT_OK, result);
        }

        finish();
    }

    public void clickFunction(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.take_picture:
                break;
            case R.id.record_sound:
                break;
            case R.id.set_location:
                break;
            case R.id.select_color:
                break;
        }

    }
}
