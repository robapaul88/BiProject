package pack.bi.activities;

import pack.bi.R;
import pack.bi.model.Product;
import pack.bi.model.Sales;
import pack.bi.storage.DatabaseHandler;
import pack.bi.utils.BiUtils;
import pack.bi.utils.ImageDownloader;
import pack.bi.utils.ResultsListener;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import java.util.Calendar;
import java.util.Locale;

public class ProductDetailsActivity extends Activity implements ResultsListener {

    public static final String product_key = "selected_product_key";
    TextView productName, productDescription, productPrice;
    ViewAnimator productImageAnimator;
    EditText quantityField;
    Button buyButton;
    Product selectedProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details_layout);
        selectedProduct = (Product) getIntent().getExtras().getSerializable(product_key);

        productName = (TextView) findViewById(R.id.productDetailsName);
        productName.setText(selectedProduct.get_name());

        productDescription = (TextView) findViewById(R.id.productDetailsDescription);
        productDescription.setText(selectedProduct.get_description());

        productPrice = (TextView) findViewById(R.id.productDetailsPrice);
        productPrice.setText(String.valueOf(selectedProduct.get_price()));

        productImageAnimator = (ViewAnimator) findViewById(R.id.productViewAnimator);
        ProgressBar progressBar = new ProgressBar(this);
        ImageView productImage = new ImageView(this);
        productImageAnimator.addView(progressBar, 0);
        productImageAnimator.addView(productImage, 1);

        new ImageDownloader(productImage, this).execute(selectedProduct.get_photo());

        quantityField = (EditText) findViewById(R.id.productQuantityField);

        buyButton = (Button) findViewById(R.id.buyProductBtn);
        buyButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                try {
                    buyProduct(Integer.parseInt(quantityField.getText().toString()));
                } catch (NumberFormatException ex) {
                    Toast.makeText(ProductDetailsActivity.this, "Please enter appropriate quantity!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void buyProduct(int quantity) {
        DatabaseHandler handler = new DatabaseHandler(ProductDetailsActivity.this);
        String date = DateFormat.format(BiUtils.BI_DATE_FORMAT, Calendar.getInstance(Locale.getDefault())).toString();
        handler.addSale(new Sales(selectedProduct.get_idProduct(), quantity, date));
        Toast.makeText(ProductDetailsActivity.this,
                        "You have purchased " + quantity + " " + selectedProduct.get_name() + "s for " + (quantity * selectedProduct.get_price())
                                        + " Euro !", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResultsSucceeded() {
        productImageAnimator.showNext();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
