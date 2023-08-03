package `in`.mrkaydev.animall.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.mrkaydev.animall.database.MilkSaleEntity
import `in`.mrkaydev.animall.repository.MilkSaleRepository
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
    private val _averagePricePeriod = MutableStateFlow(0.0)
    val averagePricePeriod: StateFlow<Double> get() = _averagePricePeriod

    init {
        getMilkSalesTillNow()
        getTotalRevenue()
        getTotalQuantity()
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
    fun getTotalQuantity() {
        viewModelScope.launch {
            repository.getTotalQuantity().collect {
                _totalQuantityTillNow.emit(it)
            }
        }
    }

    /*
     * Function to fetch total revenue till now
     */
    private fun getTotalRevenue() {
        viewModelScope.launch {
            repository.getTotalRevenue().collect {
                _totalRevenueTillNow.emit(it)
            }
        }
    }

    /*
     * Function to fetch MilkSales for a given period
     */
    fun getMilkSalesForPeriod(startDate: Date, endDate: Date) {
        viewModelScope.launch {
            repository.getMilkSalesForPeriod(startDate.toMilliSeconds(), endDate.toMilliSeconds())
                .collect {
                    _milkSalesForPeriod.emit(it)
                }
        }
    }

    /*
     * Function to fetch Milk quantity for a given period
     */
    fun getMilkQuantityForPeriod(startDate: Date, endDate: Date) {
        viewModelScope.launch {
            repository.getTotalQuantityForPeriod(
                startDate.toMilliSeconds(),
                endDate.toMilliSeconds()
            )
                .collect {
                    _totalQuantityPeriod.emit(it)
                }
        }
    }

    /*
     * Function to fetch Milk Revenue for a given period
     */
    fun getMilkRevenueForPeriod(startDate: Date, endDate: Date) {
        viewModelScope.launch {
            repository.getTotalRevenueForPeriod(
                startDate.toMilliSeconds(),
                endDate.toMilliSeconds()
            )
                .collect {
                    _totalRevenuePeriod.emit(it)
                }
        }
    }

    /*
     * Function to fetch Milk Revenue for a given period
     */
    fun getAverageMilkPriceForPeriod(startDate: Date, endDate: Date) {
        viewModelScope.launch {
            repository.getAveragePriceForPeriod(
                startDate.toMilliSeconds(),
                endDate.toMilliSeconds()
            )
                .collect {
                    _averagePricePeriod.emit(it)
                }
        }
    }
}