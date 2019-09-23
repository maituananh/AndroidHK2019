package com.example.listview2b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PlayerAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<InfoPlayer> infoPlayers;

    public PlayerAdapter(Context context, int layout, List<InfoPlayer> infoPlayers) {
        this.context = context;
        this.layout = layout;
        this.infoPlayers = infoPlayers;
    }

    @Override
    public int getCount() {
        return infoPlayers.size();
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

        TextView name = view.findViewById(R.id.namePlayer);
        TextView info = view.findViewById(R.id.informationPlayer);
        ImageView imageView = view.findViewById(R.id.imagePlayer);

        InfoPlayer infoPlayer = infoPlayers.get(i);

        name.setText(infoPlayer.getName());
        info.setText(infoPlayer.getAge() + ", " + infoPlayer.getcLUB() + ", " + infoPlayer.getCountry());
        imageView.setImageResource(infoPlayer.getImage()); // image là int
        return view;
    }
}
