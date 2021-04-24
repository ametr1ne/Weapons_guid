package com.example.weapons_guide;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.weapons_guide.setttings.SettingsActivity;
import com.example.weapons_guide.utils.CustomArrayAdapter;
import com.example.weapons_guide.utils.ListItemClass;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    private ListView list;
    private String[] array, availability_array;
    private CustomArrayAdapter adapter;
    private Toolbar toolbar;
    private int category_index;
    private int[] array_guns_image = new int[] {R.drawable.five_seven, R.drawable.usp};
    private int[] array_rifles_image = new int[] {R.drawable.ak47, R.drawable.m4a1, R.drawable.famas};
    private int[] array_weights_image = new int[] {R.drawable.negev};
    private int[] array_other_image = new int[] {R.drawable.knife};
    private List<ListItemClass> listItemMain;
    private ListItemClass listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        list = findViewById(R.id.listView);
        listItemMain = new ArrayList<>();
        fillArray(R.string.guns, getResources().getStringArray(R.array.guns_array), getResources().getStringArray(R.array.guns_array_availability),
                array_guns_image, 0);
        adapter = new CustomArrayAdapter(this, R.layout.list_view_item, listItemMain, getLayoutInflater());
        list.setAdapter(adapter);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Text_Content_Activity.class);
                intent.putExtra("category", category_index);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        toolbar.setTitle(R.string.guns);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            Intent i = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if ( id == R.id.nav_guns) {
            fillArray(R.string.guns, getResources().getStringArray(R.array.guns_array), getResources().getStringArray(R.array.guns_array_availability),
                    array_guns_image, 0);
        } else if (id == R.id.nav_rifles) {
            fillArray(R.string.rifles, getResources().getStringArray(R.array.rifles_array), getResources().getStringArray(R.array.rifles_array_availaibility),
                    array_rifles_image, 1);
        } else if (id == R.id.nav_weight) {
            fillArray(R.string.weights, getResources().getStringArray(R.array.weight_array), getResources().getStringArray(R.array.weight_array_availability),
                    array_weights_image, 2);
        } else if (id == R.id.nav_other) {
            fillArray(R.string.other, getResources().getStringArray(R.array.others_array), getResources().getStringArray(R.array.others_array_availability),
                    array_other_image, 3);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void fillArray (int title, String[] nameArray, String[] availabilityArray, int[] image, int index) {
        toolbar.setTitle(title);
        if (adapter != null) adapter.clear();
        for (int i = 0;i<nameArray.length;i++) {
            listItem = new ListItemClass();
            listItem.setName(nameArray[i]);
            listItem.setAvailability(availabilityArray[i]);
            listItem.setImageId(image[i]);
            listItemMain.add(listItem);
        }
        if (adapter != null) adapter.notifyDataSetChanged();
        category_index = index;
    }
}