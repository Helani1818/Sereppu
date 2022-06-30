package lk.mobios.serappu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import lk.mobios.serappu.Common.DBHelper;
import lk.mobios.serappu.Customer.CustomerHomeActivity;
import lk.mobios.serappu.Manager.ManagerHomeActivity;
import lk.mobios.serappu.Owner.OwnerMenuActivity;

public class LoginActivity extends AppCompatActivity {
    private DBHelper mydb;
    TextView link_reg;
    EditText et_username, et_password;
    String usertype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mydb = new DBHelper(this);
        link_reg = findViewById(R.id.link_reg);
        et_password = findViewById(R.id.et_password);
        et_username = findViewById(R.id.et_username);

        link_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void login(View view) {
        String _username = et_username.getText().toString().trim();
        String _password = et_password.getText().toString().trim();


        if (_username.equals("") || _password.equals("")) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        } else if (_username.equalsIgnoreCase("manager") || _password.equals("12345")) {
            usertype="manager";
            Intent intent = new Intent(LoginActivity.this, ManagerHomeActivity.class);
            startActivity(intent);
            finish();
        } else if (_username.equalsIgnoreCase("admin") || _password.equals("admin")) {
            usertype="owner";
            Intent intent = new Intent(LoginActivity.this, OwnerMenuActivity.class);
            startActivity(intent);
            finish();
        } else {
            Cursor rs = mydb.getUserPWD(_username);
            Log.d("Count", String.valueOf(mydb.numberOfRows()));
            rs.moveToFirst();
            String a = DBHelper.USER_COLUMN_PASSWORD;
            int index = rs.getColumnIndex(a);
            String DB_pwd = rs.getString(index);

            if (_password.equals(DB_pwd)) {
                usertype="user";
                Intent intent = new Intent(LoginActivity.this, CustomerHomeActivity.class);
                intent.putExtra("username",_username);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show();
            }

        }

    }
}
