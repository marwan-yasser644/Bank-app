package com.marwan.smartbank.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.OnConflictStrategy;

import com.marwan.smartbank.domain.entities.Account;

import java.util.List;

@Dao
public interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAccount(Account account);

    @Query("SELECT * FROM accounts WHERE id = :accountId")
    Account getAccountById(String accountId);

    @Query("SELECT * FROM accounts WHERE user_id = :userId")
    List<Account> getAccountsByUserId(String userId);

    @Update
    void updateAccount(Account account);

    @Delete
    void deleteAccount(Account account);

    @Query("DELETE FROM accounts")
    void clearAll();
}
