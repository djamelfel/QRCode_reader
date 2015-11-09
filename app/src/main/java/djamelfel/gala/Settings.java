package djamelfel.gala;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Settings extends ActionBarActivity implements View.OnClickListener {
    private ArrayList<Key_List> key_list;
    private ArrayList<User> users = null;
    private ListViewAdapter adapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            key_list = extras.getParcelableArrayList("key_list");
            users = extras.getParcelableArrayList("users");
        }
        if (key_list != null) {
            adapter = new ListViewAdapter(this, R.layout.row_item, key_list);
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
        } else {
            key_list = new ArrayList<Key_List>();
            adapter = new ListViewAdapter(this, R.layout.row_item, key_list);
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }
        findViewById(R.id.new_key).setOnClickListener(this);
     }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.new_key:
                EditText id = (EditText)findViewById(R.id.id);
                EditText key = (EditText)findViewById(R.id.key);

                String idS = id.getText().toString();
                String keyS = key.getText().toString();

                if (idS.isEmpty() || keyS.isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.empty_text_area, Toast
                            .LENGTH_LONG).show();
                }
                else {
                    adapter.add(new Key_List(Integer.parseInt(idS), keyS));
                    id.setText("");
                    key.setText("");
                }
                break;
        }
    }

    public void removeKeyListOnClickHandler(View v) {
        Key_List keyList = (Key_List)v.getTag();
        adapter.remove(keyList);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Settings.this, Read_QR_Code.class);
        intent.putParcelableArrayListExtra("key_list", key_list);
        intent.putParcelableArrayListExtra("users", users);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(Settings.this, Read_QR_Code.class);
                intent.putParcelableArrayListExtra("key_list", key_list);
                intent.putParcelableArrayListExtra("users", users);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
