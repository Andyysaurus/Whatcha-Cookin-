package com.example.moletracker;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;

public class MolePhotos extends Activity
{
    
    //SQLiteHelper database;
    private ArrayList<String> photos_date;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.molephotos);
    }

    public void takePhoto(View view) {
        
    }

    public void back(View view) {
       Intent intent = new Intent(this, SkinAreas.class);
       startActivity(intent);
    }
}
