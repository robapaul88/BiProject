package pack.bi.utils;

import pack.bi.model.Category;
import pack.bi.model.Product;
import pack.bi.model.Sales;
import pack.bi.storage.DatabaseHandler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class BiUtils {
    public static final String BI_DATE_FORMAT = "dd-MM-yyyy";
    private static final String already_initialized_key = "already_initialized";

    /**
     * Initializeaza baza de date cu date de test doar la prima rulare
     *
     * @param context
     */
    public static void initializeDatabase(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        boolean alreadyInserted = prefs.getBoolean(already_initialized_key, false);
        // astfel se introduc o singura data informatiile in baza de date
        if (!alreadyInserted) {
            // insert products
            DatabaseHandler handler = new DatabaseHandler(context);
            handler.addProduct(new Product(2, "Apples", "Apple description", 1, "http://www.seriouseats.com/images/20080920inseason-apples2.jpg"));
            handler.addProduct(new Product(2, "Bananas", "Bananas description", 2, "sample_url"));
            handler.addProduct(new Product(2, "Oranges", "Oranges description", 3, "sample_url"));
            handler.addProduct(new Product(2, "Pineapple", "Pineapple description", 4, "sample_url"));
            handler.addProduct(new Product(2, "Mango", "Mango description", 3, "sample_url"));
            handler.addProduct(new Product(1, "Potatoes", "Potatoes description", 2, "sample_url"));
            handler.addProduct(new Product(1, "Tomatoes", "Tomatoes description", 1, "sample_url"));
            handler.addProduct(new Product(1, "Onions", "Onions description", 2, "sample_url"));
            handler.addProduct(new Product(1, "Lettuce", "Lettuce description", 3, "sample_url"));
            handler.addProduct(new Product(3, "Rice", "Rice description", 4, "sample_url"));
            handler.addProduct(new Product(3, "Pasta", "Pasta description", 3, "sample_url"));
            handler.addProduct(new Product(3, "Bread", "Bread description", 2, "sample_url"));
            handler.addProduct(new Product(8, "Pizza", "Pizza description", 1, "sample_url"));
            handler.addProduct(new Product(4, "Beef Mince", "Beef Mince description", 2, "sample_url"));
            handler.addProduct(new Product(4, "Chicken Breast", "Chicken Breast description", 3, "sample_url"));
            handler.addProduct(new Product(4, "Salmon Fillets", "Salmon Fillets description", 4, "sample_url"));
            handler.addProduct(new Product(5, "Pasta Sauce", "Pasta Sauce description", 3, "sample_url"));
            handler.addProduct(new Product(5, "Curry Sauce", "Curry Sauce description", 2, "sample_url"));
            handler.addProduct(new Product(6, "Cheese", "Cheese description", 1, "sample_url"));
            handler.addProduct(new Product(6, "Butter", "Butter description", 2, "sample_url"));
            handler.addProduct(new Product(6, "Plain Yoghurt", "Plain Yoghurt description", 3, "sample_url"));
            handler.addProduct(new Product(7, "Drink", "Drink description", 4, "sample_url"));
            handler.addProduct(new Product(7, "Milk", "Milk description", 3, "sample_url"));
            handler.addProduct(new Product(7, "Fresh Orange Juice", "Fresh Orange Juice description", 2, "sample_url"));
            handler.addProduct(new Product(7, "Cola", "Cola description", 1, "sample_url"));
            handler.addProduct(new Product(7, "Beer", "Beer description", 2, "sample_url"));
            handler.addProduct(new Product(7, "Wine", "Wine description", 3, "sample_url"));

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
            handler.addSale(new Sales(1, 3, "01-01-2013"));
            handler.addSale(new Sales(1, 2, "01-01-2013"));
            handler.addSale(new Sales(1, 1, "01-01-2013"));
            handler.addSale(new Sales(2, 2, "01-01-2013"));
            handler.addSale(new Sales(2, 3, "01-01-2013"));
            handler.addSale(new Sales(2, 4, "01-01-2013"));
            handler.addSale(new Sales(3, 5, "01-01-2013"));
            handler.addSale(new Sales(3, 6, "01-01-2013"));
            handler.addSale(new Sales(3, 5, "01-01-2013"));
            handler.addSale(new Sales(4, 4, "01-01-2013"));
            handler.addSale(new Sales(5, 3, "01-01-2013"));
            handler.addSale(new Sales(5, 2, "01-01-2013"));
            handler.addSale(new Sales(6, 1, "01-01-2013"));
            handler.addSale(new Sales(7, 2, "01-01-2013"));
            handler.addSale(new Sales(8, 3, "01-01-2013"));
            handler.addSale(new Sales(8, 4, "01-01-2013"));
            handler.addSale(new Sales(9, 5, "01-01-2013"));
            handler.addSale(new Sales(10, 6, "01-01-2013"));
            handler.addSale(new Sales(10, 7, "01-01-2013"));
            handler.addSale(new Sales(11, 6, "01-01-2013"));
            handler.addSale(new Sales(12, 5, "01-01-2013"));
            handler.addSale(new Sales(13, 4, "01-01-2013"));
            handler.addSale(new Sales(14, 3, "01-01-2013"));
            handler.addSale(new Sales(15, 2, "01-01-2013"));
            handler.addSale(new Sales(16, 1, "01-01-2013"));
            handler.addSale(new Sales(17, 2, "01-01-2013"));
            handler.addSale(new Sales(18, 3, "01-01-2013"));
            handler.addSale(new Sales(19, 4, "01-01-2013"));
            handler.addSale(new Sales(20, 6, "01-01-2013"));
            handler.addSale(new Sales(20, 7, "01-01-2013"));
            handler.addSale(new Sales(21, 6, "01-01-2013"));
            handler.addSale(new Sales(22, 5, "01-01-2013"));
            handler.addSale(new Sales(23, 4, "01-01-2013"));
            handler.addSale(new Sales(24, 3, "01-01-2013"));
            handler.addSale(new Sales(25, 2, "01-01-2013"));
            handler.addSale(new Sales(26, 1, "01-01-2013"));
            handler.addSale(new Sales(27, 2, "01-01-2013"));

            prefs.edit().putBoolean(already_initialized_key, true).commit();
        }
    }

    /**
     * Afiseaza mesajul primit ca parametru intr-un dialog
     *
     * @param str - mesajul ce va fi afisat
     * @param context
     */
    public static void showAlert(String str, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(str).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();

        // Display dialog box
        alert.show();
    }
}
