package com.example.smartwe.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartwe.Adapter.PostDashboardAdapter;
import com.example.smartwe.Adapter.StoryAdapter;
import com.example.smartwe.Model.PostsDashboardModel;
import com.example.smartwe.Model.StoryModel;
import com.example.smartwe.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView storyRV, postDashboardRV;
    ArrayList<StoryModel> list;
    ArrayList<PostsDashboardModel> postsDashboardModels;

    public HomeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        storyRV = view.findViewById(R.id.story_RV);
        list = new ArrayList<>();
        list.add(new StoryModel(R.drawable.profile_pic, R.drawable.live_icon, R.drawable.profile_pic, "Tejkiran"));
        list.add(new StoryModel(R.drawable.profile_pic, R.drawable.live_icon, R.drawable.profile_pic, "Tejkiran"));
        list.add(new StoryModel(R.drawable.profile_pic, R.drawable.live_icon, R.drawable.profile_pic, "Tejkiran"));
        list.add(new StoryModel(R.drawable.profile_pic, R.drawable.live_icon, R.drawable.profile_pic, "Tejkiran"));
        list.add(new StoryModel(R.drawable.profile_pic, R.drawable.live_icon, R.drawable.profile_pic, "Tejkiran"));

        StoryAdapter adapter = new StoryAdapter(list, getContext());
        adapter.setHasStableIds(true); //added for better performance
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false); // false to stop in reverse
        storyRV.setLayoutManager(linearLayoutManager);
        storyRV.setNestedScrollingEnabled(false);
        storyRV.setAdapter(adapter);

        // for post dashboard model
        postDashboardRV = view.findViewById(R.id.post_dashboardRV);
        postsDashboardModels = new ArrayList<>();

        postsDashboardModels.add(new PostsDashboardModel(R.drawable.profile_pic, R.drawable.profile_pic, R.drawable.ic_post_bookmark, "Tejkiran Kushwaha", "Programmer", "10", "20", "2"));
        postsDashboardModels.add(new PostsDashboardModel(R.drawable.profile_pic, R.drawable.profile_pic, R.drawable.ic_post_bookmark, "Jgadeesh K", "Delloist", "100", "200", "20"));
        postsDashboardModels.add(new PostsDashboardModel(R.drawable.profile_pic, R.drawable.profile_pic, R.drawable.ic_post_bookmark, "Bad Aam", "ITCian", "101", "210", "21"));
        postsDashboardModels.add(new PostsDashboardModel(R.drawable.profile_pic, R.drawable.profile_pic, R.drawable.ic_post_bookmark, "Bhavya", "Restauranter", "1001", "2001", "201"));
        postsDashboardModels.add(new PostsDashboardModel(R.drawable.profile_pic, R.drawable.profile_pic, R.drawable.ic_post_bookmark, "Tejkiran Kushwaha", "Programmer", "10", "20", "2"));

        PostDashboardAdapter postDashboardAdapter = new PostDashboardAdapter(postsDashboardModels, getContext());
        LinearLayoutManager postLayoutManager = new LinearLayoutManager(getContext());
        postDashboardRV.setLayoutManager(postLayoutManager);
        postDashboardRV.setNestedScrollingEnabled(false); // change to true if you want to scroll
        postDashboardRV.setAdapter(postDashboardAdapter);

        return view;
    }
}