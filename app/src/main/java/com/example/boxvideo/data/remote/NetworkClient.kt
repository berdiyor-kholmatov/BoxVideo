package com.example.boxvideo.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.reflect.TypeInfo
import java.io.Closeable
import kotlin.reflect.KClass

class NetworkClient (private val httpClient: HttpClient): Closeable {

    override fun close() {
        httpClient.close()
    }

    suspend fun <T : Any> get( //there is 2 ways, 1 inline reified with allowing to be exposed NetworkClient, 2 is writing type, i choose the 2 one
        url: String,
        headers: Map<String, String>,
        typeInfo: TypeInfo
    ): T {
        val response: HttpResponse = httpClient.get(url)
        {
            headers.forEach { (key, value) -> header(key, value) }
        }
        return response.body(typeInfo)
    }

    suspend fun <T : Any> get(
        url: String,
        headers: Map<String, String>,
        responseType: KClass<T>
    ): T {
        val response: HttpResponse = httpClient.get(url)
        {
            headers.forEach { (key, value) -> header(key, value) }
        }
        return response.body(TypeInfo(type = responseType))
    }

    suspend fun <T : Any> post(
        url: String,
        headers: Map<String, String>,
        body: Any?,
        responseType: KClass<T>
    ): T {
        val response: HttpResponse = httpClient.post(url) {
            headers.forEach { (key, value) -> header(key, value) }
            body?.let {
                contentType(ContentType.Application.Json)
                setBody(it)
            }
        }
        return response.body(TypeInfo(type = responseType))
    }

    suspend fun  put(
        url: String,
        headers: Map<String, String>,
        body: Any?,
    ): HttpResponse {
        val response: HttpResponse = httpClient.post(url) {
            headers.forEach { (key, value) -> header(key, value) }
            body?.let {
                contentType(ContentType.Application.Json)
                setBody(it)
            }
        }
        return response
    }


    suspend fun delete(
        url: String,
        headers: Map<String, String>,
    ): HttpResponse {
        val response: HttpResponse = httpClient.get(url)
        {
            headers.forEach { (key, value) -> header(key, value) }
        }
        return response
    }


}