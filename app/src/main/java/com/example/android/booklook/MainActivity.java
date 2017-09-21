package com.example.android.booklook;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Book>> {

    public static final String LOG_TAG = MainActivity.class.getName();

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int BOOK_LOADER_ID = 1;


    /**
     * URL for earthquake data from the USGS dataset
     */
    //  private static final String GOOGLE_BOOKS_REQUEST_URL =
    //         "https://www.googleapis.com/books/v1/volumes?q=harry+potter&maxResults=20";
    String GOOGLE_BOOKS_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?";

    private BookAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Fetching the bundle from SearchBarActivity
        Bundle searchBundle = getIntent().getExtras();
        String searchQuery = searchBundle.getString("searchQuery");
        /*String searchQuery = searchBundle.getString("searchQuery");
        searchQuery = searchQuery.replaceAll(" ","+");
        Log.v("replace", searchQuery);

        //Making a new string builder to produce a new URL
        StringBuilder searchUrlBuilder = new StringBuilder();
        searchUrlBuilder.append("https://www.googleapis.com/books/v1/volumes?" + searchQuery +
                "&maxResults=20" );
        Log.v("URL", String.valueOf(searchUrlBuilder));
        //GOOG...URL assumes value of searchUrlBuilder
        GOOGLE_BOOKS_REQUEST_URL = String.valueOf(searchUrlBuilder);*/

        //Building URL via a URI Builder
        Uri baseUri = Uri.parse(GOOGLE_BOOKS_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("q", searchBundle.getString("searchQuery"));
        uriBuilder.appendQueryParameter("maxResults", "20");

        GOOGLE_BOOKS_REQUEST_URL = uriBuilder.toString();
        Log.v("URI", GOOGLE_BOOKS_REQUEST_URL);


        // Find a reference to the {@link ListView} in the layout
        final ListView bookListView = (ListView) findViewById(R.id.list);


        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new BookAdapter(this, new ArrayList<Book>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        bookListView.setAdapter(mAdapter);

        //Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(BOOK_LOADER_ID, null, this);
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        //Create a new Loader for the given URL this is where GOOGLE_BOOKS_REQUEST_URL was
        return new BooksLoader(this, GOOGLE_BOOKS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        //Clear the adapter of previous data
        mAdapter.clear();
        ProgressBar loadingCircle = (ProgressBar) findViewById(R.id.loading_spinner);
        loadingCircle.setVisibility(View.GONE);

        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        // Loader reset, so we can clear out our existing data.

    }
}
