package com.example.smartwe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartwe.Model.FollowModel;
import com.example.smartwe.Model.UserDataModel;
import com.example.smartwe.R;
import com.example.smartwe.databinding.SearchUserSampleLayoutBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class SearchUserAdapter extends RecyclerView.Adapter<SearchUserAdapter.viewHolder> {

    Context context;
    ArrayList<UserDataModel> searchList;

    public SearchUserAdapter(Context context, ArrayList<UserDataModel> searchList) {
        this.context = context;
        this.searchList = searchList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_user_sample_layout, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        UserDataModel model = searchList.get(position);
        Picasso.get()
                .load(model.getProfileImage()) //load user profile imag from this user (model)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.image_placeholder) //if not loaded show the plceholder image
                .into(holder.binding.searchProfileImageIcon); //once loaded show the image in this imageview
        holder.binding.searchUserName.setText(model.getUserName());
        holder.binding.searchUserProfession.setText(model.getProfession());

        //for creating node in database

        holder.binding.searchFollowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create node in database
                //whichever is following whom, we will store it in the database
                FollowModel followModel = new FollowModel();
                followModel.setFollowedBy(FirebaseAuth.getInstance().getUid()); // setting the user who is following
                followModel.setFollowedAt(new Date().getTime());

                FirebaseDatabase.getInstance().getReference()
                        .child("User")
                        .child(model.getUserId())
                        .child("followers")
                        .child(FirebaseAuth.getInstance().getUid()) //setting the user who is being followed
                        .setValue(followModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                FirebaseDatabase.getInstance().getReference()
                                        .child("User")
                                        .child(model.getUserId())
                                        .child("followerCount")
                                        .setValue(model.getFollowerCount() + 1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(context, "You followed " + model.getUserName(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });
            }
        });

    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        SearchUserSampleLayoutBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            binding = SearchUserSampleLayoutBinding.bind(itemView);
        }
    }
}
