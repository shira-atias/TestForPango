package com.shira.testforpango.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shira.testforpango.Constants.CRYPTO_LIST
import com.shira.testforpango.model.Crypto


@Database(entities = [Crypto::class], version = 1, exportSchema = false)
abstract class AppDatabase :RoomDatabase(){

    abstract fun cryptoDao():CryptoDao

    companion object{
        private var instant:AppDatabase? = null

        fun getDatabase(context: Context):AppDatabase?{
            if (instant == null)
                synchronized(AppDatabase::class.java){
                    instant = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java,CRYPTO_LIST).allowMainThreadQueries().build()
                }
            return instant
        }
    }

}