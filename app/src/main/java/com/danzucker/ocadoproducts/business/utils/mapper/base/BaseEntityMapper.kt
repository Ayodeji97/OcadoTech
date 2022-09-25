package com.danzucker.ocadoproducts.business.utils.mapper.base

interface BaseEntityMapper<T, D> {
    fun transformToDomain (type : T) : D
}