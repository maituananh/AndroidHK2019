package com.example.myappandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.Booking;

import java.util.List;

public class ListBookingOfUserAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Booking> bookingList;

    public ListBookingOfUserAdapter(Context context, int layout, List<Booking> bookingList) {
        this.context = context;
        this.layout = layout;
        this.bookingList = bookingList;
    }

    @Override
    public int getCount() {
        return bookingList.size();
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

        TextView textNameBook = view.findViewById(R.id.nameBookOfUserBooking);
        TextView textPrice = view.findViewById(R.id.priceOfUserBooking);
        ImageView imageView = view.findViewById(R.id.imageOfUserBooking);

        Booking booking = bookingList.get(i);
        textNameBook.setText("Name: " + booking.getBook().getName());
        textPrice.setText("Price: " + booking.getBook().getPrice() + " VND");
        String split = booking.getBook().getImage().substring(0, booking.getBook().getImage().indexOf("."));
        imageView.setImageResource(getImageId(this.context, split));
        imageView.getLayoutParams().width = 250;
        imageView.requestLayout();
        return view;
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }


}
