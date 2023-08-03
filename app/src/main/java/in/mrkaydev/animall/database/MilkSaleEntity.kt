package `in`.mrkaydev.animall.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "milk_sales")
data class MilkSaleEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = 0,
    @ColumnInfo(name = "quantity") val quantity: Double,
    @ColumnInfo(name = "price_per_unit") val pricePerUnit: Double,
    @ColumnInfo(name = "total_amount") val totalAmount: Double,
    @ColumnInfo(name= "date") val date: Long
)
