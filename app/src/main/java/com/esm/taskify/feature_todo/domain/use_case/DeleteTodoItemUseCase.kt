package com.esm.taskify.feature_todo.domain.use_case

import com.esm.taskify.feature_todo.domain.model.TodoItem
import com.esm.taskify.feature_todo.domain.repo.TodoListRepo
import javax.inject.Inject

class DeleteTodoItemUseCase @Inject constructor(
    private val repo: TodoListRepo
) {
    suspend operator fun invoke(todo: TodoItem){
        repo.deleteTodoItem(todo)
    }
}