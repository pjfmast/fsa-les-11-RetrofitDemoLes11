package avd.fsa.retrofitdemoles11.network

import avd.fsa.retrofitdemoles11.model.ToDo
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path

private const val BASE_URL: String = "https://jsonplaceholder.typicode.com"
private val contentType = "application/json".toMediaType()

/**
 * todo - Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
 */
private val retrofit = Retrofit.Builder()
    .build()


/**
 * todo - Retrofit service object for creating api calls
 */
interface TodoApiService {

}

/**
 * todo - A public Api object that exposes the lazy-initialized Retrofit service
 */
object TodoApi {
    // todo
    }
