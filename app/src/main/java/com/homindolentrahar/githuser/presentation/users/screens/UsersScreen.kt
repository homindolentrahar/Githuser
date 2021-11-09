package com.homindolentrahar.githuser.presentation.users.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import com.homindolentrahar.githuser.R
import com.homindolentrahar.githuser.domain.model.UserModel
import com.homindolentrahar.githuser.presentation.Screens
import com.homindolentrahar.githuser.presentation.core.components.Appbar
import com.homindolentrahar.githuser.presentation.users.UsersViewModel
import com.homindolentrahar.githuser.presentation.users.components.UserListItem
import com.homindolentrahar.githuser.ui.theme.White

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun UsersScreen(
    navController: NavController,
    viewModel: UsersViewModel = hiltViewModel()
) {
    val users = viewModel.users.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            Appbar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_github),
                        contentDescription = "Logo",
                    )
                },
                actions = {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search User",
                            tint = Color.LightGray
                        )
                    }
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More Options",
                            tint = White,
                        )
                    }
                }
            )
        },
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(users) { user ->
                UserListItem(
                    user = user ?: UserModel.empty(),
                    onClick = { model ->
                        navController.navigate(Screens.UserDetailScreen.route + "/${model.username}")
                    }
                )
            }
            users.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillParentMaxSize()
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.Center),
                                    color = MaterialTheme.colors.primary,
                                    strokeWidth = 4.dp,
                                )
                            }
                        }
                    }
                    loadState.append is LoadState.Loading -> {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                CircularProgressIndicator(
                                    color = MaterialTheme.colors.primary,
                                    strokeWidth = 4.dp,
                                )
                            }
                        }
                    }
                    loadState.refresh is LoadState.Error -> {
                        val error = users.loadState.refresh as LoadState.Error
                        item {
                            Column(
                                modifier = Modifier
                                    .fillParentMaxSize()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Image(
                                    painter = painterResource(id = if (isSystemInDarkTheme()) R.drawable.ic_error_dark else R.drawable.ic_error_light),
                                    contentDescription = "error",
                                    modifier = Modifier
                                        .width(48.dp)
                                        .height(48.dp)
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "Something went wrong...",
                                    style = MaterialTheme.typography.h3,
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = error.error.localizedMessage!!,
                                    style = MaterialTheme.typography.body2,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }
                    loadState.append is LoadState.Error -> {
                        val error = users.loadState.append as LoadState.Error
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = error.error.localizedMessage!!,
                                    style = MaterialTheme.typography.body2.copy(
                                        color = Color.Red,
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
//    Box(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        if (state.error.isNotBlank()) {
//            Text(
//                text = state.error,
//                style = MaterialTheme.typography.h4.copy(
//                    color = Color.Red,
//                ),
//                textAlign = TextAlign.Center,
//            )
//        }
//
//        if (state.isLoading) {
//            CircularProgressIndicator(
//                modifier = Modifier.align(Alignment.Center)
//            )
//        }
//    }
}