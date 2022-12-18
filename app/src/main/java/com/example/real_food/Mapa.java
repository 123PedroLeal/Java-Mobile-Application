package com.example.real_food;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;

import org.osmdroid.api.IMapController;
import org.osmdroid.api.IMapView;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;

public class Mapa extends AppCompatActivity
{
private MapView mapa;
private MapController mapController;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Codigo tomado para evitar el error de la conexion con SQLite
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        mapa = (MapView) findViewById(R.id.Mapa);
        mapController = (MapController) mapa.getController();
        mapa.setTileSource(TileSourceFactory.MAPNIK);
        mapa.setMultiTouchControls(true);

        mapa.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);
        mapController.setZoom(12);

        ScaleBarOverlay mScaleBarOverlay = new ScaleBarOverlay(mapa);
        mapa.getOverlays().add(mScaleBarOverlay);

        RotationGestureOverlay mRotationGestureOverlay = new RotationGestureOverlay(this, mapa);
        mRotationGestureOverlay.setEnabled(true);
        mapa.getOverlays().add(mRotationGestureOverlay);

        GeoPoint madrid = new GeoPoint(40.41675,-3.70379);
        MapController mMapController= (MapController) mapa.getController();
        mMapController.setCenter(madrid);
    }

}