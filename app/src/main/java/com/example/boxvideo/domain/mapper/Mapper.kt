package com.example.boxvideo.domain.mapper

interface Mapper <M, D> {
    fun domainToModel(domain: D): M
    fun modelToDomain(model: M): D
}
