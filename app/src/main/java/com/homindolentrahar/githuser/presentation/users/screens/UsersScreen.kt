package com.homindolentrahar.githuser.presentation.users.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.homindolentrahar.githuser.presentation.Screens
import com.homindolentrahar.githuser.presentation.users.UsersViewModel
import com.homindolentrahar.githuser.presentation.users.components.UserListItem

@ExperimentalCoilApi
@Composable
fun UsersScreen(
    navController: NavController,
    viewModel: UsersViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Row(
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
                ) {
                    Text(
                        text = "Githuser",
                        style = MaterialTheme.typography.h3.copy(
                            color = Color.White
                        ),
                    )
                }
            }
            items(state.users) { user ->
                UserListItem(
                    user = user,
                    onClick = { model ->
                        navController.navigate(Screens.UserDetailScreen.route + "/${model.username}")
                    }
                )
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                style = MaterialTheme.typography.h4.copy(
                    color = Color.Red,
                ),
                textAlign = TextAlign.Center,
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}