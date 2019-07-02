package com.example.week4test_coffee;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;
    List<Coffee> coffeeList = null;

    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance() {
        ItemFragment fragment = new ItemFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        DatabaseHelper db = new DatabaseHelper(getContext());

        if(db.queryForAllCoffeeItems() == null) {
            Log.d("TAG", "onCreateView: ");
            RetrofitCoffee retrofitCoffee = new RetrofitCoffee();
            retrofitCoffee.getService().getCoffeeListResponse("https://demo6983184.mockable.io/coffees/")
                    .enqueue(new Callback<Coffee>() {
                        @Override
                        public void onResponse(Call<Coffee> call, Response<Coffee> response) {
                            Coffee coffee = response.body();
                            db.insertCoffee(coffee);
                            coffeeList.add(coffee);
                            Log.d("TAG", "onResponse: ");
                        }

                        @Override
                        public void onFailure(Call<Coffee> call, Throwable t) {

                        }
                    });
        }
        else {
            coffeeList = db.queryForAllCoffeeItems();
        }

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(coffeeList, mListener));
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Coffee item);
    }
}
