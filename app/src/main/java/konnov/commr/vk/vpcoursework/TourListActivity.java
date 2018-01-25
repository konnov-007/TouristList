package konnov.commr.vk.vpcoursework;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class TourListActivity extends AppCompatActivity {
    ListView listView;
    TourListAdapter tourListAdapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_list);
        renderingTheList();
    }

    void renderingTheList(){
        listView = findViewById(R.id.listView);
        dbHelper = new DBHelper(this);
        tourListAdapter = new TourListAdapter(this, dbHelper.numberOfRowsInToursTable());
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(tourListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ArrayList<ArrayList<String>> theData = dbHelper.toursTableToList();
                Intent intent = new Intent(TourListActivity.this, ChangeTourActivity.class);
                intent.putExtra("name", theData.get(position).get(0));
                intent.putExtra("country", theData.get(position).get(1));
                startActivity(intent);
            }
        });



        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int itemToDelete = i;

                new AlertDialog.Builder(TourListActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Вы уверены?")
                        .setMessage("Вы уверены, что вы хотите удалить этот тур?")
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        ArrayList<ArrayList<String>> theData = dbHelper.toursTableToList();
                                        dbHelper.deleteItemFromToursTable(theData.get(itemToDelete).get(0), theData.get(itemToDelete).get(1));
                                        renderingTheList();
                                    }
                                }
                        )
                        .setNegativeButton("Нет", null)
                        .show();
                return true;
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        renderingTheList();
    }
}
