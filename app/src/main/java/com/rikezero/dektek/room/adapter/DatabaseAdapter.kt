package com.rikezero.dektek.room.adapter

import com.rikezero.dektek.networking.response.DekTeKResponse

interface DatabaseAdapter<T> {
    suspend fun insert(entity: T): DekTeKResponse<Unit>{
        throw UnsupportedOperationException("Not implemented for this adapter")
    }
    suspend fun update(entity: T): DekTeKResponse<Unit>{
        throw UnsupportedOperationException("Not implemented for this adapter")
    }
    suspend fun delete(entity: T): DekTeKResponse<Unit>{
        throw UnsupportedOperationException("Not implemented for this adapter")
    }
    suspend fun getById(id: String): DekTeKResponse<T?>{
        throw UnsupportedOperationException("Not implemented for this adapter")
    }
    suspend fun getAll(): DekTeKResponse<List<T>>{
        throw UnsupportedOperationException("Not implemented for this adapter")
    }
}
