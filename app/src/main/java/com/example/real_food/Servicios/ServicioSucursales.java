package com.example.real_food.Servicios;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.example.real_food.Entidades.Producto;
import com.example.real_food.Entidades.Sucursal;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ServicioSucursales
{
    public ArrayList <Sucursal> cursorToArray(Cursor cursor)
    {
        ArrayList<Sucursal> list = new ArrayList<>();
        if(cursor.getCount() != 0)
        {
            while (cursor.moveToNext())
            {
                Sucursal sucursal = new Sucursal
                    (
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
                    );
                list.add(sucursal);
            }
        }
        return list;
    }
    // Intruccion para convertir el dato de tipo byte[] a tipo bytearray.
    public byte [] ImageViewtoByte(ImageView imageView)
    {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
