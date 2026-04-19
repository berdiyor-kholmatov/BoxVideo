package com.example.boxvideo.domain.util

interface Mapper <M, D> {
    fun domainToModel(domain: D): M
    fun modelToDomain(model: M): D
}
