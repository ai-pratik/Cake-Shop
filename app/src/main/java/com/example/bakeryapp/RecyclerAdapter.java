package com.example.bakeryapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> implements Filterable  {
    private Context context;
    private ArrayList<BakeryProductsModel> bakeryProductsModelArrayList = new ArrayList<>();
    private ArrayList<BakeryProductsModel> bakeryProductsModelFilterArrayList = new ArrayList<>();
    private OnProductClickListner onProductClickListner;
    public RecyclerAdapter(Context context, ArrayList<BakeryProductsModel> bakeryProductsModelArrayList, OnProductClickListner onProductClickListner) {
        this.context = context;
        this.bakeryProductsModelArrayList = bakeryProductsModelArrayList;
        bakeryProductsModelFilterArrayList = new ArrayList<>(bakeryProductsModelArrayList);
        this.onProductClickListner = onProductClickListner;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bakery_item,parent,false);
        return new RecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        if (bakeryProductsModelArrayList.size() != 0){
            final BakeryProductsModel model = bakeryProductsModelArrayList.get(position);
            if (model != null){
                holder.txt_ProductDescription.setText(model.getProductDescription());
                holder.txt_ProductName.setText(model.getProductName());
                holder.txt_ProductPrice.setText(model.getProductPrice());
                holder.img_productImage.setImageDrawable(ContextCompat.getDrawable(context,model.getProductImage()));
                holder.btn_buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onProductClickListner.onProductClick(model);
                    }
                });
            }
        }

    }

    @Override
    public int getItemCount() {
        return bakeryProductsModelArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return SearchFilter;
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_productImage;
        TextView txt_ProductName,txt_ProductDescription,txt_ProductPrice;
        private Button btn_buy;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_productImage = (itemView).findViewById(R.id.img_productImage);
            txt_ProductName = (itemView).findViewById(R.id.txt_ProductName);
            txt_ProductDescription = (itemView).findViewById(R.id.txt_ProductDescription);
            txt_ProductPrice = (itemView).findViewById(R.id.txt_ProductPrice);
            btn_buy = (itemView).findViewById(R.id.btn_buy);
        }
    }
    private Filter SearchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<BakeryProductsModel> filterlist = new ArrayList<>();
            if (constraint==null || constraint.length()==0)
            {
                filterlist.addAll(bakeryProductsModelFilterArrayList);
            }
            else
            {
                String filterpattern = constraint.toString().trim();
                for (BakeryProductsModel item : bakeryProductsModelFilterArrayList)
                {
                    if (item.getProductName().toLowerCase().contains(filterpattern))
                    {
                        filterlist.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filterlist;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            bakeryProductsModelArrayList.clear();
            bakeryProductsModelArrayList.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
