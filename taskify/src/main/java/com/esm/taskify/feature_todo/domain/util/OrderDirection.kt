package com.esm.taskify.feature_todo.domain.util

sealed class OrderDirection{
    object Up: OrderDirection()
    object Down: OrderDirection()
}