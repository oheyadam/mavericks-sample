package com.oheyadam.mavericks.login

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.PersistState
import com.airbnb.mvrx.Uninitialized

data class LoginState(
    @PersistState val userName: String = "",
    @PersistState val password: String = "",
    /** We use this Async if we want to keep track of the state of the current network request */
    val request: Async<Boolean> = Uninitialized
) : MavericksState {
    val canSubmit = userName.isNotBlank() && password.isNotBlank()
    val isLoading: Boolean = request is Loading
    val isFail: Boolean = !isLoading && request is Fail
}
