package lk.mobios.serappu.Owner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import lk.mobios.serappu.R;

public class OwnerMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_menu);
    }

    public void getcustomerFeedbacks(View view) {
        Intent i = new Intent(OwnerMenuActivity.this,FeedbackViewActivity.class);
        startActivity(i);
    }

    public void viewProducts(View view)
    {

    }
}
