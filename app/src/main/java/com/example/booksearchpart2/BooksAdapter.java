package com.example.booksearchpart2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.CustomViewHolder> {
    private List<Item> volumeInfo;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Item book);
    }

    public BooksAdapter(OnItemClickListener listener) {
        onItemClickListener = listener;
        volumeInfo = new ArrayList<>();

    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_list, parent, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.bind(volumeInfo.get(position), onItemClickListener);

        VolumeInfo volumeInfo = this.volumeInfo.get(position).getVolumeInfo();
        holder.bookTitle.setText(volumeInfo.getTitle());

        if (volumeInfo.getAverageRating() != null) {
            holder.rating.setText("Rating: "+volumeInfo.getAverageRating());
        } else {
            holder.rating.setText("Rating: N/A");
        }

        if (volumeInfo.getAuthors() != null && !volumeInfo.getAuthors().isEmpty()) {
            holder.bookAuthor.setText("Author: "+volumeInfo.getAuthors().get(0));
        }

        if (volumeInfo.getPublishedDate() != null) {
            holder.publishedDate.setText("Published date: "+volumeInfo.getPublishedDate());
        } else {
            holder.publishedDate.setText("Published date: N/A");
        }

        if (volumeInfo.getPageCount() != null) {
            holder.pages.setText("Page count: "+volumeInfo.getPageCount());
        } else {
            holder.pages.setText("Page count: N/A");
        }

        Glide
                .with(holder.imageView.getContext())
                .load(volumeInfo.getImageLinks().smallThumbnail)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        if (volumeInfo == null) return 0;
        return volumeInfo.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView bookTitle, bookAuthor, rating, publishedDate, pages;
        ImageView imageView;

        CustomViewHolder(View view) {
            super(view);
            bookTitle = view.findViewById(R.id.bookTitle);
            bookAuthor = view.findViewById(R.id.bookAuthor);
            rating = view.findViewById(R.id.publisher);
            publishedDate = view.findViewById(R.id.publishedDate);
            pages = view.findViewById(R.id.description);
            imageView = view.findViewById(R.id.image_view);
        }

        public void bind(final Item item, final OnItemClickListener listener) {
            itemView.setOnClickListener(view -> listener.onItemClick(item));
        }
    }

    public void setBookData(List<Item> bookList) {
        this.volumeInfo.clear();
        this.volumeInfo.addAll(bookList);
        notifyDataSetChanged();
    }

}
