package com.example.davidalex.postadeal.controller;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.davidalex.postadeal.R;
import com.example.davidalex.postadeal.model.datasource.CustomContentProvider;
import com.example.davidalex.postadeal.model.entities.Activity;

import java.util.List;

/**
 * Created by david on 03.01.2017.
 */

public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.MyViewHolder> {

    private Context mContext;
    private Cursor cursor;
    private int count;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView dealPlace, dealPrice, imageResourceID;
        public ImageView coverImage, overflow;
        public View view;

        public MyViewHolder(View view) {
            super(view);
            this.view = view;
            dealPlace = (TextView) view.findViewById(R.id.title);
            dealPrice = (TextView) view.findViewById(R.id.count);
            coverImage = (ImageView) view.findViewById(R.id.cover_image);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            imageResourceID = (TextView)view.findViewById(R.id.image_resource_id);
        }
    }

    public DealsAdapter(Context mContext, Cursor cursor) {
        this.mContext = mContext;
        this.cursor = cursor;
        if (cursor != null)
            count = cursor.getCount();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.deal_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        cursor.moveToPosition(position);
        holder.dealPlace.setText(cursor.getString(cursor.getColumnIndex(CustomContentProvider.ACTIVITY_PLACEMENT)));
        holder.dealPrice.setText(cursor.getString(cursor.getColumnIndex(CustomContentProvider.ACTIVITY_PRICE)));
        holder.imageResourceID.setText(cursor.getString(cursor.getColumnIndex(CustomContentProvider.ACTIVITY_IMAGE_ID)));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext, R.style.DialogStyle);
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                View dialogView = inflater.inflate(R.layout.cover_image_dialog, null);
                ImageView imageView = ((ImageView) dialogView.findViewById(R.id.cover_image_dialog));
                //imageView.setImageResource(R.drawable.castle_france);
                imageView.setImageResource(Integer.parseInt(((TextView) v.findViewById(R.id.image_resource_id)).getText().toString()));
                //dialogBuilder.
                //dialogBuilder.setTitle("gnkjgnjkdsgndjksg");
                dialogBuilder.setView(dialogView);
                AlertDialog dialog = dialogBuilder.create();
                //dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, 1000);
                dialog.getWindow().setLayout(1000, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.dials_dialog);
                dialog.getWindow().setGravity(Gravity.CENTER);
                dialog.show(); // show the dialog
            }
        });
        Glide.with(mContext).load(cursor.getInt(cursor.getColumnIndex(CustomContentProvider.ACTIVITY_IMAGE_ID))).into(holder.coverImage);

//        holder.overflow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPopupMenu(holder.overflow);
//            }
//        });
    }


     //TODO Showing popup menu when tapping on dots to delete or edit an activity information

//    private void showPopupMenu(View view) {
//        // inflate menu
//        PopupMenu popup = new PopupMenu(mContext, view);
//        MenuInflater inflater = popup.getMenuInflater();
//        //inflater.inflate(R.menu.deal_menu, popup.getMenu());
//        //popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
//        popup.show();
//    }
//    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
//
//        public MyMenuItemClickListener() {
//        }
//
//        @Override
//        public boolean onMenuItemClick(MenuItem menuItem) {
//            switch (menuItem.getItemId()) {
//                case R.id.action_add_favourite:
//                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
//                    return true;
//
//                default:
//            }
//            return false;
//        }
//    }

    @Override
    public int getItemCount() {
        return count;
    }
}

