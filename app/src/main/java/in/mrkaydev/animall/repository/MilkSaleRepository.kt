package `in`.mrkaydev.animall.repository

import `in`.mrkaydev.animall.database.MilkSaleDao
import `in`.mrkaydev.animall.database.MilkSaleEntity
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class MilkSaleRepository @Inject constructor(private val milkSaleDao: MilkSaleDao) {
    suspend fun insertMilkSale(milkSale: MilkSaleEntity) {
        milkSaleDao.insertMilkSale(milkSale)
    }

    fun getMilkSalesTotal():Flow<List<MilkSaleEntity>> {
        return milkSaleDao.getAllList()
    }

    fun getMilkSalesForPeriod(startDate: Date, endDate: Date): Flow<List<MilkSaleEntity>> {
        return milkSaleDao.getMilkSalesForPeriod(startDate, endDate)
    }

    fun getTotalQuantityForPeriod(startDate: Date, endDate: Date): Flow<Double> {
        return milkSaleDao.getTotalQuantityForPeriod(startDate, endDate)
    }

    fun getTotalRevenueForPeriod(startDate: Date, endDate: Date): Flow<Double> {
        return milkSaleDao.getTotalRevenueForPeriod(startDate, endDate)
    }

    fun getAveragePriceForPeriod(startDate: Date, endDate: Date): Flow<Double> {
        return milkSaleDao.getAveragePriceForPeriod(startDate, endDate)
    }
}
