package com.example.shoppingchecklist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

    private List<Product> list;
    ProductDatabase productDatabase;

    ProductAdapter(List<Product> productList) {
        list = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        productDatabase = ProductManager.getInstance(parent.getContext());
        return new ProductViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_holder, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.name.setText(list.get(position).getProduct_name());
        holder.itemView.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), EditProductActivity.class);
            System.out.println("id = "+list.get(position).getProduct_id());
            i.putExtra("id", list.get(position).getProduct_id());
            i.putExtra("name", list.get(position).getProduct_name());
//            i.putExtra("position", position);
            view.getContext().startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addProduct(Product product) {
        list.add(product);
        this.notifyItemInserted(list.size() - 1);
    }

//    public void change(int position, String newName) {
//        list.get(position).setProduct_name(newName);
//    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.productName);
        }
    }

}