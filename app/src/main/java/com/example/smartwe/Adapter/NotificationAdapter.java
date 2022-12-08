package com.example.smartwe.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartwe.Model.NotificationModel;
import com.example.smartwe.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.viewHolder> {

    ArrayList<NotificationModel> list;
    Context context;

    public NotificationAdapter(ArrayList<NotificationModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.notification_swipe_notification_rv, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        NotificationModel model = list.get(position);

        holder.profileImage.setImageResource(model.getNotification_ProfileImg());
        holder.notficationtxt.setText(Html.fromHtml(model.getNotificationTXT()));
        holder.timetxt.setText(model.getTimeTXT());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView profileImage;
        TextView notficationtxt, timetxt;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.search_profile_image_icon);
            notficationtxt = itemView.findViewById(R.id.notification_tv);
            timetxt = itemView.findViewById(R.id.time_tv);
        }
    }
}
