package tech.mobiledeveloper.mfti

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import tech.mobiledeveloper.mfti.screen.detail.DetailScreen
import tech.mobiledeveloper.mfti.screen.main.MainScreen
import tech.mobiledeveloper.mfti.screen.main.MainViewModel
import tech.mobiledeveloper.mfti.ui.theme.MFTI_SampleTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            MFTI_SampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") {
                            val viewModel = hiltViewModel<MainViewModel>()
                            MainScreen(mainViewModel = viewModel, navController = navController)
                        }

                        composable("detail/{name}") { backStackEntry ->
                            DetailScreen(backStackEntry.arguments?.getString("name").orEmpty())
                        }
                    }
                }
            }
        }
    }
}