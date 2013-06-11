package pack.bi.core;

import pack.bi.model.Product;

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
}
