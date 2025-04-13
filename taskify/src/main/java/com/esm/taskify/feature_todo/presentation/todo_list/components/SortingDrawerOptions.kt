package com.esm.taskify.feature_todo.presentation.todo_list.components

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import com.esm.taskify.core.util.TodoListStrings
import com.esm.taskify.feature_todo.domain.util.SortingDirection
import com.esm.taskify.feature_todo.domain.util.TodoItemOrder

@Composable
fun SortingDrawerOptions(
    todoItemOrder: TodoItemOrder,
    onOrderChange: (TodoItemOrder) -> Unit
){
    val titleSelected = todoItemOrder::class == TodoItemOrder.Title::class
    NavigationDrawerItem(
        label = {
            IconRow(
                text = TodoListStrings.TITLE,
                isChecked = titleSelected
            )
        },
        selected = false,
        onClick = {
            onOrderChange(TodoItemOrder.Title(todoItemOrder.sortingDirection, todoItemOrder.showArchived))
        }
    )

    val timeSelected = todoItemOrder::class == TodoItemOrder.Time::class
    NavigationDrawerItem(
        label = {
            IconRow(
                text = TodoListStrings.TIME,
                isChecked = timeSelected
            )
        },
        selected = false,
        onClick = {
            onOrderChange(TodoItemOrder.Time(todoItemOrder.sortingDirection, todoItemOrder.showArchived))
        }
    )

    val completedSelected = todoItemOrder::class == TodoItemOrder.Completed::class
    NavigationDrawerItem(
        label = {
            IconRow(
                text = TodoListStrings.Completed,
                isChecked = completedSelected
            )
        },
        selected = false,
        onClick = {
            onOrderChange(TodoItemOrder.Completed(todoItemOrder.sortingDirection, todoItemOrder.showArchived))
        }
    )

    HorizontalDivider()

    val sortDownSelected = todoItemOrder.sortingDirection == SortingDirection.Down
    NavigationDrawerItem(
        label = {
            IconRow(
                text = TodoListStrings.SORT_DOWN,
                isChecked = sortDownSelected
            )
        },
        selected = false,
        onClick = {
            onOrderChange(todoItemOrder.copy(SortingDirection.Down, todoItemOrder.showArchived))
        }
    )

    val sortUpSelected = todoItemOrder.sortingDirection == SortingDirection.Up
    NavigationDrawerItem(
        label = {
            IconRow(
                text = TodoListStrings.SORT_UP,
                isChecked = sortUpSelected
            )
        },
        selected = false,
        onClick = {
            onOrderChange(todoItemOrder.copy(SortingDirection.Up, todoItemOrder.showArchived))
        }
    )

    HorizontalDivider()

    NavigationDrawerItem(
        label = {
            IconRow(text = TodoListStrings.SHOW_ARCHIVED, isChecked = todoItemOrder.showArchived)
        }, selected = false,
        onClick = {
            onOrderChange(todoItemOrder.copy(todoItemOrder.sortingDirection, !todoItemOrder.showArchived))
        })
}
