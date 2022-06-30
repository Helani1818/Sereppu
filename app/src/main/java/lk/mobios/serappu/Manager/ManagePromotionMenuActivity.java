package lk.mobios.serappu.Manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import lk.mobios.serappu.Customer.BillActivity;
import lk.mobios.serappu.Products.ProductSingleView;
import lk.mobios.serappu.R;

public class ManagePromotionMenuActivity extends AppCompatActivity {
    TextView btn_promotion_add, btn_promotion_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_promotion_menu);

        btn_promotion_add = findViewById(R.id.btn_promotion_add);
        btn_promotion_view = findViewById(R.id.btn_promotion_view);




        btn_promotion_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagePromotionMenuActivity.this, PromotionNewActivity.class);
                startActivity(intent);
            }
        });
        btn_promotion_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagePromotionMenuActivity.this, PromotionListActivity.class);
                startActivity(intent);
            }
        });
    }
}
