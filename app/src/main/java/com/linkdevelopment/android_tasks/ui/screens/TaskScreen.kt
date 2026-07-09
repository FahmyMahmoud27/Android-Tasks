package com.linkdevelopment.android_tasks.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linkdevelopment.android_tasks.ui.theme.Black
import com.linkdevelopment.android_tasks.ui.theme.LightGreenBg
import com.linkdevelopment.android_tasks.ui.theme.PrimaryGreen
import com.linkdevelopment.android_tasks.ui.utils.TaskItem
import androidx.compose.material.icons.filled.Home


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen() {
    var taskText by remember { mutableStateOf("") }
    val tasks = remember { mutableStateListOf("Buy milk", "Walk the dog") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Create Task",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Home, contentDescription = "")
                    }
                },

            )
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(
                text = "WHAT YOU'LL BUILD",
                color = PrimaryGreen,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )


            TextField(
                value = taskText,
                onValueChange = { taskText = it },
                placeholder = { Text("New task...", color = Color.Gray) },
                leadingIcon = {
                },
                trailingIcon = {
                    if (taskText.isNotBlank()) {
                        IconButton(onClick = {
                            tasks.add(taskText)
                            taskText = ""
                        }) {
                            Icon(Icons.Default.Add, contentDescription = "Add Task", tint = PrimaryGreen)
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = LightGreenBg,
                    unfocusedContainerColor = LightGreenBg,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))


            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(tasks) { task ->
                    TaskItem(
                        taskName = task,
                        onDeleteClick = { tasks.remove(task) }
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun TaskScreenPreview() {
    TaskScreen()

}
