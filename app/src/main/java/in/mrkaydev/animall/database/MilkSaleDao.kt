package `in`.mrkaydev.animall.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface MilkSaleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMilkSale(milkSale: MilkSaleEntity)

    @Query("SELECT * FROM milk_sales")
    fun getAllList():Flow<List<MilkSaleEntity>>

    @Query("SELECT SUM(quantity) FROM milk_sales")
    fun  getTotalQuantity(): Flow<Double>

    @Query("SELECT SUM(total_amount) FROM milk_sales")
    fun  getTotalRevenue(): Flow<Double>

    @Query("SELECT SUM(price_per_unit) FROM milk_sales")
    fun  getTotalPrice(): Flow<Double>

    @Query("SELECT * FROM milk_sales WHERE date BETWEEN :startDate AND :endDate")
    fun  getMilkSalesForPeriod(startDate: Date, endDate: Date): Flow<List<MilkSaleEntity>>

    @Query("SELECT SUM(quantity) FROM milk_sales WHERE date BETWEEN :startDate AND :endDate")
    fun  getTotalQuantityForPeriod(startDate: Date, endDate: Date): Flow<Double>

    @Query("SELECT SUM(total_amount) FROM milk_sales WHERE date BETWEEN :startDate AND :endDate")
    fun  getTotalRevenueForPeriod(startDate: Date, endDate: Date): Flow<Double>

    @Query("SELECT AVG(price_per_unit) FROM milk_sales WHERE date BETWEEN :startDate AND :endDate")
    fun  getAveragePriceForPeriod(startDate: Date, endDate: Date): Flow<Double>
}