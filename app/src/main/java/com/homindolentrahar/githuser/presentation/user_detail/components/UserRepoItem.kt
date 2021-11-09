package com.homindolentrahar.githuser.presentation.user_detail.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.homindolentrahar.githuser.domain.model.RepoModel
import com.homindolentrahar.githuser.ui.theme.Gray

@ExperimentalMaterialApi
@Preview
@Composable
fun UserRepoItem(
    repo: RepoModel = RepoModel.empty(),
    onClick: (RepoModel) -> Unit = { model -> Log.d(model.nodeId, model.name) }
) {
    Card(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.background,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colors.surface),
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth(),
        onClick = { onClick(repo) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = repo.name,
                    color = MaterialTheme.colors.primary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = repo.description,
                    color = MaterialTheme.colors.onBackground,
                    fontSize = 12.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.wrapContentWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                MaterialTheme.colors.primary,
                                CircleShape
                            )
                            .size(12.dp),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = repo.language,
                        color = Gray,
                        fontSize = 12.sp,
                    )
                    if (repo.stargazersCount > 0) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Row(
                            modifier = Modifier.wrapContentWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                modifier = Modifier.size(12.dp),
                                imageVector = Icons.Default.Star,
                                contentDescription = "Stars",
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = repo.stargazersCount.toString(),
                                color = Gray,
                                fontSize = 12.sp,
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .border(1.dp, MaterialTheme.colors.surface, RoundedCornerShape(8.dp)),
                ) {
                    Text(
                        text = repo.visibility,
                        color = Gray,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}