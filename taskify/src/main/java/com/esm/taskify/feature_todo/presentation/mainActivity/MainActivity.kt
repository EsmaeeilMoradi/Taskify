package com.esm.taskify.feature_todo.presentation.mainActivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.esm.taskify.feature_todo.presentation.navgraph.NavGraph
import com.esm.taskify.feature_todo.presentation.todo_list.TodoListScreen
import com.esm.taskify.feature_todo.presentation.todo_list.TodoListViewModel
import com.esm.taskify.feature_todo.presentation.todo_new_update.TodoNewUpdateScreen
import com.esm.taskify.feature_todo.presentation.util.Screen
import com.esm.taskify.feature_todo.presentation.util.Screen.TodoNewUpdateScreen
import com.esm.taskify.ui.theme.TodoTheme
import com.esm.taskify_news.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)


        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition.value
            }
        }

        setContent {
            NewsAppTheme {
                enableEdgeToEdge()
                Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
                    NavGraph(startDestination = viewModel.startDestination.value)
                }
            }
            //TodoFunction()
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TodoFunction() {
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
            ) {
                composable(route = Screen.TodoItemListScreen.route) {
                    TodoListScreen(
                        navController = navController,
                        viewModel = listViewModel
                    )
                }
                composable(
                    route = TodoNewUpdateScreen.route + "?todoId={todoId}",
                    arguments = listOf(
                        navArgument(
                            name = "todoId"
                        ) {
                            type = NavType.IntType
                            defaultValue = -1
                        }
                    )
                ) {
                    TodoNewUpdateScreen(
                        navController = navController
                    )


                }
            }
        }
    }
}