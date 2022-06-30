package lk.mobios.serappu.Products;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import lk.mobios.serappu.Customer.BillActivity;
import lk.mobios.serappu.Customer.MyCartActivity;
import lk.mobios.serappu.LoginActivity;
import lk.mobios.serappu.R;
import lk.mobios.serappu.RegisterActivity;

public class ProductSingleView extends AppCompatActivity {

    int counter=1;
    TextView increase, decrease, count ,btn_addToCart, btn_buy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_single_view);

        increase = findViewById(R.id.increase);
        decrease = findViewById(R.id.decrease);
        count = findViewById(R.id.count);
        btn_addToCart= findViewById(R.id.btn_addToCart);
        btn_buy = findViewById(R.id.btn_buy);

        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter=counter+1;
                Log.d("Count",String.valueOf(counter));
                count.setText(String.valueOf(counter));
            }
        });



        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter>0){
                    counter=counter-1;
                }else{
                    counter=0;
                }

                count.setText(String.valueOf(counter));
            }
        });

        btn_addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductSingleView.this, MyCartActivity.class);
                intent.putExtra("ProductName","Serappu 01");
                intent.putExtra("Price","250");
                intent.putExtra("Quantity",String.valueOf(counter));
                startActivity(intent);
            }
        });

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductSingleView.this, BillActivity.class);
                intent.putExtra("ProductName","Serappu 01");
                intent.putExtra("Price","250");
                intent.putExtra("Quantity",String.valueOf(counter));
                startActivity(intent);
            }
        });

    }
}
