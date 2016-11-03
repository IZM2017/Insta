package com.hsr.hemantsingh.insta.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.hsr.hemantsingh.insta.Models.User;
import com.hsr.hemantsingh.insta.R;
import com.hsr.hemantsingh.insta.Networking.VolleySingleton;
import com.hsr.hemantsingh.insta.listeners.CustomItemClickListener;

import java.util.List;

/**
 * Created by HemantSingh on 21/10/16.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<User> mDataset;
    CustomItemClickListener listener;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements  CustomItemClickListener {
        // each data item is just a string in this case
        public View mItemView;
        public  TextView nameTV, followerTV;

        public NetworkImageView proPicIB;

        public ViewHolder(View v) {
            super(v);
            mItemView = v;
            nameTV = (TextView) v.findViewById(R.id.textViewName);
            proPicIB = (NetworkImageView) v.findViewById(R.id.imageButton2);
            followerTV = (TextView) v.findViewById(R.id.textViewFollowers);
        }

        @Override
        public void onItemClick(View v, int position) {

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<User> myDataset, CustomItemClickListener listener) {
        mDataset = myDataset;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemview, parent, false);
        // set the view's size, margins, paddings and layout parameters

        final ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, vh.getAdapterPosition());
            }
        });
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.nameTV.setText(mDataset.get(position).getItems().first().getUser().getFull_name());
        Log.wtf( "onBindViewHolder: ", mDataset.get(position).getItems().first().getUser().getProfilePicture().replace("s150x150","s640x640"));
         holder.proPicIB.setImageUrl(mDataset.get(position).getItems().first().getUser().getProfilePicture().replace("s150x150","s640x640"), VolleySingleton.getInstance().getImageLoader());
        holder.followerTV.setText(mDataset.get(position).getItems().first().getUser().getUsername());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}


