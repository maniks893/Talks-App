package com.example.talksapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talksapp.Models.MessagesModel;
import com.example.talksapp.R;
import com.google.firebase.auth.FirebaseAuth;

import java.security.PublicKey;
import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{

    ArrayList<MessagesModel> messagesModels;
    Context context;

    int SENDER_VIEW_TYPE = 1;
    int RECEIVER_VIEW_TYPE = 2;

    public ChatAdapter(ArrayList<MessagesModel> messagesModels, Context context) {
        this.messagesModels = messagesModels;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        if (viewType == SENDER_VIEW_TYPE)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender , parent , false);
            return new SenderViewHolder(view);

        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_reciver , parent , false);
            return new ReceiverViewHolder(view);


        }
    }

    @Override
    public int getItemViewType(int position) {

        if (messagesModels.get(position).getuId().equals(FirebaseAuth.getInstance().getUid()))
        {
            return  SENDER_VIEW_TYPE;
        }
        else {
            return RECEIVER_VIEW_TYPE;
        }

    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
        MessagesModel messagesModel = messagesModels.get(position);

                if(holder.getClass() == SenderViewHolder.class){
                    ((SenderViewHolder)holder).SenderMsg.setText(messagesModel.getMessage());

                }
                else {
                    ((ReceiverViewHolder)holder).receiverMsg.setText(messagesModel.getMessage());
                }


    }

    @Override
    public int getItemCount() {
        return messagesModels.size();
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder {

        TextView receiverMsg , receiverTime;



        public ReceiverViewHolder(View itemView) {
            super(itemView);
            receiverMsg = itemView.findViewById(R.id.recieverText);
            receiverTime = itemView.findViewById(R.id.recieverTime);

        }
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder {

        TextView SenderMsg , SenderTime;

        public SenderViewHolder( View itemView) {
            super(itemView);

            SenderMsg = itemView.findViewById(R.id.senderText);
            SenderTime = itemView.findViewById(R.id.senderTime);

        }
    }

}
