package com.example

interface UserRepository {

    fun addUser(user: User)

    fun getUser(id: Int): User

    fun updateUser(user: User)

    fun getUsers(): List<User>

    fun deleteUser(id: Int)
}