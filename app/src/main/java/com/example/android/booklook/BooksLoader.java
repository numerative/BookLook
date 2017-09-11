package com.example.android.booklook;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * This class carries network task in the background.
 */

public class BooksLoader extends AsyncTaskLoader<List<Book>> {
    private static final String LOG_TAG = BooksLoader.class.getName();

    private String mUrl;

    public BooksLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    public List<Book> loadInBackground() {
        //Checking whether URL is null
        if (mUrl == null) {
            return null;
        }

        //Perform the network request, parse the response, and extract a list of Books.
        List<Book> books = QueryUtils.fetchBookData(mUrl);
        //return the list of books to the loader in the MainActivity
        return books;

    }

}
