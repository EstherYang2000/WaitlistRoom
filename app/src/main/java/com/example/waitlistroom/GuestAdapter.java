package com.example.waitlistroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.waitlistroom.database.Guest;

import java.util.List;

public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.MyViewHolder> {
    private Context context;
    private List<Guest> guestList;
    final private ItemClickListener mItemClickListener;

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }
    public GuestAdapter(Context context,ItemClickListener listener){
        this.context=context;
        mItemClickListener = listener;

    }

    public void setGuestList(List<Guest>guestList){
        this.guestList=guestList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GuestAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestAdapter.MyViewHolder holder, int position) {

        holder.guestNameView.setText(this.guestList.get(position).GuestName);
        holder.partsizeView.setText(this.guestList.get(position).Partysize);

    }

    @Override
    public int getItemCount() {
        return this.guestList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView guestNameView;
        TextView partsizeView;
        public MyViewHolder(View view){
            super(view);
            guestNameView=view.findViewById(R.id.guestnameView);
            partsizeView=view.findViewById(R.id.partysizeTextView);
        }

        @Override
        public void onClick(View v) {
            int elementId = guestList.get(getAdapterPosition()).Gid;
            mItemClickListener.onItemClickListener(elementId);
        }
    }


}
