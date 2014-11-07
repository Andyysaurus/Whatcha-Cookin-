package com.cookApp;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void check(View view) {
        Intent intent = new Intent(this, Check.class);
        startActivity(intent);    
    } 


    public void addI(View view) {
        Intent intent = new Intent(this, AddIngredients.class);
        startActivity(intent);    
    } 


    public void addNI(View view) {
        Intent intent = new Intent(this, AddNewIngredients.class);
        startActivity(intent);    
    } 


    public void addNR(View view) {
        Intent intent = new Intent(this, AddNewRecipe.class);
        startActivity(intent);    
    } 


    public void about(View view) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);    
    } 
}
