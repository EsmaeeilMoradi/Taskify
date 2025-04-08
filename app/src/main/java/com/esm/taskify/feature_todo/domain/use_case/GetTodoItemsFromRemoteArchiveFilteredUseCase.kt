package com.esm.taskify.feature_todo.domain.use_case

import com.esm.taskify.feature_todo.domain.model.TodoItem
import com.esm.taskify.feature_todo.domain.util.OrderDirection
import com.esm.taskify.feature_todo.domain.util.TodoItemOrder

class GetTodoItemsFromRemoteArchiveFilteredUseCase(
        private val getSortedTodoItemsUseCase: GetSortedTodoItemsUseCase
    ) {
        suspend operator fun invoke(
            todoItemOrder: TodoItemOrder = TodoItemOrder.Time(OrderDirection.Down),
            showArchived: Boolean
        ): List<TodoItem> {
            return if(showArchived){
                getSortedTodoItemsUseCase(todoItemOrder)
            }else{
                getSortedTodoItemsUseCase(todoItemOrder).filter{ !it.archived}
            }
        }
    }