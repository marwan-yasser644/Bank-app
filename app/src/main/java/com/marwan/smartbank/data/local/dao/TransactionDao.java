package com.marwan.smartbank.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.OnConflictStrategy;

import com.marwan.smartbank.domain.entities.Transaction;

import java.util.List;

@Dao
public interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTransaction(Transaction transaction);

    @Query("SELECT * FROM transactions WHERE user_id = :userId ORDER BY timestamp DESC LIMIT :limit")
    List<Transaction> getTransactions(String userId, int limit);

    @Query("SELECT * FROM transactions WHERE id = :transactionId")
    Transaction getTransactionById(String transactionId);

    @Query("SELECT * FROM transactions WHERE user_id = :userId AND type = :type AND timestamp BETWEEN :startDate AND :endDate ORDER BY timestamp DESC")
    List<Transaction> getFilteredTransactions(String userId, String type, long startDate, long endDate);

    @Update
    void updateTransaction(Transaction transaction);

    @Delete
    void deleteTransaction(Transaction transaction);

    @Query("DELETE FROM transactions")
    void clearAll();
}
