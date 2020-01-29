package com.noon.a7pets;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.noon.a7pets.models.GenericProductModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> implements Filterable {

    Context context;
    List<GenericProductModel> products;
    List<GenericProductModel> productsFull;

    public ProductsAdapter(Context context, List<GenericProductModel> products) {
        this.context = context;
        this.products = products;
        productsFull = new ArrayList<>(products);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.raw_items, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        //Biding the resources to views here
        final GenericProductModel product = products.get(position);
        Picasso.with(context).load(products.get(position).getCardimage()).into(holder.productImage);
        holder.productName.setText(products.get(position).getCardname());
        holder.productPrice.setText(String.valueOf(products.get(position).getCardprice()));


        holder.productCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, IndividualProduct.class);
                intent.putExtra("product", product);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public Filter getFilter() {
        return productsFilter;
    }

    private Filter productsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<GenericProductModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(productsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (GenericProductModel item : productsFull){
                    if (item.getCardname().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            products.clear();
            products.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView productName;
        TextView productPrice;
        CardView productCardView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            productCardView = itemView.findViewById(R.id.product_cardview);
        }
    }
}
