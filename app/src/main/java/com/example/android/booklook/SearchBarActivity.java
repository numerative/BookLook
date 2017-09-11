package com.example.android.booklook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);

        final Intent openResultActivity = new Intent(this, MainActivity.class);
        final EditText searchQuery = (EditText) findViewById(R.id.search_edit_text);
        Button searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String searchQueryString = searchQuery.getText().toString();
                Log.v("SearchqueryinSBA", searchQueryString);
                openResultActivity.putExtra("searchQuery", searchQueryString );

                startActivity(openResultActivity);

            }
        });

    }
}
