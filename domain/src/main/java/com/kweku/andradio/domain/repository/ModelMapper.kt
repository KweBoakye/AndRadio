package com.kweku.andradio.domain.repository

interface ModelMapper<M,E> {

    fun fromEntity(from: E): M
    fun toEntity(from: M): E
}