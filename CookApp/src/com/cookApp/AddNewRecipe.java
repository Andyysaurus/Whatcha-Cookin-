package com.cookApp;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.view.View;

public class AddNewRecipe extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnewrecipes);
    }

    public void back(View view) {
       Intent intent = new Intent(this, MainActivity.class);
       startActivity(intent);
    } 

	public void next(View view) {
		Intent intent = new Intent(this, AddNewIngredients2.class);
		startActivity(intent);
    }          
}