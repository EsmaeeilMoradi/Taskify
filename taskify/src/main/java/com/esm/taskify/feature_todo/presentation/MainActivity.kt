package com.esm.taskify.feature_todo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.esm.taskify.feature_todo.presentation.todo_list.TodoListScreen
import com.esm.taskify.feature_todo.presentation.todo_list.TodoListViewModel
import com.esm.taskify.feature_todo.presentation.todo_new_update.TodoNewUpdateScreen
import com.esm.taskify.feature_todo.presentation.util.Screen
import com.esm.taskify.feature_todo.presentation.util.Screen.TodoNewUpdateScreen
import com.esm.taskify.ui.theme.TodoTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()
                    val listViewModel: TodoListViewModel = hiltViewModel()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.TodoItemListScreen.route,
                    ){
                        composable(route = Screen.TodoItemListScreen.route){
                            TodoListScreen(
                                navController = navController,
                                viewModel = listViewModel
                            )
                        }
                        composable(
                            route = Screen.TodoNewUpdateScreen.route + "?todoId={todoId}",
                            arguments = listOf(
                                navArgument(
                                    name = "todoId"
                                ){
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ){
                            TodoNewUpdateScreen(
                                navController = navController
                            )


                        }
                    }
                }
            }
        }
    }
}