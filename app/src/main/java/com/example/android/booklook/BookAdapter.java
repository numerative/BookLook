package com.example.android.booklook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.android.booklook.R.id.authors;


/**
 * This is a custom Adapter that will fetch data and display it on the book_list.xml layout file.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    //Input parameter it should expect
    public BookAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_list_item, parent, false);
        }

        //Get the object at this position in the list
        Book currentBook = getItem(position);

        //Find the TextView in the book_list_item.xml layout with the ID title
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);

        //Get the Title and set it to a title textview
        titleTextView.setText(currentBook.getTitle());

        //Find the TextView in the book_list_item.xml layout with the ID sub_title
        TextView subTitleTextView = (TextView) listItemView.findViewById(R.id.sub_title);

        if (currentBook.getSubTitle() == null) {
            subTitleTextView.setVisibility(View.GONE);
        } else {
            //Get the Title and set it to a sub_title textview
            subTitleTextView.setText(currentBook.getSubTitle());
        }
        //Find the TextView in the book_list_item.xml layout with the ID authors
        TextView authorsTextView = (TextView) listItemView.findViewById(authors);

        //Get the Title and set it to a authors textview
        ArrayList<String> authorsArray = new ArrayList<>(currentBook.getAuthors());
        StringBuilder authors = new StringBuilder();
        if (authorsArray.size() == 1) {
            authors.append(authorsArray.get(0));
        }
            if (authorsArray.size() > 1) {
                authors.append(authorsArray.get(0));
                for (int i = 1; i < authorsArray.size(); i++) {
                    authors.append(", " + authorsArray.get(i));
                }
            }

            authorsTextView.setText(authors);

        if (currentBook.getPageCount() != null) {
            //Find the TextView in the book_list_item.xml layout with the ID page_count
            TextView pageCountTextView = (TextView) listItemView.findViewById(R.id.page_count);

            //Get the Title and set it to a page_count textview
            pageCountTextView.setText(currentBook.getPageCount() + " pages");
        }

        //Find the TextView in the book_list_item.xml layout with the ID page_count
        TextView averageRatingTextView = (TextView) listItemView.findViewById(R.id.average_rating);

        //Get the Title and set it to a page_count textview
        averageRatingTextView.setText(currentBook.getAverageRating());

        return listItemView;
    }
}
