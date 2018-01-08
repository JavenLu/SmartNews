package javen.example.com.irecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;


public abstract class IViewHolder extends RecyclerView.ViewHolder {

    public IViewHolder(View itemView) {
        super(itemView);
    }

    public final int getILayoutPosition() {
        return getLayoutPosition() - 2;
    }

    public final int getIAdapterPosition() {
        return getAdapterPosition() - 2;
    }

    public final int getIOldPosition() {
        return getOldPosition() - 2;
    }

    public final long getIItemId() {
        return getItemId();
    }

    public final int getIItemViewType() {
        return getItemViewType();
    }
}
