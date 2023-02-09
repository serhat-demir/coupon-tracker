package com.serhatd.coupontracker.di

import android.content.Context
import androidx.room.Room
import com.serhatd.coupontracker.data.repository.CouponTrackerRepository
import com.serhatd.coupontracker.data.room.CouponDao
import com.serhatd.coupontracker.data.room.CurrencyDao
import com.serhatd.coupontracker.data.room.DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideCouponTrackerRepository(couponDao: CouponDao, currencyDao: CurrencyDao): CouponTrackerRepository {
        return CouponTrackerRepository(couponDao, currencyDao)
    }

    @Provides
    @Singleton
    fun provideCouponDao(@ApplicationContext context: Context): CouponDao {
        val db = Room.databaseBuilder(context, DB::class.java, "coupontracker.sqlite")
            .createFromAsset("coupontracker.sqlite").build()

        return db.getCouponDao()
    }

    @Provides
    @Singleton
    fun provideCurrencyDao(@ApplicationContext context: Context): CurrencyDao {
        val db = Room.databaseBuilder(context, DB::class.java, "coupontracker.sqlite")
            .createFromAsset("coupontracker.sqlite").build()

        return db.getCurrencyDao()
    }
}