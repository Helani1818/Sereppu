package lk.mobios.serappu.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import lk.mobios.serappu.Common.DBHelper;
import lk.mobios.serappu.R;

public class FeedbackSingleActivity extends AppCompatActivity {
    TextView feedback_name, feedback;
    private DBHelper mydb;
    int id_To_Update = 0;
    String _name, _feedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_single);
        feedback_name = findViewById(R.id.feedback_name);
        feedback = findViewById(R.id.feedback);

        mydb = new DBHelper(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                Cursor rs = mydb.getDataFB(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                _name = rs.getString(rs.getColumnIndex(DBHelper.FB_COLUMN_NAME));
                _feedback = rs.getString(rs.getColumnIndex(DBHelper.FB_COLUMN_FEEDBACK));
                if (!rs.isClosed()) {
                    rs.close();
                }

                feedback_name.setText(_name);
                feedback.setText(_feedback);
            }
        }
    }
}
