package com.example.real_food.BaseDeDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.real_food.Entidades.Producto;

public class BaseDatos extends SQLiteOpenHelper
{
    private SQLiteDatabase sqLiteDatabase;

    public BaseDatos(Context context)
    {
        super(context,"RF.db",null,1);
        sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE PRODUCTOS("+
                    "id TEXT PRIMARY KEY,"+
                    "name VARCHAR,"+
                    "description TEXT,"+
                    "price VARCHAR,"+
                    "image TEXT"+")");

//        db.execSQL("CREATE TABLE SUCURSALES("+
//                "id TEXT PRIMARY KEY,"+
//                "name VARCHAR,"+
//                "description TEXT,"+
//                "price VARCHAR,"+
//                "image TEXT"+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS PRODUCTOS");
    }

    //Metodos CRUD.

    //POST o INSERT
    public void InsertarProducto(Producto producto)
    {
        String sql= "INSERT INTO PRODUCTOS VALUES(?,?,?,?,?)";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,producto.getId());
        statement.bindString(2,producto.getNombre());
        statement.bindString(3,producto.getDescription());
        statement.bindString(4,String.valueOf(producto.getPrecio()));
        statement.bindString(5,producto.getImagen());

        statement.executeInsert();
    }

    //GET Todos.
    public Cursor getProductos()
    {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PRODUCTOS", null);
        return cursor;
    }

    // GET uno solo por id.
    public Cursor getProductoporId(String id)
    {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PRODUCTOS WHERE id ="+id, null);
        return cursor;
    }

    // DELETE uno solo por id.
    public void BorrarporId(String id)
    {
        sqLiteDatabase.execSQL("DELETE FROM PRODUCTOS WHERE id ="+id);
    }

    // UPDATE
    public void ActualizarProducto(String Id, String Name, String Description, String Price, byte[] Image)
    {
        ContentValues contentValues = new ContentValues();

        contentValues.put("Name", Name);
        contentValues.put("Description", Description);
        contentValues.put("Price", Price);
        contentValues.put("Image", Image);

        sqLiteDatabase.update("PRODUCTOS",contentValues, "id= ?",new String[]{Id});
    }

}
