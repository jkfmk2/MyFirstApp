package com.example.kuofengchang.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView item_list;
    private TextView show_app_name;

    private ArrayList<String> data = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        processViews();;
        processControllers();

        data.add("關於Android Tutorial的事情");
        data.add("一隻非常可愛的小狗狗!");
        data.add("一首非常好聽的音樂！");

        int layoutId = android.R.layout.simple_list_item_1;
        adapter = new ArrayAdapter<String>(this, layoutId, data);

        item_list.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            String titleText = data.getStringExtra("titleText");

            if (requestCode == 0) {
                this.data.add(titleText);
                adapter.notifyDataSetChanged();
            }
            else if (requestCode == 1) {
                int position = data.getIntExtra("position", -1);

                if(position != -1) {
                    this.data.set(position, titleText);
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    private void processViews() {
        item_list = (ListView) findViewById(R.id.item_list);
        show_app_name = (TextView) findViewById(R.id.show_app_name);
    }

    private void processControllers() {
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent("android.intent.action.EDIT_ITEM");
                intent.putExtra("position", position);
                intent.putExtra("titleText", data.get(position));
                startActivityForResult(intent, 1);
            }
        };

        item_list.setOnItemClickListener(itemListener);

        AdapterView.OnItemLongClickListener itemLongListener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                Toast.makeText(MainActivity.this,
                        "Long: " + data.get(position), Toast.LENGTH_LONG).show();
                return false;
            }
        };

        item_list.setOnItemLongClickListener(itemLongListener);

        View.OnLongClickListener listener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder d = new AlertDialog.Builder(MainActivity.this);
                d.setTitle(R.string.app_name)
                        .setMessage(R.string.about)
                        .show();
                return false;
            }
        };

        show_app_name.setOnLongClickListener(listener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void aboutApp(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void clickMenuItem(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.search_item:
                break;
            case R.id.add_item:
                Intent intent = new Intent("android.intent.action.ADD_ITEM");
                startActivityForResult(intent, 0);
                break;
            case R.id.revert_item:
                break;
            case R.id.delete_item:
                break;
        }

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("MenuItem Test")
              .setMessage(item.getTitle())
              .setIcon(item.getIcon())
              .show();
    }
}
