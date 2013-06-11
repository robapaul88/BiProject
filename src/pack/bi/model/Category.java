package pack.bi.model;

import java.io.Serializable;

public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    int _idCategory;
    String _categoryName;

    public Category() {
        super();
    }

    public Category(String _categoryName) {
        super();
        this._categoryName = _categoryName;
    }

    public Category(int _idCategory, String _categoryName) {
        super();
        this._idCategory = _idCategory;
        this._categoryName = _categoryName;
    }

    public int get_idCategory() {
        return _idCategory;
    }

    public void set_idCategory(int _idCategory) {
        this._idCategory = _idCategory;
    }

    public String get_categoryName() {
        return _categoryName;
    }

    public void set_categoryName(String _categoryName) {
        this._categoryName = _categoryName;
    }

    @Override
    public String toString() {
        return "Category [_idCategory=" + _idCategory + ", _categoryName=" + _categoryName + "]";
    }
}
