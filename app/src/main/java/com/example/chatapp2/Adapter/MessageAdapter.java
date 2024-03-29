package com.example.chatapp2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chatapp2.MessageActivity;
import com.example.chatapp2.Model.Chat;
import com.example.chatapp2.Model.User;
import com.example.chatapp2.R;
import com.example.chatapp2.WorkActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    public static final int MSG_TYPE_LEFT=0;
    public static final int MSG_TYPE_RIGHT=1;
    private Context mContext;
    private List<Chat> mChat;
    private String imageuri;

    FirebaseUser fuser;

    public MessageAdapter(Context mContext, List<Chat> mChat,String imageuri){
        this.mChat= mChat;
        this.mContext= mContext;
        this.imageuri=imageuri;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == MSG_TYPE_RIGHT){
            View view= LayoutInflater.from(mContext).inflate(R.layout.chat_item_right,parent,false);
            return new MessageAdapter.ViewHolder(view);
        }
        else{
            View view= LayoutInflater.from(mContext).inflate(R.layout.chat_item_left,parent,false);
            return new MessageAdapter.ViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int postion) {

            Chat chat= mChat.get(postion);
            holder.show_message.setText(chat.getMessage());
            if(imageuri.equals("default")){
                holder.profile_image.setImageResource(R.mipmap.ic_launcher);
            }
            else{
                Glide.with(mContext).load(imageuri).into(holder.profile_image);

            }
            if(postion == mChat.size()-1){
                if(chat.isIsseen()){
                    holder.txt_seen.setText("Seen");
                }else{
                    holder.txt_seen.setText("Delivered");
                }
            }else{
                holder.txt_seen.setVisibility(View.GONE);
            }
    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView show_message;
        public ImageView profile_image;
        public TextView txt_seen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            show_message = itemView.findViewById(R.id.show_message);
            profile_image = itemView.findViewById(R.id.profile_image);
            txt_seen= itemView.findViewById(R.id.txt_seen);


        }
    }


    @Override
    public int getItemViewType(int position) {
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if(mChat.get(position).getSender().equals(fuser.getUid())){
            return  MSG_TYPE_RIGHT;
        }
        else{
            return  MSG_TYPE_LEFT;
        }
    }
}
