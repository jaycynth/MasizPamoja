package com.julie.masizpamoja.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.julie.masizpamoja.R;
import com.julie.masizpamoja.models.Chat;
import com.julie.masizpamoja.utils.SharedPreferencesManager;

import java.util.List;

public class PusherMessageAdapter extends RecyclerView.Adapter{
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private static final int VIEW_TYPE_CONNECTED = 3;

    private List<Chat> messsageList;
    private Context context;


    public PusherMessageAdapter(Context context, List<Chat> messsageList) {
        this.messsageList = messsageList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {

        Chat message = messsageList.get(position);
        if (message != null) {
            if (String.valueOf(message.getMesssage().getUser().getId()).equals(SharedPreferencesManager.getInstance(context).getUniqueid())) {
                return VIEW_TYPE_MESSAGE_SENT;

            } else {
                return VIEW_TYPE_MESSAGE_RECEIVED;

            }
        } else {
            return VIEW_TYPE_CONNECTED;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_message, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.their_message, parent, false);
            return new ReceivedMessageHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_connected, parent, false);
            return new ChatRoomViewHolder(view);
        }

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

Chat message = messsageList.get(position);
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_CONNECTED:
                ((ChatRoomViewHolder) holder).bind(message);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + holder.getItemViewType());
        }
    }

    @Override
    public int getItemCount() {
        return messsageList.size();
    }

    public void addMessage(Chat message){
        messsageList.add(message);
        notifyDataSetChanged();
    }


    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText;

        SentMessageHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.message_body);
            timeText = (TextView) itemView.findViewById(R.id.time_text);
        }

        void bind(Chat message) {
            messageText.setText(message.getMesssage().getText());

            // Format the stored timestamp into a readable String using method.
            timeText.setText(message.getMesssage().getTime());
        }
    }


    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, nameText,timeText;
        //ImageView profileImage;

        ReceivedMessageHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.message_body);
            timeText = (TextView) itemView.findViewById(R.id.time_text);
            nameText = (TextView) itemView.findViewById(R.id.name);
            //profileImage = (ImageView) itemView.findViewById(R.id.image_message_profile);
        }

        void bind(Chat message) {
            messageText.setText(message.getMesssage().getText());

            // Format the stored timestamp into a readable String using method.
            timeText.setText(message.getMesssage().getTime());

            nameText.setText(message.getMesssage().getUser().getName());

            // Insert the profile image from the URL into the ImageView.
            //Utils.displayRoundImageFromUrl(mContext, message.getSender().getProfileUrl(), profileImage);
        }
    }


    public class ChatRoomViewHolder extends RecyclerView.ViewHolder {


        TextView messageText;

        ChatRoomViewHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.message_body);
        }

        void bind(Chat message) {
            messageText.setText(message.getMesssage().getUser().getName());


        }


    }

}
