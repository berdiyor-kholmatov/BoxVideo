package com.example.boxvideo.network.client

//import androidx.compose.ui.autofill.ContentType
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
}