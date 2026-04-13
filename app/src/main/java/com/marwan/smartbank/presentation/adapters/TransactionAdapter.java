package com.marwan.smartbank.presentation.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marwan.smartbank.databinding.ItemTransactionBinding;
import com.marwan.smartbank.domain.entities.Transaction;
import com.marwan.smartbank.utils.helpers.FormatHelper;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private List<Transaction> transactions = new ArrayList<>();
    private Context context;
    private OnTransactionClickListener listener;

    public interface OnTransactionClickListener {
        void onTransactionClick(Transaction transaction);
    }

    public TransactionAdapter(Context context, OnTransactionClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTransactionBinding binding = ItemTransactionBinding.inflate(
                LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);
        holder.bind(transaction);
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemTransactionBinding binding;

        ViewHolder(ItemTransactionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Transaction transaction) {
            binding.transactionTitle.setText(transaction.getRecipientName());
            binding.transactionSubtitle.setText(transaction.getType());
            binding.transactionAmount.setText(
                    FormatHelper.formatCurrency(transaction.getAmount()));

            double amount = transaction.getAmount();
            binding.transactionAmount.setTextColor(amount < 0 ? Color.RED : Color.GREEN);

            binding.getRoot().setOnClickListener(v -> 
                    listener.onTransactionClick(transaction));
        }
    }
}
