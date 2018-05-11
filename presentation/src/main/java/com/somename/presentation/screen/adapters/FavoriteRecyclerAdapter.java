package com.somename.presentation.screen.adapters;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.somename.presentation.R;
import com.somename.presentation.viewmodel.BookViewModel;

import java.util.List;

public class FavoriteRecyclerAdapter extends BaseAdapter<FavoriteRecyclerHolder, BookViewModel> {

    @Nullable
    OnClickListener mOnClickListener;

    private final View.OnClickListener mInternalListener = (view) -> {
        if (mOnClickListener != null) {
            int position = (int) view.getTag();
            mOnClickListener.onClick(position);
        }
    };

    public FavoriteRecyclerAdapter(@NonNull List<BookViewModel> items) {
        super(items);
    }

    @Override
    public FavoriteRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FavoriteRecyclerHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(FavoriteRecyclerHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.itemView.findViewById(R.id.item_btn_favorite).setTag(position);
        holder.itemView.findViewById(R.id.item_btn_favorite).setOnClickListener(mInternalListener);
        BookViewModel bookViewModel = getItem(position);
        holder.bind(bookViewModel);
    }

    public void setOnClickListener(@Nullable OnClickListener onItemClickListener) {
        mOnClickListener = onItemClickListener;
    }

    public interface OnClickListener {

        void onClick(@NonNull int position);

    }

}
