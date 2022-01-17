package com.oheyadam.mavericks.login

import com.oheyadam.mavericks.repository.Repository
import javax.inject.Inject

class Login @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(username: String, password: String): Boolean {
        return repository.login(username, password)
    }
}

class FailLogin @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): Boolean {
        return repository.failLogin()
    }
}
