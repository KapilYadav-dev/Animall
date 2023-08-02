package `in`.mrkaydev.animall.models

import java.util.*

data class MilkSale(
    val id: Long = 0,
    val quantity: Double,
    val pricePerUnit: Double,
    val totalAmount: Double,
    val date: Date
)