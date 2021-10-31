package com.homindolentrahar.githuser.domain.usecases.users

import javax.inject.Inject

data class UsersUsecases @Inject constructor (
    val getUsers: GetUsers,
    val getSingleUser: GetSingleUser,
    val getFollowers: GetFollowers,
    val searchUser: SearchUser,
)
