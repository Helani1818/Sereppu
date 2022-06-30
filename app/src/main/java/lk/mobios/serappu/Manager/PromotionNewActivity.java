package lk.mobios.serappu.Manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import lk.mobios.serappu.Common.DBHelper;
import lk.mobios.serappu.R;

public class PromotionNewActivity extends AppCompatActivity {
    EditText et_promo_title, et_promo_description;
    private DBHelper mydb;
    String title, desc,id_To_Update;
    TextView btn_addPromotion;
    boolean updated =false;
    String val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_new);

        mydb = new DBHelper(this);
        et_promo_title =findViewById(R.id.et_promo_title);
        et_promo_description =findViewById(R.id.et_promo_description);
        btn_addPromotion = findViewById(R.id.btn_addPromotion);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String update = extras.getString("Update");
            if(update.equals("1")){
                title = extras.getString("title");
                desc = extras.getString("desc");
                id_To_Update = extras.getString("id_To_Update");
                et_promo_title.setText(title);
                et_promo_description.setText(desc);
                btn_addPromotion.setText("Update Promotion");
                updated=true;
                val=id_To_Update;
            }
        }

    }

    public void addPromo(View view) {

        String promoTitle = et_promo_title.getText().toString().trim();
        String promoDesc = et_promo_description.getText().toString().trim();


        if(promoTitle.equals("")||promoDesc.equals("")){
            Toast.makeText(this, "Fill all the fields", Toast.LENGTH_SHORT).show();
        }else{
            //Add new Promo
            if(updated==false){
                mydb.insertNewPromo(promoTitle,promoDesc);
                Intent i = new Intent(this,PromotionListActivity.class);
                startActivity(i);
                finish();
            }else {
                int iu = Integer.valueOf(val);
                mydb.updatePromo(iu,promoTitle,promoDesc);
                Intent i = new Intent(this,PromotionListActivity.class);
                startActivity(i);
                finish();
            }

        }



    }
}
