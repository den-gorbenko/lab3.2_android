package com.example.lab3_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list)
    RecyclerView myList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        List<Fruit> Fruits = new ArrayList<>();

        // putting data to list
        Fruits.add(new Fruit("Apple",
                R.drawable.apple,
                "An apple is a sweet, edible fruit produced by an apple tree (Malus domestica). Apple trees are cultivated worldwide and are the most widely grown species in the genus Malus. The tree originated in Central Asia, where its wild ancestor, Malus sieversii, is still found today. Apples have been grown for thousands of years in Asia and Europe and were brought to North America by European colonists.",
                "China/USA/Poland"));
        Fruits.add(new Fruit(
                "Banana",
                R.drawable.banana,
                "A banana is an edible fruit – botanically a berry – produced by several kinds of large herbaceous flowering plants in the genus Musa. In some countries, bananas used for cooking may be called \"plantains\", distinguishing them from dessert bananas. The fruit is variable in size, color, and firmness, but is usually elongated and curved, with soft flesh rich in starch covered with a rind, which may be green, yellow, red, purple, or brown when ripe. The fruits grow in clusters hanging from the top of the plant.",
                "Africa"));
        Fruits.add(new Fruit(
                "Grape",
                R.drawable.grape,
                "A grape is a fruit, botanically a berry, of the deciduous woody vines of the flowering plant genus Vitis.  Grapes can be eaten fresh as table grapes or they can be used for making wine, jam, grape juice, jelly, grape seed extract, raisins, vinegar, and grape seed oil. Grapes are a non-climacteric type of fruit, generally occurring in clusters.",
                "Algeria"));
        Fruits.add(new Fruit(
                "Mandarin",
                R.drawable.mandarin,
                "The mandarin orange (Citrus reticulata), also known as the mandarin or mandarine, is a small citrus tree with fruit resembling other oranges, usually eaten plain or in fruit salads. The tangerine is a group of orange-coloured citrus fruit consisting of hybrids of mandarin orange.  Mandarins are smaller and oblate, rather than spherical, like the common oranges (which are a mandarin hybrid). The taste is considered less sour, as well as sweeter and stronger.",
                "China"));

        //layout to have vertical manager
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        myList.setLayoutManager(manager);

        // adding an adapter to list
        myList.setAdapter(new MyAdapter(Fruits));

    }

    // class for list inputting
    class Fruit {

        private String name;
        private int avatarId;
        private String description;
        private String origin;

        public Fruit(String name, int avatarId, String description, String origin) {
            this.name = name;
            this.avatarId = avatarId;
            this.description = description;
            this.origin = origin;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAvatarId() {
            return avatarId;
        }

        public void setAvatarId(int avatarId) {
            this.avatarId = avatarId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }
    }

    // creating an adapter
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.FruitViewHolder>  {

        private List<Fruit> list;

        public MyAdapter(List<Fruit> list) {
            this.list = list;
        }

        //creating class ViewHolder

        public class FruitViewHolder extends RecyclerView.ViewHolder {

            public TextView Name;
            public ImageView image;

            // put data to an item
            public FruitViewHolder(@NonNull View itemView) {
                super(itemView);

                Name = itemView.findViewById(R.id.Name);
                image = itemView.findViewById(R.id.imageView);

            }
        }
        @NonNull
        @Override
        public FruitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);

            FruitViewHolder holder = new FruitViewHolder(v);

            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull FruitViewHolder holder, int position) {
            holder.Name.setText(list.get(position).getName());
            holder.image.setImageResource(list.get(position).getAvatarId());
            holder.itemView.setOnClickListener(view -> {

                // adding data that  has to go to another activity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("Name",list.get(position).getName());
                intent.putExtra("origin", list.get(position).getOrigin());
                intent.putExtra("description", list.get(position).getDescription());
                intent.putExtra("image", list.get(position).getAvatarId());

                startActivity(intent);
            });
        }


        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}

