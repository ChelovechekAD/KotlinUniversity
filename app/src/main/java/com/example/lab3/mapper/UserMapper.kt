package com.example.lab3.mapper

import com.example.lab3.dto.UserDTO
import com.example.lab3.model.User

interface UserMapper {

    companion object {
        fun toEntity(userDTO: UserDTO ) : User {
            return User(userDTO.id, userDTO.login, userDTO.email, userDTO.phone)
        }
    }
}