package com.example.smartwe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartwe.Model.FollowModel;
import com.example.smartwe.R;
import com.example.smartwe.databinding.ProfileFriendRvSampleBinding;

import java.util.ArrayList;

public class FollowerssAdapter extends RecyclerView.Adapter<FollowerssAdapter.viewHolder> {

    ArrayList<FollowModel> list;
    Context context;

    public FollowerssAdapter(ArrayList<FollowModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.profile_friend_rv_sample, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        FollowModel model = list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ProfileFriendRvSampleBinding binding;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ProfileFriendRvSampleBinding.bind(itemView);
        }
    }
}
