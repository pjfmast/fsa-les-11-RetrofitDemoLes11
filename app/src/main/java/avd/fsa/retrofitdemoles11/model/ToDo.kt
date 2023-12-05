package avd.fsa.retrofitdemoles11.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ToDo (
    @SerialName(value="id")
    val id: Int? = null,

    @SerialName(value="userId")
    val owner: Int,

    @SerialName(value="title")
    val title:String,

    @SerialName(value="completed")
    val completed: Boolean
)