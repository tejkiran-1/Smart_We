package com.example.smartwe.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartwe.Adapter.NotificationAdapter;
import com.example.smartwe.Model.NotificationModel;
import com.example.smartwe.R;

import java.util.ArrayList;

public class NotificationSwipeFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<NotificationModel> list;

    public NotificationSwipeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification_swipe, container, false);

        recyclerView = view.findViewById(R.id.notification_recyclerView);
        list = new ArrayList<>();

        list.add(new NotificationModel(R.drawable.pjhotographer_kid, "<b>Tejkiran</b> mentioned you in a comment.", "just now"));
        list.add(new NotificationModel(R.drawable.cap_baby, "<b>Jagadeesh</b> commented on your post.", "39 min"));
        list.add(new NotificationModel(R.drawable.profile_pic, "<b>Nitish</b> liked your post.", "59 min"));
        list.add(new NotificationModel(R.drawable.shocked_kid, "<b>Bhavya</b> shared a post.", "1 hr"));
        list.add(new NotificationModel(R.drawable.tuple_kids, "<b>Jagadeesh</b> commented on your post.", "39 minutes"));
        list.add(new NotificationModel(R.drawable.shocked_kid, "<b>Jagadeesh</b> commented on your post.", "39 minutes"));
        list.add(new NotificationModel(R.drawable.cap_baby, "<b>Jagadeesh</b> commented on your post.", "39 minutes"));
        list.add(new NotificationModel(R.drawable.cap_baby, "<b>Jagadeesh</b> commented on your post.", "39 minutes"));
        list.add(new NotificationModel(R.drawable.cap_baby, "<b>Jagadeesh</b> commented on your post.", "39 minutes"));
        list.add(new NotificationModel(R.drawable.cap_baby, "<b>Jagadeesh</b> commented on your post.", "39 minutes"));
        list.add(new NotificationModel(R.drawable.cap_baby, "<b>Jagadeesh</b> commented on your post.", "39 minutes"));
        list.add(new NotificationModel(R.drawable.cap_baby, "<b>Jagadeesh</b> commented on your post.", "39 minutes"));
        list.add(new NotificationModel(R.drawable.cap_baby, "<b>Jagadeesh</b> commented on your post.", "39 minutes"));

        NotificationAdapter adapter = new NotificationAdapter(list, getContext());
        adapter.setHasStableIds(true); // This is the line that fixes the issue / for better performance
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}