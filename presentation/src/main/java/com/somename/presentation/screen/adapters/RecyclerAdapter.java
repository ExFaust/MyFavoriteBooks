package com.somename.presentation.screen.adapters;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.somename.presentation.R;
import com.somename.presentation.viewmodel.BookViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends BaseAdapter<RecyclerHolder,BookViewModel> {

    @Nullable
    OnClickListener mOnClickListener;

    List<String> mIds = new ArrayList<>();

    private final View.OnClickListener mInternalListener = (view) -> {
        if (mOnClickListener != null) {
            int position = (int) view.getTag();
            mOnClickListener.onClick(position, (ImageButton) view);
        }
    };

    public RecyclerAdapter(@NonNull List<BookViewModel> items) {
        super(items);
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ImageButton imageButton = holder.itemView.findViewById(R.id.item_btn_favorite);
        imageButton.setTag(position);
        imageButton.setOnClickListener(mInternalListener);
        BookViewModel bookViewModel = getItem(position);
        holder.bind(bookViewModel);
        if (mIds.contains(bookViewModel.getId())){
            imageButton.setImageResource(R.drawable.ic_favorite_red_24dp);
        }else
            imageButton.setImageResource(R.drawable.ic_favorite_24dp);
    }

    public void setOnClickListener(@Nullable OnClickListener onItemClickListener) {
        mOnClickListener = onItemClickListener;
    }

    public void setIds(List<String> ids){
        this.mIds = ids;
    }

    public List<String> getIds(){
        return mIds;
    }

    public interface OnClickListener {

        void onClick(@NonNull int position, ImageButton imageButton);

    }

}
