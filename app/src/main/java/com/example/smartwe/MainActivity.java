package com.example.smartwe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.smartwe.Fragment.AddFragment;
import com.example.smartwe.Fragment.HomeFragment;
import com.example.smartwe.Fragment.NotificationFragment;
import com.example.smartwe.Fragment.ProfileFragment;
import com.example.smartwe.Fragment.SearchFragment;
import com.example.smartwe.databinding.ActivityMainBinding;
import com.iammert.library.readablebottombar.ReadableBottomBar;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        MainActivity.this.setTitle("My Profile");


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        binding.toolbar.setVisibility(View.GONE);  //for toolbar

        transaction.replace(R.id.frameContainer, new HomeFragment());
        transaction.commit();


        binding.readableBottomBar.setOnItemSelectListener(new ReadableBottomBar.ItemSelectListener() {
            @Override
            public void onItemSelected(int i) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                switch (i) {
                    case 0:
                        binding.toolbar.setVisibility(View.GONE);
                        transaction.replace(R.id.frameContainer, new HomeFragment());
                        break;
                    case 1:
                        binding.toolbar.setVisibility(View.GONE);
                        transaction.replace(R.id.frameContainer, new NotificationFragment());
                        break;
                    case 2:
                        binding.toolbar.setVisibility(View.GONE);
                        transaction.replace(R.id.frameContainer, new AddFragment());
                        break;
                    case 3:
                        binding.toolbar.setVisibility(View.GONE);
                        transaction.replace(R.id.frameContainer, new SearchFragment());
                        break;
                    case 4:
                        binding.toolbar.setVisibility(View.VISIBLE);
                        transaction.replace(R.id.frameContainer, new ProfileFragment());
                        break;
                }
                transaction.commit();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Toast.makeText(this, "Item 1 Selected", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}