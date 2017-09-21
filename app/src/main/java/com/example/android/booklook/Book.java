package com.example.android.booklook;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * This will serve as the Custom Data type which returns Book details
 */

public class Book {

    private String mTitle;

    private String mSubTitle;

    private ArrayList<String> mAuthors;

    private String mAverageRating;

    private String mPageCount;

    private Bitmap mSmallThumbnail;

    public Book (String title, String subTitle, ArrayList<String> authors, String pageCount,
                 String averageRating, Bitmap smallThumbnail) {
        mTitle = title;
        mSubTitle = subTitle;
        mAuthors = authors;
        mAverageRating = averageRating;
        mPageCount = pageCount;
        mSmallThumbnail = smallThumbnail;
    }

    //Get Title of the Book
    public String getTitle() {
        return mTitle;
    }

    public String getSubTitle() {
        return mSubTitle;
    }

    public ArrayList<String> getAuthors() {
        return mAuthors;
    }

    public String getAverageRating() {
        return mAverageRating;
    }

    public String getPageCount() {
        return mPageCount;
    }

    public Bitmap getSmallThumbnail() {
        return mSmallThumbnail;
    }
}
