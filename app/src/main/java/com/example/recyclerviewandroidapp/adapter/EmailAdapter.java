package com.example.recyclerviewandroidapp.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewandroidapp.R;
import com.example.recyclerviewandroidapp.models.ItemEmail;

import java.util.ArrayList;

public class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.ViewHolder>{
    ArrayList<ItemEmail> itemEmails; //Chua tat ca cac item ban dau
    ArrayList<ItemEmail> displayItem;
    Context context;
    String keyword;
    boolean isShowingFavorite;

    public EmailAdapter(ArrayList<ItemEmail> itemEmails, Context context) {
        this.itemEmails = itemEmails;
        this.context = context;
        displayItem = new ArrayList<>();
        displayItem.addAll(itemEmails);
        keyword = "";
        isShowingFavorite = false;
    }

    public void search(String keyword) {
        this.keyword = keyword;
        displayItem.clear();
        for (ItemEmail item : itemEmails) {
            if (item.getName().contains(keyword) || item.getContent().contains(keyword)) {
                displayItem.add(item);
            }
            isShowingFavorite = false;
            notifyDataSetChanged();
        }
    }

    public void showFavorite() {
        this.keyword = "";
        displayItem.clear();
        for (ItemEmail item : itemEmails) {
            if (item.isSelected()) {
                displayItem.add(item);
            }
        }
        isShowingFavorite = true;
        notifyDataSetChanged();
    }

    public void showAll() {
        displayItem.clear();
        displayItem.addAll(itemEmails);
        isShowingFavorite = false;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.layout_item_email, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        ItemEmail item = displayItem.get(position);

        Drawable background = viewHolder.txtAvatar.getBackground();
        background.setColorFilter(new PorterDuffColorFilter(item.getColor(), PorterDuff.Mode.SRC_ATOP));

        if (keyword.length() > 2) {
            String name = item.getName().replace(keyword, "<b>" + keyword + "</b>");
            String content = item.getContent().replace(keyword, "<b>" + keyword + "</b>");
            viewHolder.txtName.setText(Html.fromHtml(name));
            viewHolder.txtContent.setText(Html.fromHtml(content));
        } else {
            viewHolder.txtName.setText(item.getName());
            viewHolder.txtContent.setText(item.getContent());
        }

        viewHolder.txtAvatar.setText(item.getName().substring(0,1));
        if (item.isSelected()) {
            viewHolder.imgFavorite.setImageResource(R.drawable.ic_star_bold);
        } else {
            viewHolder.imgFavorite.setImageResource(R.drawable.ic_star_normal);
        }
    }

    @Override
    public int getItemCount() {
        return displayItem.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtContent;
        TextView txtTime;
        TextView txtAvatar;
        ImageView imgFavorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtContent = (TextView) itemView.findViewById(R.id.txt_content);
            txtTime = (TextView) itemView.findViewById(R.id.txt_time);
            txtAvatar = (TextView) itemView.findViewById(R.id.txt_view_avatar);
            imgFavorite = (ImageView) itemView.findViewById(R.id.txt_image);

            imgFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isFavorite = displayItem.get(getAdapterPosition()).isSelected();
                    displayItem.get(getAdapterPosition()).setSelected(!isFavorite);

                    if (isShowingFavorite)
                        displayItem.remove(getAdapterPosition());

                    notifyDataSetChanged();
                }
            });
        }
    }
}
