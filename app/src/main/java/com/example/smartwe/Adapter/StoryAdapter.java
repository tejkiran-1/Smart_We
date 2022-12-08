package com.example.smartwe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartwe.Model.StoryModel;
import com.example.smartwe.R;

import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.viewHolder> {

    ArrayList<StoryModel> list;
    Context context;

    public StoryAdapter(ArrayList<StoryModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.story_rv_design, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        StoryModel storyModel = list.get(position);
        holder.storyImg.setImageResource(storyModel.getStory());
        holder.profile.setImageResource(storyModel.getProfile());
        holder.storyType.setImageResource(storyModel.getStoryType());
        holder.name.setText(storyModel.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView storyImg, profile, storyType;
        TextView name;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            storyImg = itemView.findViewById(R.id.post_img);
            profile = itemView.findViewById(R.id.search_profile_image_icon);
            storyType = itemView.findViewById(R.id.story_type);
            name = itemView.findViewById(R.id.name_tv);
        }
    }
}
