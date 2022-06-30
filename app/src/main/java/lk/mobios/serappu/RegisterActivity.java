package lk.mobios.serappu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import lk.mobios.serappu.Common.DBHelper;
import lk.mobios.serappu.Customer.CustomerHomeActivity;

public class RegisterActivity extends AppCompatActivity {
    private DBHelper mydb ;
    TextView et_name,et_email,et_usrname,et_pswd,et_confirm_pwd,btn_register, link_reg;
    String _name,_email,_usrname,_pwd,_confirmpwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_usrname = findViewById(R.id.et_usrname);
        et_pswd = findViewById(R.id.et_pswd);
        et_confirm_pwd = findViewById(R.id.et_confirm_pwd);
        btn_register =  findViewById(R.id.btn_register);
        link_reg = findViewById(R.id.link_reg);
        mydb = new DBHelper(this);

        link_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _name = et_name.getText().toString().trim();
                _email = et_email.getText().toString().trim();
                _usrname = et_usrname.getText().toString().trim();
                _pwd = et_pswd.getText().toString().trim();
                _confirmpwd = et_confirm_pwd.getText().toString().trim();

                if(_name.equals("")||_email.equals("")||_usrname.equals("")||_pwd.equals("")||_confirmpwd.equals("")){
                    Toast.makeText(RegisterActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else if(!_pwd.equals(_confirmpwd)){
                    Toast.makeText(RegisterActivity.this, "Passwords are not similar", Toast.LENGTH_SHORT).show();
                } else {
                    //Save
                    mydb.insertNewUser(_name,_email,_usrname,_pwd);

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();


                }
            }
        });
    }
}
