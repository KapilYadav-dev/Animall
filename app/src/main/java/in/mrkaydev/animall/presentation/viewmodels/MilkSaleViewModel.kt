package `in`.mrkaydev.animall.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.mrkaydev.animall.database.MilkSaleEntity
import `in`.mrkaydev.animall.repository.MilkSaleRepository
import `in`.mrkaydev.animall.utils.CommonUtils
import `in`.mrkaydev.animall.utils.CommonUtils.log
import `in`.mrkaydev.animall.utils.CommonUtils.toMilliSeconds
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MilkSaleViewModel @Inject constructor(private val repository: MilkSaleRepository) :
    ViewModel() {

    /*
     * For List
     */

    private val _milkSalesForPeriod = MutableStateFlow<List<MilkSaleEntity>>(emptyList())
    val milkSalesForPeriod: StateFlow<List<MilkSaleEntity>> get() = _milkSalesForPeriod

    private val _milkSalesTotalTillNow = MutableStateFlow<List<MilkSaleEntity>>(emptyList())
    val milkSalesTotalTillNow: StateFlow<List<MilkSaleEntity>> get() = _milkSalesTotalTillNow

    /*
     * For quantity
     */

    private val _totalQuantityTillNow = MutableStateFlow(0.0)
    val totalQuantityTillNow: StateFlow<Double> get() = _totalQuantityTillNow

    private val _totalQuantityPeriod = MutableStateFlow(0.0)
    val totalQuantityPeriod: StateFlow<Double> get() = _totalQuantityPeriod

    /*
     * For Revenue
     */

    private val _totalRevenueTillNow = MutableStateFlow(0.0)
    val totalRevenueTillNow: StateFlow<Double> get() = _totalRevenueTillNow

    private val _totalRevenuePeriod = MutableStateFlow(0.0)
    val totalRevenuePeriod: StateFlow<Double> get() = _totalRevenuePeriod

    /*
     * For Average price
     */
    private val _averagePricePeriodTillNow = MutableStateFlow(0.0)
    val averagePricePeriodTillNow: StateFlow<Double> get() = _averagePricePeriodTillNow

    private val _averagePricePeriod = MutableStateFlow(0.0)
    val averagePricePeriod: StateFlow<Double> get() = _averagePricePeriod

    init {
        getMilkSalesTillNow()
        getTotalRevenue()
        getTotalQuantity()
        getAverageMilkPriceTotal()
    }

    /*
     * Function to insert a new MilkSale into the database
     */
    fun insertMilkSale(milkSale: MilkSaleEntity) {
        viewModelScope.launch {
            repository.insertMilkSale(milkSale)
        }
    }

    /*
     * Function to fetch MilkSales for till now
     */
    private fun getMilkSalesTillNow() {
        viewModelScope.launch {
            repository.getMilkSalesTotal().collect {
                _milkSalesTotalTillNow.emit(it)
            }
        }
    }

    /*
     * Function to fetch total quantity till now
     */
    private fun getTotalQuantity() {
        viewModelScope.launch {
            repository.getTotalQuantity().collect {
                it?.let { it1 -> _totalQuantityTillNow.emit(it1) }
            }
        }
    }

    /*
     * Function to fetch total revenue till now
     */
    private fun getTotalRevenue() {
        viewModelScope.launch {
            repository.getTotalRevenue().collect {
                it?.let { it1 -> _totalRevenueTillNow.emit(it1) }
            }
        }
    }

    /*
     * Function to fetch MilkSales for a given period
     */
    private fun getMilkSalesForPeriod(startDate: Date, endDate: Date) {
        viewModelScope.launch {
            repository.getMilkSalesForPeriod(startDate.toMilliSeconds(), endDate.toMilliSeconds())
                .collect {
                    "getMilkSalesForPeriod value is $it".log()
                    it?.let { it1 -> _milkSalesForPeriod.emit(it1) }
                }
        }
    }

    /*
     * Function to fetch Milk quantity for a given period
     */
    private fun getMilkQuantityForPeriod(startDate: Date, endDate: Date) {
        viewModelScope.launch {
            repository.getTotalQuantityForPeriod(
                startDate.toMilliSeconds(),
                endDate.toMilliSeconds()
            )
                .collect {
                   // "getMilkQuantityForPeriod value is $it".log()
                    it?.let { it1 -> _totalQuantityPeriod.emit(it1) }
                }
        }
    }

    /*
     * Function to fetch Milk Revenue for a given period
     */
    private fun getMilkRevenueForPeriod(startDate: Date, endDate: Date) {
        viewModelScope.launch {
            repository.getTotalRevenueForPeriod(
                startDate.toMilliSeconds(),
                endDate.toMilliSeconds()
            )
                .collect {
                   // "getMilkRevenueForPeriod value is $it".log()
                    it?.let { it1 -> _totalRevenuePeriod.emit(it1) }
                }
        }
    }

    /*
     * Function to fetch Milk Revenue for a given period
     */
    private fun getAverageMilkPriceForPeriod(startDate: Date, endDate: Date) {
        viewModelScope.launch {
            repository.getAveragePriceForPeriod(
                startDate.toMilliSeconds(),
                endDate.toMilliSeconds()
            )
                .collect {
                   // "getAverageMilkPriceForPeriod value is $it".log()
                    it?.let { it1 -> _averagePricePeriod.emit(it1) }
                }
        }
    }
    /*
    * Function to fetch Milk Revenue for all
    */
    private fun getAverageMilkPriceTotal() {
        viewModelScope.launch {
            repository.getAveragePriceTotal()
                .collect {
                    it?.let { it1 -> _averagePricePeriodTillNow.emit(it1) }
                }
        }
    }

    fun getAllDataForDate(days: Int) {
        viewModelScope.launch {
            getMilkRevenueForPeriod( CommonUtils.subtractDaysFromDate(days),Date())
            getMilkQuantityForPeriod(CommonUtils.subtractDaysFromDate(days),Date())
            getAverageMilkPriceForPeriod( CommonUtils.subtractDaysFromDate(days),Date())
            getMilkSalesForPeriod( CommonUtils.subtractDaysFromDate(days),Date())
        }
    }
}