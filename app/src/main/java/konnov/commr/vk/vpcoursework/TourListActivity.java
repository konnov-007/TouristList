package konnov.commr.vk.vpcoursework;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

public class TourListActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_list);
        listView = findViewById(R.id.listView);
        DBHelper dbHelper = new DBHelper(this);
        TourListAdapter tourListAdapter = new TourListAdapter(this, dbHelper.numberOfRows());
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(tourListAdapter);
    }

}
