package pack.bi.model;

import java.io.Serializable;

public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    int _idProduct;
    int _idType;
    String _name;
    String _description;
    int _price;
    String _photo;

    public Product() {
    }

    public Product(int _idType, String _name, String _description, int _price, String _photo) {
        super();
        this._idType = _idType;
        this._name = _name;
        this._description = _description;
        this._price = _price;
        this._photo = _photo;
    }

    public Product(int _idProduct, int _idType, String _name, String _description, int _price, String _photo) {
        super();
        this._idProduct = _idProduct;
        this._idType = _idType;
        this._name = _name;
        this._description = _description;
        this._price = _price;
        this._photo = _photo;
    }

    public int get_idProduct() {
        return _idProduct;
    }

    public void set_idProduct(int _idProduct) {
        this._idProduct = _idProduct;
    }

    public int get_idType() {
        return _idType;
    }

    public void set_idType(int _idType) {
        this._idType = _idType;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public int get_price() {
        return _price;
    }

    public void set_price(int _price) {
        this._price = _price;
    }

    public String get_photo() {
        return _photo;
    }

    public void set_photo(String _photo) {
        this._photo = _photo;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Product [_idProduct=" + _idProduct + ", _idType=" + _idType + ", _name=" + _name + ", _description=" + _description + ", _price="
                        + _price + ", _photo=" + _photo + "]";
    }
}
