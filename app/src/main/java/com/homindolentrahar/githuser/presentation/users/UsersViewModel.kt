package com.homindolentrahar.githuser.presentation.users

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homindolentrahar.githuser.domain.model.UserModel
import com.homindolentrahar.githuser.domain.usecases.users.UsersUsecases
import com.homindolentrahar.githuser.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersUsecases: UsersUsecases
) : ViewModel() {
    private val _state = mutableStateOf(UsersState())
    val state: State<UsersState> = _state

    private var getAllUsersJob: Job? = null

    init {
        getAllUsers()
    }

    private fun getAllUsers() {
        getAllUsersJob = usersUsecases.getUsers().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = result.message ?: "Mboh nangopo",
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true,
                    )
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        users = result.data ?: emptyList()
                    )
                }
            }
        }
            .launchIn(viewModelScope)
    }

    override fun onCleared() {
        getAllUsersJob?.cancel()

        super.onCleared()
    }
}

data class UsersState(
    val isLoading: Boolean = false,
    val users: List<UserModel> = emptyList(),
    val error: String = "",
)