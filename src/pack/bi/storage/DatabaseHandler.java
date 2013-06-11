package pack.bi.storage;

import pack.bi.model.Category;
import pack.bi.model.Product;
import pack.bi.model.Sales;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "StoreDB";

    // Products
    private static final String TABLE_PRODUCTS = "products";
    private static final String KEY_Product_ID = "productid";
    private static final String KEY_Product_TypeID = "producttypeid";
    private static final String KEY_Product_Name = "productname";
    private static final String KEY_Product_Description = "productdescription";
    private static final String KEY_Product_Price = "productprice";
    private static final String KEY_Product_Photo = "productphoto";

    // Categories
    private static final String TABLE_CATEGORIES = "categories";
    private static final String KEY_Category_ID = "categoryid";
    private static final String KEY_Category_Name = "categoryname";

    // Sales
    private static final String TABLE_SALES = "sales";
    private static final String KEY_Sales_ID = "salesid";
    private static final String KEY_Sales_P_ID = "salespid";
    private static final String KEY_Sales_Ammount = "salesammount";
    private static final String KEY_Sales_Date = "salesdate";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("Waiting:", "Creating database..");

        String CREATE_PRODUCTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCTS + " (" + KEY_Product_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + KEY_Product_TypeID + " INTEGER," + KEY_Product_Name + " TEXT," + KEY_Product_Description + " TEXT," + KEY_Product_Price
                        + " INTEGER," + KEY_Product_Photo + " TEXT );";
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CATEGORIES + " (" + KEY_Category_ID
                        + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_Category_Name + " TEXT );";
        String CREATE_SALES_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_SALES + " (" + KEY_Sales_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + KEY_Sales_P_ID + " INTEGER," + KEY_Sales_Ammount + " INTEGER," + KEY_Sales_Date + " TEXT );";
        db.execSQL(CREATE_PRODUCTS_TABLE);
        db.execSQL(CREATE_CATEGORIES_TABLE);
        db.execSQL(CREATE_SALES_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALES);
        // Create tables again
        onCreate(db);
    }

    // Adding new product
    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Product_TypeID, product.get_idType());
        values.put(KEY_Product_Name, product.get_name());
        values.put(KEY_Product_Description, product.get_description());
        values.put(KEY_Product_Price, product.get_price());
        values.put(KEY_Product_Photo, product.get_photo());

        // Inserting Row
        db.insert(TABLE_PRODUCTS, null, values);
    }

    // Adding new category
    public void addCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Category_Name, category.get_categoryName());

        // Inserting Row
        db.insert(TABLE_CATEGORIES, null, values);
    }

    // Adding new sales
    public void addSale(Sales sale) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Sales_ID, sale.get_idSale());
        values.put(KEY_Sales_P_ID, sale.get_idProduct());
        values.put(KEY_Sales_Ammount, sale.get_amount());
        values.put(KEY_Sales_Date, sale.get_date());

        // Inserting Row
        db.insert(TABLE_SALES, null, values);
    }

    // Getting single product by id
    public Product getProduct(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUCTS, new String[] { KEY_Product_ID, KEY_Product_TypeID, KEY_Product_Name, KEY_Product_Description,
                        KEY_Product_Price, KEY_Product_Photo }, KEY_Product_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Product product = new Product(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4),
                        cursor.getString(5));
        // return contact
        return product;
    }

    // Getting single product by id
    public List<Product> getProductsByCategoryId(int categoryId) {
        List<Product> productsList = new ArrayList<Product>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUCTS, new String[] { KEY_Product_ID, KEY_Product_TypeID, KEY_Product_Name, KEY_Product_Description,
                        KEY_Product_Price, KEY_Product_Photo }, KEY_Product_TypeID + "=?", new String[] { String.valueOf(categoryId) }, null, null,
                        null, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.set_idProduct(cursor.getInt(0));
                product.set_idType(cursor.getInt(1));
                product.set_name(cursor.getString(2));
                product.set_description(cursor.getString(3));
                product.set_price(cursor.getInt(4));
                product.set_photo(cursor.getString(5));

                // Adding product to list
                productsList.add(product);
            } while (cursor.moveToNext());
        }

        return productsList;
    }

    // Getting All Products
    public List<Product> getAllProducts() {

        List<Product> productsList = new ArrayList<Product>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.set_idProduct(cursor.getInt(0));
                product.set_idType(cursor.getInt(1));
                product.set_name(cursor.getString(2));
                product.set_description(cursor.getString(3));
                product.set_price(cursor.getInt(4));
                product.set_photo(cursor.getString(5));

                // Adding product to list
                productsList.add(product);
            } while (cursor.moveToNext());
        }

        // return product list
        return productsList;
    }

    // Getting All Categories
    public List<Category> getAllCategories() {

        List<Category> categoriesList = new ArrayList<Category>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORIES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.set_idCategory(cursor.getInt(0));
                category.set_categoryName(cursor.getString(1));

                // Adding category to list
                categoriesList.add(category);
            } while (cursor.moveToNext());
        }

        // return categories list
        return categoriesList;
    }

    // Getting All Sales
    public List<Sales> getAllSales() {

        List<Sales> salesList = new ArrayList<Sales>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SALES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Sales sale = new Sales();
                sale.set_idSale(cursor.getInt(0));
                sale.set_idProduct(cursor.getInt(1));
                sale.set_amount(cursor.getInt(2));
                sale.set_date(cursor.getString(3));

                // Adding category to list
                salesList.add(sale);
            } while (cursor.moveToNext());
        }

        // return sales list
        return salesList;
    }

    // Getting products Count
    public int getProductsCount() {
        return (int) DatabaseUtils.queryNumEntries(getReadableDatabase(), TABLE_PRODUCTS);

    }

    // Updating product
    public int updateProduct(Product product) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Product_TypeID, product.get_idType());
        values.put(KEY_Product_Name, product.get_name());
        values.put(KEY_Product_Description, product.get_description());
        values.put(KEY_Product_Price, product.get_price());
        values.put(KEY_Product_Photo, product.get_photo());

        // updating row
        return db.update(TABLE_PRODUCTS, values, KEY_Product_ID + " = ?", new String[] { String.valueOf(product.get_idProduct()) });
    }

    // Deleting single product
    public void deleteProduct(Product product) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, KEY_Product_ID + " = ?", new String[] { String.valueOf(product.get_idProduct()) });
        db.close();
    }

}
