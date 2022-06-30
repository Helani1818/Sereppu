package lk.mobios.serappu.Manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import lk.mobios.serappu.Common.DBHelper;
import lk.mobios.serappu.R;

public class DisplayPromo extends AppCompatActivity {
    private DBHelper mydb;
    int id_To_Update = 0;

    TextView promo_title, promo_desc;
    String title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_promo);
        promo_title = findViewById(R.id.promo_title);
        promo_desc = findViewById(R.id.promo_desc);


        mydb = new DBHelper(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                title = rs.getString(rs.getColumnIndex(DBHelper.PROMO_COLUMN_TITLE));
                description = rs.getString(rs.getColumnIndex(DBHelper.PROMO_COLUMN_DESCRIPTION));
                if (!rs.isClosed()) {
                    rs.close();
                }

                promo_title.setText(title);
                promo_desc.setText(description);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();
        int Value = extras.getInt("id");
        if (extras != null) {
            if (Value > 0) {
                getMenuInflater().inflate(R.menu.main, menu);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.action_Edit:
                Intent intent = new Intent(this, PromotionNewActivity.class);
                intent.putExtra("Update", "1");
                intent.putExtra("id_To_Update", id_To_Update);
                intent.putExtra("title", title);
                intent.putExtra("desc", description);
                startActivity(intent);
                break;

            case R.id.action_Delete:
                mydb.deletePromos(id_To_Update);
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, PromotionListActivity.class);
                startActivity(i);
                finish();
        }
        return true;
    }
}
