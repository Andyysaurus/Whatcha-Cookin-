package com.cookApp;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.view.View;

public class AddNewIngredients2 extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnewingredients2);
    }

    public void back(View view) {
       Intent intent = new Intent(this, AddNewRecipe.class);
       startActivity(intent);
    }

    public void done(View view) {
        return;
    }   

    public void another(View view) {
        return;
    }             
}
