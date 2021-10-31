package com.homindolentrahar.githuser.presentation.users.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.homindolentrahar.githuser.domain.model.UserModel

@ExperimentalCoilApi
@Composable
fun UserListItem(user: UserModel, onClick: (UserModel) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick(user) }
    ) {
        Image(
            painter = rememberImagePainter(user.avatarUrl),
            contentDescription = user.username,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape),
        )
        Spacer(modifier = Modifier.size(16.dp))
        Column(
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = user.username,
                style = MaterialTheme.typography.h3,
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = user.htmlUrl,
                style = MaterialTheme.typography.body2,
            )
        }
    }
}