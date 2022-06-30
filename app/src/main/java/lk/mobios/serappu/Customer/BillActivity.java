package lk.mobios.serappu.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import lk.mobios.serappu.R;

public class BillActivity extends AppCompatActivity {
    String _productName, _qty, _unitPrice;
    int tot;
    TextView p_name, qty, uPrice, totPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        p_name = findViewById(R.id.p_name);
        qty = findViewById(R.id.qty);
        uPrice = findViewById(R.id.uPrice);
        totPrice = findViewById(R.id.totPrice);

        Intent intent = getIntent();
        if (intent != null) { //Null Checking
            _productName = intent.getStringExtra("ProductName");
            _qty = intent.getStringExtra("Quantity");
            _unitPrice = intent.getStringExtra("Price");
        }

        p_name.setText(_productName);
        qty.setText(String.valueOf(_qty));
        uPrice.setText(String.valueOf(_unitPrice));

        tot = Integer.valueOf(_qty) * Integer.valueOf(_unitPrice);
        totPrice.setText(String.valueOf(tot));


    }

    public void ok(View view) {
        Intent i = new Intent(this, CustomerHomeActivity.class);
        startActivity(i);
        finish();
    }
}
