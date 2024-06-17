package com.example.plugins

import com.example.User
import com.example.UserRepositoryImpl
import com.google.gson.Gson
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import kotlin.random.Random

fun Application.configureRouting() {
    val repos = UserRepositoryImpl()
    routing {
        get("/adduser/{id}/{name}/{email}") {
            call.apply {
                User(
                    id = parameters["id"]!!.toInt(),
                    name = parameters["name"]!!,
                    email = parameters["email"]!!
                ).let {
                    Gson().toJson(repos.addUser(it))
                    call.respondText(it.toString())
                }
            }
        }
        get("/user/{id}") {
            call.respondText(Gson().toJson(repos.getUser(call.parameters["id"]!!.toInt())))
        }
        get("/users") {
            call.respondText(Gson().toJson(repos.getUsers()))
        }

        get("/updateuser/{id}/{name}/{email}") {

            call.apply {
                User(
                    id = parameters["id"]!!.toInt(),
                    name = parameters["name"]!!, email = parameters["email"]!!
                ).let {
                    repos.updateUser(user = it)
                    respondText(Gson().toJson(it))
                }
            }
        }

        get("/deleteuser/{id}") {
            call.apply {
                repos.deleteUser(call.parameters["id"]!!.toInt())
                respondText("user deleted")
            }
        }

    }
}

