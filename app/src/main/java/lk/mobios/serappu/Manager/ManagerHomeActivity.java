package lk.mobios.serappu.Manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import lk.mobios.serappu.MainActivity;
import lk.mobios.serappu.R;
import lk.mobios.serappu.RegisterActivity;

public class ManagerHomeActivity extends AppCompatActivity {

    TextView btn_mnge_products ,btn_mnge_promotions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home);

        btn_mnge_products = findViewById(R.id.btn_mnge_products);
        btn_mnge_promotions = findViewById(R.id.btn_mnge_promotions);

        btn_mnge_products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerHomeActivity.this, ManageProductMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_mnge_promotions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerHomeActivity.this, ManagePromotionMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
