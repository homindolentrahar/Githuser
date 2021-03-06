package com.homindolentrahar.githuser.presentation.user_detail.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.homindolentrahar.githuser.R
import com.homindolentrahar.githuser.domain.model.RepoModel
import com.homindolentrahar.githuser.presentation.core.components.Appbar
import com.homindolentrahar.githuser.presentation.user_detail.UserDetailViewModel
import com.homindolentrahar.githuser.presentation.user_detail.components.UserDetailStats
import com.homindolentrahar.githuser.presentation.user_detail.components.UserRepoItem
import com.homindolentrahar.githuser.ui.theme.White

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun UserDetailScreen(
    navController: NavController,
    viewModel: UserDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val repos = viewModel.userRepos.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            Appbar(
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go Back",
                            tint = Color.LightGray,
                        )
                    }
                },
                title = {
                    Text(
                        text = "@${state.userDetail?.username}",
                        style = TextStyle(
                            color = White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    )
                }
            )
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            state.userDetail?.let { detail ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
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
                                UserDetailStats(
                                    statsCount = detail.publicRepos.toDouble(),
                                    statsName = "Repos",
                                    onClick = {}
                                )
                                UserDetailStats(
                                    statsCount = detail.followers.toDouble(),
                                    statsName = "Followers",
                                    onClick = {}
                                )
                                UserDetailStats(
                                    statsCount = detail.following.toDouble(),
                                    statsName = "Followings",
                                    onClick = {}
                                )
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
                            ),
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        val context = LocalContext.current
                        val githubIntent = Intent(Intent.ACTION_VIEW, Uri.parse(detail.htmlUrl))

                        OutlinedButton(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentPadding = PaddingValues(12.dp),
                            border = BorderStroke(1.5.dp, Color.LightGray),
                            shape = RoundedCornerShape(
                                corner = CornerSize(8.dp),
                            ),
                            onClick = { context.startActivity(githubIntent) }
                        ) {
                            Text(
                                text = "Github Page",
                                style = MaterialTheme.typography.h4.copy(
                                    color = White,
                                ),
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Repositories",
                        color = White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyColumn(
                        modifier = Modifier.fillMaxHeight(),
                    ) {
                        items(repos) { repo ->
                            Box(
                                modifier = Modifier.padding(vertical = 8.dp)
                            ) {
                                UserRepoItem(
                                    repo = repo ?: RepoModel.empty(),
                                    onClick = { item ->
                                        Log.d("User Detail", item.name)
                                    }
                                )
                            }
                        }
                        repos.apply {
                            when {
                                loadState.refresh is LoadState.Loading -> {
                                    item {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
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
                                    val error = loadState.refresh as LoadState.Error
                                    item {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize()
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
                                    val error = loadState.append as LoadState.Error
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
}