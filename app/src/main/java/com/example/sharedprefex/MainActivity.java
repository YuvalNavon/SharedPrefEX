/**
 * @author		Yuval Navon <yuvalnavon8@gmail.com>
 * @version	1.0
 * @since		31/12/2021
 * The program allows the user to increase a number from zero
 * (Which is shown to them via a TextView) and reset it, and also has
 * an EditText for the user to input what they want into, and the user can
 * exit the app via a Button but when they come back all of their data from their
 * previous usage is saved via a SharedPreferences object and the onStop method.
 */

package com.example.sharedprefex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

int count;
String stri;
TextView text;
EditText et;
SharedPreferences data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count = 0;
        text = findViewById(R.id.textView);
        et = findViewById(R.id.EditText);
        text.setText("0");


    }



    public void plusOne (View view) {
        /**
         * Adds 1 to the counter and updates its TextView.
         *
         *
         * @param   View view - the button that was pressed.
         * @return	None
         */
        count += 1;
        text.setText(String.valueOf(count));
    }

    public void reset (View view) {
        /**
         * Resets the counter to 0 and updates its TextView.
         *
         *
         * @param   View view - the button that was pressed.
         * @return	None
         */
        count = 0;
        text.setText("0");
    }

    public void exit (View view) {
        /**
         * Exits the app.
         *
         *
         * @param   View view - the button that was pressed.
         * @return	None
         */

        finish();
    }


    @Override
    protected void onStop() {
        /**
         * Saves the counter and text as they were when the app is closed
         * via a SharedPrefrences.
         *
         *
         * @param   None
         * @return	None
         */
        super.onStop();
        data = getSharedPreferences("COUNT_CONTAINER",MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        stri = et.getText().toString();
        editor.putInt("Count_Var", count);
        editor.putString("EditText_Var", stri);
        editor.commit();
    }

    @Override
    protected void onStart() {
        /**
         * Puts the counter and text that were saved into their TextView and EditText
         * via a SharedPrefrences.
         *
         *
         * @param   None
         * @return	None
         */
        super.onStart();
        data =getSharedPreferences("COUNT_CONTAINER",MODE_PRIVATE);
        count = data.getInt("Count_Var", 0);
        stri = data.getString("EditText_Var", "");
        text.setText(String.valueOf(count));
        et.setText(stri);

    }



    public boolean onCreateOptionsMenu (Menu menu)
    /**
     *  Creates a general OptionsMenu which allows the user to choose which
     *  allows the user to choose which screen they want to use, the main
     *  screen or the credits screen.
     *
     *
     * @param    Menu menu -required for the function to inflate the menu layout into
     *           the Menu object which it creates.
     * @return	true
     */
    {

        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    /**
     * Decides which option the user chose and sends them to the other screen
     * if they picked it.
     *
     *
     * @param    MenuItem item - the option which the user chose.
     * @return	true
     */
    {
        int id = item.getItemId();
        if (id == R.id.Credits)
        {
            Intent si = new Intent(this, Credits.class);
            startActivity(si);
        }
        return true;


    }

}