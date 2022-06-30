package lk.mobios.serappu.Manager;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import lk.mobios.serappu.Common.DBHelper;
import lk.mobios.serappu.R;

public class DisplayPromoUserActivity extends AppCompatActivity {
    private DBHelper mydb;
    int id_To_Update = 0;

    TextView promo_title, promo_desc;
    String title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_promo_user);
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
}
