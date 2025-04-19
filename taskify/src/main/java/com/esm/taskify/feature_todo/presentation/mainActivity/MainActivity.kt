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
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
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
import com.esm.taskify_news.data.local.NewsDao
import com.esm.taskify_news.domain.model.Article
import com.esm.taskify_news.domain.model.Source
import com.esm.taskify_news.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    //test DB
    @Inject
    lateinit var dao: NewsDao


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        lifecycleScope.launch {
        dao.upsert(
            Article(
                author = "",
                title = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
                description = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
                content = "We use cookies and data to Deliver and maintain Google services Track outages and protect against spam, fraud, and abuse Measure audience engagement and site statistics to unde… [+1131 chars]",
                publishedAt = "2023-06-16T22:24:33Z",
                source = Source(
                    id = "", name = "bbc"
                ),
                url = "https://consent.google.com/ml?continue=https://news.google.com/rss/articles/CBMiaWh0dHBzOi8vY3J5cHRvc2F1cnVzLnRlY2gvY29pbmJhc2Utc2F5cy1hcHBsZS1ibG9ja2VkLWl0cy1sYXN0LWFwcC1yZWxlYXNlLW9uLW5mdHMtaW4td2FsbGV0LXJldXRlcnMtY29tL9IBAA?oc%3D5&gl=FR&hl=en-US&cm=2&pc=n&src=1",
                urlToImage = "https://media.wired.com/photos/6495d5e893ba5cd8bbdc95af/191:100/w_1280,c_limit/The-EU-Rules-Phone-Batteries-Must-Be-Replaceable-Gear-2BE6PRN.jpg"
            )
        )
        }




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