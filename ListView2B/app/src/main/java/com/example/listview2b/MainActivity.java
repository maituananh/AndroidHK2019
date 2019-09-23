package com.example.listview2b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<InfoPlayer> infoPlayerList;
    PlayerAdapter playerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showPlayer();
        playerAdapter = new PlayerAdapter(this, R.layout.infordetailplayer , infoPlayerList);
        listView.setAdapter(playerAdapter);
    }
    public void showPlayer() {
        listView = findViewById(R.id.listViewPlayer);
        infoPlayerList = new ArrayList<>();
        infoPlayerList.add(new InfoPlayer("toni kroos", 28, R.drawable.kroos, "Real Madrid", "ĐỨC"));
        infoPlayerList.add(new InfoPlayer("gotze", 28, R.drawable.gotze, "Borussia Dortmund", "ĐỨC"));
        infoPlayerList.add(new InfoPlayer("Reus", 28, R.drawable.reus, "Borussia Dortmund", "ĐỨC"));
        infoPlayerList.add(new InfoPlayer("Ronaldo", 28, R.drawable.ronaldo, "juve", "bồ đào nha"));
        infoPlayerList.add(new InfoPlayer("de gea", 28, R.drawable.degea, "MU", "tây ban nha"));
    }
}
