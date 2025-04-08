package com.esm.taskify.feature_todo.domain.use_case

import com.esm.taskify.core.util.TodoNewUpdateConstants
import com.esm.taskify.feature_todo.domain.model.TodoItem
import com.esm.taskify.feature_todo.domain.repo.TodoListRepo
import com.esm.taskify.feature_todo.domain.util.InvalidTodoItemException
import javax.inject.Inject

class UpdateTodoItemUseCase @Inject constructor(
    private val repo: TodoListRepo
) {
    suspend operator fun invoke(
        todo: TodoItem
    ) {
        if(todo.title.isBlank()){
            throw InvalidTodoItemException(TodoNewUpdateConstants.EMPTY_TITLE)
        }
        if(todo.description.isBlank()){
            throw InvalidTodoItemException(TodoNewUpdateConstants.EMPTY_DESCRIPTION)
        }
        repo.updateTodoItem(todo)
    }
}