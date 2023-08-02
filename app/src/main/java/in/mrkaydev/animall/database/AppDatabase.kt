package `in`.mrkaydev.animall.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import `in`.mrkaydev.animall.utils.Converters

@Database(entities = [MilkSaleEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun milkSaleDao(): MilkSaleDao
}