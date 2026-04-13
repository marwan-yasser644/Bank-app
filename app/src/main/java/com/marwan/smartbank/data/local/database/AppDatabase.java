package com.marwan.smartbank.data.local.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.annotation.NonNull;

import com.marwan.smartbank.domain.entities.User;
import com.marwan.smartbank.domain.entities.Transaction;
import com.marwan.smartbank.domain.entities.Account;
import com.marwan.smartbank.domain.entities.Beneficiary;
import com.marwan.smartbank.data.local.dao.UserDao;
import com.marwan.smartbank.data.local.dao.TransactionDao;
import com.marwan.smartbank.data.local.dao.AccountDao;
import com.marwan.smartbank.data.local.dao.BeneficiaryDao;

@Database(
    entities = {
        User.class,
        Transaction.class,
        Account.class,
        Beneficiary.class
    },
    version = 1,
    exportSchema = true
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract TransactionDao transactionDao();
    public abstract AccountDao accountDao();
    public abstract BeneficiaryDao beneficiaryDao();

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE users ADD COLUMN kyc_status TEXT");
        }
    };

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class,
                "smartbank_database")
                .fallbackToDestructiveMigration()
                .build();
        }
        return instance;
    }
}
