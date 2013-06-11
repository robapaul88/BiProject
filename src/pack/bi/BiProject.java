package pack.bi;

import pack.bi.model.Category;
import pack.bi.model.Product;
import pack.bi.model.Sales;
import pack.bi.utils.BiUtils;

import android.app.Application;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class BiProject extends Application {

    List<Product> products = new ArrayList<Product>();
    List<Category> categories = new ArrayList<Category>();
    List<Sales> sales = new ArrayList<Sales>();

    @Override
    public void onCreate() {
        super.onCreate();
        // creez un task asincron care sa initializeze baza de date la prima rulare cu date de test
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                BiUtils.initializeDatabase(getApplicationContext());
                return null;
            }

        }.execute();
    }

    /**
     * Use these to cache data outside database for faster operations
     *
     * @return
     */
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Sales> getSales() {
        return sales;
    }

    public void setSales(List<Sales> sales) {
        this.sales = sales;
    }
}
