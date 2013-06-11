package pack.bi.activities;

import pack.bi.R;
import pack.bi.adapters.ItemsAdapter;
import pack.bi.model.Product;
import pack.bi.storage.DatabaseHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductsActivity extends Activity implements OnItemClickListener {

    ListView productsList;
    List<String> items = new ArrayList<String>();
    List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_layout);
        productsList = (ListView) findViewById(R.id.productsListView);
        items.clear();
        DatabaseHandler handler = new DatabaseHandler(this);
        products = handler.getAllProducts();
        for (Product p : products) {
            items.add(p.get_name());
        }

        ItemsAdapter adapter = new ItemsAdapter(this, items);
        productsList.setAdapter(adapter);
        productsList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Intent intent = new Intent(ProductsActivity.this, ProductDetailsActivity.class);
        intent.putExtra(ProductDetailsActivity.product_key, products.get(arg2));
        startActivity(intent);
    }

}
