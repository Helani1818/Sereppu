package lk.mobios.serappu.Owner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import lk.mobios.serappu.Common.DBHelper;
import lk.mobios.serappu.Customer.FeedbackSingleActivity;
import lk.mobios.serappu.Manager.DisplayPromo;
import lk.mobios.serappu.R;

public class FeedbackViewActivity extends AppCompatActivity {
    private DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_view);

        ListView obj;
        mydb = new DBHelper(this);
        Toast.makeText(this, "count : " + mydb.numberOfRowsFB(), Toast.LENGTH_SHORT).show();
        ArrayList array_list = mydb.getAllFeedbacks();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array_list);
        obj = (ListView) findViewById(R.id.listView2);
        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                int id_To_Search = arg2 + 1;

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

                Intent intent = new Intent(FeedbackViewActivity.this, FeedbackSingleActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
    }
}
