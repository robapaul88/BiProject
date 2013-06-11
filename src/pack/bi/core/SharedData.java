package pack.bi.core;

import pack.bi.model.Product;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class SharedData {
    private static SharedData _instance;
    public String _selectedProduct;
    public Product _myProduct;

    private SharedData() {
        _instance = this;
    }

    public static SharedData getInstance() {
        if (_instance == null) {
            new SharedData();
        }
        return _instance;
    }

    /**
     *
     * @param str
     * @param context
     */
    public void showAlert(String str, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(str).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        AlertDialog alert = builder.create();

        // Display dialog box
        alert.show();
    }

}
