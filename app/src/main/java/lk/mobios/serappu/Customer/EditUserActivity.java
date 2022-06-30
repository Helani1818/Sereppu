package lk.mobios.serappu.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import lk.mobios.serappu.R;

public class EditUserActivity extends AppCompatActivity {

    EditText et_name, et_email, et_address, et_tel;
    TextView btn_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_address = findViewById(R.id.et_address);
        et_tel = findViewById(R.id.et_tel);
        btn_edit = findViewById(R.id.btn_edit);

        CustomerHomeActivity activity = new CustomerHomeActivity();
        User lr = new User();
        lr = activity.getUserData();

        et_name.setText(lr.getName());
        et_email.setText(lr.getEmail());

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditUserActivity.this, "Successfully Edited", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(EditUserActivity.this,CustomerHomeActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
