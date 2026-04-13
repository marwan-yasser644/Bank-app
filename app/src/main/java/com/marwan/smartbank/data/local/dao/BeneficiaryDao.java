package com.marwan.smartbank.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.OnConflictStrategy;

import com.marwan.smartbank.domain.entities.Beneficiary;

import java.util.List;

@Dao
public interface BeneficiaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBeneficiary(Beneficiary beneficiary);

    @Query("SELECT * FROM beneficiaries WHERE id = :beneficiaryId")
    Beneficiary getBeneficiaryById(String beneficiaryId);

    @Query("SELECT * FROM beneficiaries WHERE user_id = :userId ORDER BY is_favorite DESC")
    List<Beneficiary> getBeneficiariesByUserId(String userId);

    @Query("SELECT * FROM beneficiaries WHERE user_id = :userId AND is_favorite = 1")
    List<Beneficiary> getFavoriteBeneficiaries(String userId);

    @Update
    void updateBeneficiary(Beneficiary beneficiary);

    @Delete
    void deleteBeneficiary(Beneficiary beneficiary);

    @Query("DELETE FROM beneficiaries")
    void clearAll();
}
