package pack.bi.activities;

import pack.bi.R;
import pack.bi.utils.BiUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

    Button products, statistics, contact;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        products = (Button) findViewById(R.id.productsBtn);
        products.setOnClickListener(this);
        statistics = (Button) findViewById(R.id.statisticsBtn);
        statistics.setOnClickListener(this);
        contact = (Button) findViewById(R.id.contactBtn);
        contact.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        progress = ProgressDialog.show(this, "DB Initialization", "First time only!", false, true);
        BiUtils.initializeDatabase(this);
        progress.dismiss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.productsBtn:
                startActivity(new Intent(MainActivity.this, ProductsActivity.class));
                break;
            case R.id.statisticsBtn:
                startActivity(new Intent(MainActivity.this, OptionsActivity.class));
                break;
            case R.id.contactBtn:
                startActivity(new Intent(MainActivity.this, ContactActivity.class));
                break;
            default:
                break;
        }
    }
}
