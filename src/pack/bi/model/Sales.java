package pack.bi.model;

import java.io.Serializable;

/**
 * @author roba
 *
 */
public class Sales implements Serializable {

    private static final long serialVersionUID = 1L;

    int _idSale;
    int _idProduct;
    int _amount;
    String _date;

    public Sales() {
        super();
    }

    public Sales(int _idProduct, int _amount, String _date) {
        super();
        this._idProduct = _idProduct;
        this._amount = _amount;
        this._date = _date;
    }

    public Sales(int _idSale, int _idProduct, int _amount, String _date) {
        super();
        this._idSale = _idSale;
        this._idProduct = _idProduct;
        this._amount = _amount;
        this._date = _date;
    }

    public int get_idSale() {
        return _idSale;
    }

    public void set_idSale(int _idSale) {
        this._idSale = _idSale;
    }

    public int get_idProduct() {
        return _idProduct;
    }

    public void set_idProduct(int _idProduct) {
        this._idProduct = _idProduct;
    }

    public int get_amount() {
        return _amount;
    }

    public void set_amount(int _amount) {
        this._amount = _amount;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    @Override
    public String toString() {
        return "Sales [_idSale=" + _idSale + ", _idProduct=" + _idProduct + ", _amount=" + _amount + ", _date=" + _date + "]";
    }
}
