package com.example.recyclerview;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String[] dataName;
    private String[] dataDescription;
    private TypedArray dataPhoto;
    private ArrayList<Hero> heroes;
    private ListHeroAdapter adapter;
    private RecyclerView rvHeroes;
    private String title = "Mode List";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepare();
        rvHeroes = findViewById(R.id.rv_heroes);
        rvHeroes.setHasFixedSize(true);

        setActionBarTitle("Mode List");
        showListRecycler();
    }

    private void showListRecycler() {
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        ListHeroAdapter adapter = new ListHeroAdapter(this);
        addItem();
        adapter.setHeroes(heroes);
        rvHeroes.setAdapter(adapter);

        ItemClickSupport.addTo(rvHeroes).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedPresident(heroes.get(position));
            }
        });
    }

    private void showGridRecycler(){
        rvHeroes.setLayoutManager(new GridLayoutManager(this, 2));
        GridHeroAdapter adapter = new GridHeroAdapter(this);
        addItem();
        adapter.setHeroes(heroes);
        rvHeroes.setAdapter(adapter);

        ItemClickSupport.addTo(rvHeroes).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedPresident(heroes.get(position));
            }
        });
    }

    private void showCardViewRecycler(){
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        CardViewHeroAdapter adapter = new CardViewHeroAdapter(this);
        addItem();
        adapter.setHeroes(heroes);
        rvHeroes.setAdapter(adapter);

        ItemClickSupport.addTo(rvHeroes).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedPresident(heroes.get(position));
            }
        });
    }

    private void addItem(){
        heroes = new ArrayList<>();
        for(int i = 0; i < dataName.length; i++){
            Hero hero = new Hero();
            hero.setName(dataName[i]);
            hero.setDescription(dataDescription[i]);
            hero.setPhoto(dataPhoto.getResourceId(i,-1));
            heroes.add(hero);
        }
    }

    private void prepare(){
        dataName = getResources().getStringArray(R.array.data_name);
        dataDescription = getResources().getStringArray(R.array.data_description);
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_list:
                setActionBarTitle("Mode List");
                showListRecycler();
                break;
            case R.id.action_grid:
                setActionBarTitle("Mode Grid");
                showGridRecycler();
                break;
            case R.id.action_cardview:
                setActionBarTitle("Mode CardView");
                showCardViewRecycler();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    private void showSelectedPresident(Hero hero){
        Toast.makeText(this, hero.getName(), Toast.LENGTH_SHORT).show();
    }

}
