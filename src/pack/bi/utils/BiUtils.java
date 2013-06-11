package pack.bi.utils;

import pack.bi.model.Category;
import pack.bi.model.Product;
import pack.bi.model.Sales;
import pack.bi.storage.DatabaseHandler;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class BiUtils {
    public static final String BI_DATE_FORMAT = "dd-MM-yyyy";
    private static final String already_initialized_key = "already_initialized";

    public static void initializeDatabase(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        boolean alreadyInserted = prefs.getBoolean(already_initialized_key, false);
        // astfel se introduc o singura data informatiile in baza de date
        if (!alreadyInserted) {
            // insert products
            DatabaseHandler handler = new DatabaseHandler(context);
            handler.addProduct(new Product(2, "Apples", "Apple description", 1, "http://www.seriouseats.com/images/20080920inseason-apples2.jpg"));
            handler.addProduct(new Product(2, "Bananas", "Bananas description", 1, "sample_url"));
            handler.addProduct(new Product(2, "Oranges", "Oranges description", 1, "sample_url"));
            handler.addProduct(new Product(2, "Pineapple", "Pineapple description", 1, "sample_url"));
            handler.addProduct(new Product(2, "Mango", "Mango description", 1, "sample_url"));
            handler.addProduct(new Product(1, "Potatoes", "Potatoes description", 1, "sample_url"));
            handler.addProduct(new Product(1, "Tomatoes", "Tomatoes description", 1, "sample_url"));
            handler.addProduct(new Product(1, "Onions", "Onions description", 1, "sample_url"));
            handler.addProduct(new Product(1, "Lettuce", "Lettuce description", 1, "sample_url"));
            handler.addProduct(new Product(3, "Rice", "Rice description", 1, "sample_url"));
            handler.addProduct(new Product(3, "Pasta", "Pasta description", 1, "sample_url"));
            handler.addProduct(new Product(3, "Bread", "Bread description", 1, "sample_url"));
            handler.addProduct(new Product(8, "Pizza", "Pizza description", 1, "sample_url"));
            handler.addProduct(new Product(4, "Beef Mince", "Beef Mince description", 1, "sample_url"));
            handler.addProduct(new Product(4, "Chicken Breast", "Chicken Breast description", 1, "sample_url"));
            handler.addProduct(new Product(4, "Salmon Fillets", "Salmon Fillets description", 1, "sample_url"));
            handler.addProduct(new Product(5, "Pasta Sauce", "Pasta Sauce description", 1, "sample_url"));
            handler.addProduct(new Product(5, "Curry Sauce", "Curry Sauce description", 1, "sample_url"));
            handler.addProduct(new Product(6, "Cheese", "Cheese description", 1, "sample_url"));
            handler.addProduct(new Product(6, "Butter", "Butter description", 1, "sample_url"));
            handler.addProduct(new Product(6, "Plain Yoghurt", "Plain Yoghurt description", 1, "sample_url"));
            handler.addProduct(new Product(7, "Drink", "Drink description", 1, "sample_url"));
            handler.addProduct(new Product(7, "Milk", "Milk description", 1, "sample_url"));
            handler.addProduct(new Product(7, "Fresh Orange Juice", "Fresh Orange Juice description", 1, "sample_url"));
            handler.addProduct(new Product(7, "Cola", "Cola description", 1, "sample_url"));
            handler.addProduct(new Product(7, "Beer", "Beer description", 1, "sample_url"));
            handler.addProduct(new Product(7, "Wine", "Wine description", 1, "sample_url"));

            // insert categories
            handler.addCategory(new Category("Vegetables"));// id = 1
            handler.addCategory(new Category("Fruits"));// id = 2
            handler.addCategory(new Category("Grains"));// id = 3
            handler.addCategory(new Category("Beefs"));// id = 4
            handler.addCategory(new Category("Sauces"));// id = 5
            handler.addCategory(new Category("Fats"));// id = 6
            handler.addCategory(new Category("Drinks"));// id = 7
            handler.addCategory(new Category("Custom"));// id = 8

            // insert sales
            handler.addSale(new Sales(1, 1, "01-01-2013"));
            handler.addSale(new Sales(1, 1, "01-01-2013"));
            handler.addSale(new Sales(1, 1, "01-01-2013"));
            handler.addSale(new Sales(2, 1, "01-01-2013"));
            handler.addSale(new Sales(2, 1, "01-01-2013"));
            handler.addSale(new Sales(2, 1, "01-01-2013"));
            handler.addSale(new Sales(3, 1, "01-01-2013"));
            handler.addSale(new Sales(3, 1, "01-01-2013"));
            handler.addSale(new Sales(3, 1, "01-01-2013"));
            handler.addSale(new Sales(4, 1, "01-01-2013"));

            prefs.edit().putBoolean(already_initialized_key, true).commit();
        }

    }
}
