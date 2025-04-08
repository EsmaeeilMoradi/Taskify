package com.esm.taskify.feature_todo.domain.use_case

import com.esm.taskify.feature_todo.domain.model.TodoItem
import com.esm.taskify.feature_todo.domain.util.OrderDirection
import com.esm.taskify.feature_todo.domain.util.TodoItemOrder

class GetTodoItemsFromCacheArchiveFilteredUseCase(
    private val getTodoItemsFromCacheUseCase: GetTodoItemsFromCacheUseCase
) {
    suspend operator fun invoke(
        todoItemOrder: TodoItemOrder = TodoItemOrder.Time(OrderDirection.Down),
        showArchived: Boolean
    ): List<TodoItem> {
        return if(showArchived){
            getTodoItemsFromCacheUseCase(todoItemOrder)
        }else{
            getTodoItemsFromCacheUseCase(todoItemOrder).filter{ !it.archived}
        }
    }
}