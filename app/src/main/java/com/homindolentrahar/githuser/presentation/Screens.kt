package com.homindolentrahar.githuser.presentation

sealed class Screens(val route: String) {
    object UsersScreen : Screens("/users")
    object UserDetailScreen : Screens("/user")
}
