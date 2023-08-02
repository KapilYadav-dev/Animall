package `in`.mrkaydev.animall.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import `in`.mrkaydev.animall.database.AppDatabase
import `in`.mrkaydev.animall.database.MilkSaleDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "milk_sales.db"
        ).build()
    }

    @Provides
    fun provideMilkSaleDao(database: AppDatabase): MilkSaleDao {
        return database.milkSaleDao()
    }
}