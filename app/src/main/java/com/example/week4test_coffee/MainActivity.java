package com.example.week4test_coffee;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    ItemFragment itemFragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemFragment = new ItemFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frgListFragment, itemFragment)
                .addToBackStack("LIST_FRG").commit();

    }

    @Override
    public void onListFragmentInteraction(Coffee item) {
    }
}
