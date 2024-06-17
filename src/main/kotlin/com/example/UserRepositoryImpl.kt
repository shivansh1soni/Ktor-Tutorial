package com.example

class UserRepositoryImpl: UserRepository {

    override fun addUser(user: User) {
        serverData.add(user)
    }

    override fun getUser(id: Int) =
        serverData.filter {it ->
            it.id == id
        }[0]


    override fun updateUser(user: User) {

        serverData.filter {
            it.id == user.id
        }[0].apply {
            email = user.email
            name = user.name
        }

    }

    override fun getUsers(): List<User> = serverData


    override fun deleteUser(id: Int) {
        for ((index, user) in serverData.withIndex()) {
            if (user.id == id) {
                serverData.removeAt(index)
                break
            }
        }
    }
}