package `in`.mrkaydev.animall.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.mrkaydev.animall.database.MilkSaleEntity
import `in`.mrkaydev.animall.repository.MilkSaleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MilkSaleViewModel @Inject constructor(private val repository: MilkSaleRepository) :
    ViewModel() {
    private val _milkSalesForPeriod = MutableStateFlow<List<MilkSaleEntity>>(emptyList())
    val milkSalesForPeriod: StateFlow<List<MilkSaleEntity>> get() = _milkSalesForPeriod

    private val _milkSalesTotal = MutableStateFlow<List<MilkSaleEntity>>(emptyList())
    val milkSalesTotal: StateFlow<List<MilkSaleEntity>> get() = _milkSalesTotal

    private val _totalQuantity = MutableStateFlow(0.0)
    val totalQuantity: StateFlow<Double> get() = _totalQuantity

    private val _totalRevenue = MutableStateFlow(0.0)
    val totalRevenue: StateFlow<Double> get() = _totalRevenue

    private val _averagePrice = MutableStateFlow(0.0)
    val averagePrice: StateFlow<Double> get() = _averagePrice

    init {
        getMilkSalesTillNow()
    }

    // Function to insert a new MilkSale into the database
    fun insertMilkSale(milkSale: MilkSaleEntity) {
        viewModelScope.launch {
            repository.insertMilkSale(milkSale)
        }
    }

    // Function to fetch MilkSales for till now
    private fun getMilkSalesTillNow() {
        viewModelScope.launch {
            repository.getMilkSalesTotal().collect {
                _milkSalesTotal.emit(it)
            }
        }
    }

    // Function to fetch total Milk sale till now
    private fun getMilkTotalQuantity() {
        viewModelScope.launch {
            repository.getMilkSalesTotal().collect {
                _milkSalesTotal.emit(it)
            }
        }
    }

    // Function to fetch MilkSales for a given period
    fun getMilkSalesForPeriod(startDate: Date, endDate: Date) {
        viewModelScope.launch {
            repository.getMilkSalesForPeriod(startDate, endDate).collect {
                _milkSalesForPeriod.emit(it)
            }
        }
    }

    // Function to fetch total quantity for a given period
    fun getTotalQuantityForPeriod(startDate: Date, endDate: Date) {
        viewModelScope.launch {
            val totalQuantity = repository.getTotalQuantityForPeriod(startDate, endDate).collect() {
                _totalQuantity.emit(it)
            }
        }
    }

    // Function to fetch total revenue for a given period
    fun getTotalRevenueForPeriod(startDate: Date, endDate: Date) {
        viewModelScope.launch {
            val totalRevenue = repository.getTotalRevenueForPeriod(startDate, endDate).collect {
                _totalQuantity.emit(it)
            }
        }

        // Function to fetch average price for a given period
        fun getAveragePriceForPeriod(startDate: Date, endDate: Date) {
            viewModelScope.launch {
                val averagePrice = repository.getAveragePriceForPeriod(startDate, endDate).collect {
                    _averagePrice.emit(it)
                }
            }
        }
    }
}