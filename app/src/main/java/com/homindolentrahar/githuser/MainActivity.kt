package com.homindolentrahar.githuser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.homindolentrahar.githuser.common.Constants
import com.homindolentrahar.githuser.presentation.Screens
import com.homindolentrahar.githuser.presentation.user_detail.screens.UserDetailScreen
import com.homindolentrahar.githuser.presentation.users.screens.UsersScreen
import com.homindolentrahar.githuser.ui.theme.GithuserTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            this.window.statusBarColor = ContextCompat.getColor(this, R.color.black)
            GithuserTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screens.UsersScreen.route,
                    ) {
                        composable(route = Screens.UsersScreen.route) {
                            UsersScreen(navController = navController)
                        }
                        composable(route = Screens.UserDetailScreen.route + "/{${Constants.PARAM_USERNAME}}") {
                            UserDetailScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}