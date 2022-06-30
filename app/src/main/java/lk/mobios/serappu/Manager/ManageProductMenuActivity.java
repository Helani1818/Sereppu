package lk.mobios.serappu.Manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import lk.mobios.serappu.Products.ProductAddNewActivity;
import lk.mobios.serappu.R;

public class ManageProductMenuActivity extends AppCompatActivity {
    TextView btn_product_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_product_menu);
        btn_product_add = findViewById(R.id.btn_product_add);

        btn_product_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageProductMenuActivity.this, ProductAddNewActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
