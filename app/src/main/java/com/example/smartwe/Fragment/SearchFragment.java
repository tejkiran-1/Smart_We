package com.example.smartwe.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartwe.Adapter.SearchUserAdapter;
import com.example.smartwe.Model.UserDataModel;
import com.example.smartwe.R;
import com.example.smartwe.databinding.FragmentSearchBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    ArrayList<UserDataModel> list = new ArrayList<>();
    FirebaseAuth auth;
    FirebaseDatabase database;;
    FragmentSearchBinding binding;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentSearchBinding.inflate(inflater, container, false);

        SearchUserAdapter adapter = new SearchUserAdapter(getContext(), list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()); //default is vertical
        adapter.setHasStableIds(true); //added for better performance
        binding.searchUsersRV.setLayoutManager(layoutManager);
        binding.searchUsersRV.setAdapter(adapter);

        database.getReference().child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { //whatever data is there in user database all fetched and stored in the form of snapshot
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) { //for each user in the database
                    UserDataModel model = dataSnapshot.getValue(UserDataModel.class); //get the data of that user and store it in the model
                    model.setUserId(dataSnapshot.getKey()); //get the key of that user and store it in the model
                    list.add(model); //add the model to the list
                }
                adapter.notifyDataSetChanged(); //notify the adapter that the data has changed
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return binding.getRoot();
    }
}