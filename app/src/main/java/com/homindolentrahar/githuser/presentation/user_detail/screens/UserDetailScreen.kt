package com.homindolentrahar.githuser.presentation.user_detail.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.homindolentrahar.githuser.presentation.user_detail.UserDetailViewModel

@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun UserDetailScreen(
    navController: NavController,
    viewModel: UserDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        state.userDetail?.let { detail ->
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                stickyHeader {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(
                            onClick = {
                                navController.navigateUp()
                            }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                tint = Color.White,
                                contentDescription = "Back",
                                modifier = Modifier
                                    .width(16.dp)
                                    .height(16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "@${detail.username}",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        )
                    }
                }
                item {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                painter = rememberImagePainter(detail.avatarUrl),
                                contentDescription = detail.username,
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround,
                            ) {
                                Column(
                                    modifier = Modifier.wrapContentWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Text(
                                        text = detail.publicRepos.toString(),
                                        style = TextStyle(
                                            color = Color.Black,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                        )
                                    )
                                    Text(
                                        text = "Repos",
                                        style = TextStyle(
                                            color = Color.DarkGray,
                                            fontSize = 12.sp,
                                        )
                                    )
                                }
                                Column(
                                    modifier = Modifier.wrapContentWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Text(
                                        text = detail.followers.toString(),
                                        style = TextStyle(
                                            color = Color.Black,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                        )
                                    )
                                    Text(
                                        text = "Followers",
                                        style = TextStyle(
                                            color = Color.DarkGray,
                                            fontSize = 12.sp,
                                        )
                                    )
                                }
                                Column(
                                    modifier = Modifier.wrapContentWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Text(
                                        text = detail.following.toString(),
                                        style = TextStyle(
                                            color = Color.Black,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                        )
                                    )
                                    Text(
                                        text = "Following",
                                        style = TextStyle(
                                            color = Color.DarkGray,
                                            fontSize = 12.sp,
                                        )
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = detail.name,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        )
                        Text(
                            text = detail.bio,
                            style = TextStyle(
                                color = Color.LightGray,
                                fontSize = 12.sp,
                            )
                        )
                    }
                }
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                style = MaterialTheme.typography.h4.copy(
                    color = Color.Red
                ),
                textAlign = TextAlign.Center,
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}