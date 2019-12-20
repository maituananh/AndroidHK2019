package com.example.myappandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.Book;

import java.util.List;


public class ListBookAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Book> bookList;

    public ListBookAdapter(Context context, int layout, List<Book> bookList) {
        this.context = context;
        this.layout = layout;
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(layout, null);

        TextView textNameBook = view.findViewById(R.id.nameBook);
        TextView textNameAuthor = view.findViewById(R.id.nameAuthor);
        TextView textPrice = view.findViewById(R.id.price);
        ImageView imageView = view.findViewById(R.id.image);

        Book book = bookList.get(i);
        textNameBook.setText("Name: " + book.getName());
        textNameAuthor.setText("Author: " + book.getAuthor());
        textPrice.setText("Price: " + book.getPrice() + " VND");

        // chuyển byte từ data sang bitmap và gắn vào imageView
        byte[] image = book.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        imageView.setImageBitmap(bitmap);
        return view;
    }
}
