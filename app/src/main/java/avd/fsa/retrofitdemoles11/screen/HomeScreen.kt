package avd.fsa.retrofitdemoles11.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import avd.fsa.retrofitdemoles11.model.ToDo
import avd.fsa.retrofitdemoles11.network.TodoApi
import avd.fsa.retrofitdemoles11.uiComponent.CodeCard
import avd.fsa.retrofitdemoles11.uiComponent.FilterChipGroup
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    Column(modifier = Modifier.padding(all = 12.dp)) {

        val chipsList = listOf("/POST", "/GET", "/GET/1", "/DELETE", "/PUT")
        var headLine by remember { mutableStateOf(chipsList[0]) }
        val scope = rememberCoroutineScope()
        var jsonResponse by remember { mutableStateOf("") }
        var showLoading by remember { mutableStateOf(false) }

        val apiTodo = TodoApi.todoApiService

        val toDo = ToDo(
            owner = 1,
            completed = true,
            title = "How to Make HTTP Requests With Retrofit in Android",
        )


        Text(
            style = MaterialTheme.typography.headlineLarge,
            text = headLine
        )
        Divider()


        FilterChipGroup(items = chipsList,
            onSelectedChanged = { selectedIndex: Int ->
                headLine = chipsList[selectedIndex]
                jsonResponse = ""
            })

        OutlinedTextField(modifier = Modifier.fillMaxWidth(),
            value = "https://jsonplaceholder.typicode.com/",
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "Localized Description",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(modifier = Modifier
            .align(alignment = Alignment.CenterHorizontally)
            .width(200.dp),
            onClick = {
                showLoading = true
                scope.launch {
                    when (headLine) {
                        "/POST" -> {
                            jsonResponse = apiTodo.createNewTodo(toDo).toString()
                        }

                        "/GET" -> {
                            jsonResponse = apiTodo.getAllTodos().toString()

                        }

                        "/GET/1" -> {
                            jsonResponse = apiTodo.getTodoById(1).toString()

                        }

                        "/PUT" -> {
                            // Use PUT request to Update data
                            jsonResponse = apiTodo.updateTodo(
                                id = 1,
                                todo = toDo
                            ).toString()

                        }

                        "/DELETE" -> {
                            jsonResponse = apiTodo.deleteTodo(2).toString()
                        }
                    }
                    showLoading = !showLoading
                }

            }) {
            Text(text = "Send")
        }
        if (showLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
        CodeCard(jsonStr = jsonResponse)
    }

}
