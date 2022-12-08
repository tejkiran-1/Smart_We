package com.example.smartwe.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.smartwe.Adapter.FollowerssAdapter;
import com.example.smartwe.Model.FollowModel;
import com.example.smartwe.Model.UserDataModel;
import com.example.smartwe.R;
import com.example.smartwe.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    RecyclerView friendsRecyclerView;
    ArrayList<FollowModel> list;
    FragmentProfileBinding profileBinding;
    //ImageView profileCoverImage;

    FirebaseAuth auth;
    FirebaseStorage storage;
    FirebaseDatabase db;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        db = FirebaseDatabase.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        friendsRecyclerView = view.findViewById(R.id.profile_myFriends_RV);

        //profileCoverImage = (ImageView) view.findViewById(R.id.profile_cover_img);
        profileBinding = FragmentProfileBinding.inflate(inflater, container, false);
/*
        TextView profileName = (TextView) view.findViewById(R.id.profile_user_name_tv);
        TextView profileProfession = (TextView) view.findViewById(R.id.profile_user_profession);
*/
        //for updating profile cover image other details
        db.getReference().child("User").child(auth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    //
                    UserDataModel userDataModel = snapshot.getValue(UserDataModel.class);
                    //profile cover image update
                    Picasso.get()
                            .load(userDataModel.getCoverPhoto())
                            .fit()
                            .centerCrop()
                            .placeholder(R.drawable.image_placeholder)
                            .into(profileBinding.profileCoverImg);

                    //profile pic
                    Picasso.get()
                            .load(userDataModel.getProfileImage())
                            .fit()
                            .centerCrop()
                            .placeholder(R.drawable.image_placeholder)
                            .into(profileBinding.profileProfileImageIcon);

                    //profile user name
                    //old way
                    /*
                    profileName.setText(userDataModel.getUserName());
                    profileProfession.setText(userDataModel.getProfession());

                     */
                    //new way
                    profileBinding.profileUserNameTv.setText(userDataModel.getUserName());
                    profileBinding.profileUserProfession.setText(userDataModel.getProfession());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        list = new ArrayList<>();

        FollowerssAdapter adapter = new FollowerssAdapter(list, getContext());
        adapter.setHasStableIds(true); //for smooth scrolling //for better performance
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        profileBinding.profileMyFriendsRV.setLayoutManager(linearLayoutManager);
        profileBinding.profileMyFriendsRV.setAdapter(adapter);

        db.getReference().child(("User"))
                .child(auth.getUid())
                .child("followers").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            FollowModel followModel = dataSnapshot.getValue(FollowModel.class);
                            list.add(followModel);
                        }
                        adapter.notifyDataSetChanged();
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        //for changing cover photo
        //ImageView changeCoverPicIcon = (ImageView) view.findViewById(R.id.changeCoverImage);

        profileBinding.changeCoverImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to get into the gallery
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 11);
            }
        });

        //changing profile image
        profileBinding.profileBadgeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to get into the gallery
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 22);
            }
        });

        return profileBinding.getRoot();
        //return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11) {
            if (data.getData() != null) {
                Uri uri = data.getData(); //everythin comes as uri form from the gallery
                profileBinding.profileCoverImg.setImageURI(uri);

                final StorageReference storageReference = storage.getReference().child("cover_photo").child(auth.getUid());
                storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getContext(), "Cover Photo Uploaded", Toast.LENGTH_SHORT).show();

                        //for saving the downloadable link of image into the database
                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                db.getReference().child("User").child(auth.getUid()).child("coverPhoto").setValue(uri.toString());
                            }
                        });
                    }
                });
            }
        }
        else {
            if (data.getData() != null) {
                Uri uri = data.getData(); //everythin comes as uri form from the gallery
                profileBinding.profileProfileImageIcon.setImageURI(uri);

                final StorageReference storageReference = storage.getReference().child("profile_image").child(auth.getUid());
                storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getContext(), "Profile Photo Uploaded", Toast.LENGTH_SHORT).show();

                        //for saving the downloadable link of image into the database
                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                db.getReference().child("User").child(auth.getUid()).child("profileImage").setValue(uri.toString());
                            }
                        });
                    }
                });
            }
        }
    }
}