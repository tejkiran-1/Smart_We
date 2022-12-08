package com.example.smartwe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartwe.Model.PostsDashboardModel;
import com.example.smartwe.R;

import java.util.ArrayList;

public class PostDashboardAdapter extends RecyclerView.Adapter<PostDashboardAdapter.viewHolder> {

    ArrayList<PostsDashboardModel> list;
    Context context;

    public PostDashboardAdapter(ArrayList<PostsDashboardModel> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_dashboard_rv_sample, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        PostsDashboardModel model = list.get(position);
        holder.profile_img.setImageResource(model.getProfile_img());
        holder.post_img.setImageResource(model.getPost_img());
        holder.userNameTXT.setText(model.getUserName_tv());
        holder.aboutTXT.setText(model.getAbout_tv());
        holder.likeTXT.setText(model.getLike_tv());
        holder.comment_tv.setText(model.getComment_tv());
        holder.shareTXT.setText(model.getShare_tv());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView profile_img, post_img, bookmark_img;
        TextView userNameTXT, aboutTXT, likeTXT, shareTXT, comment_tv;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profile_img = itemView.findViewById(R.id.search_profile_image_icon);
            post_img = itemView.findViewById(R.id.post_img);
            bookmark_img = itemView.findViewById(R.id.post_bookmark);
            userNameTXT = itemView.findViewById(R.id.userName_tv);
            aboutTXT = itemView.findViewById(R.id.aboutUser_tv);
            likeTXT = itemView.findViewById(R.id.like_tv);
            shareTXT = itemView.findViewById(R.id.share_tv);
            comment_tv = itemView.findViewById(R.id.comment_tv);
        }
    }
}
