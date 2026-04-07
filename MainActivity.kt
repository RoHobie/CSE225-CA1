package com.example.ca1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Dashboard()
        }
    }
}

@Composable
fun Dashboard() {

    data class Course(
        val title: String,
        val instructor: String,
        val progress: MutableState<Float>
    )

    val courses = remember {
        listOf(
            Course("INT001", "Ms. A", mutableStateOf(0.2f)),
            Course("INT002", "Mr. B", mutableStateOf(0.5f)),
            Course("INT003", "Mr. C", mutableStateOf(0.7f))
        )
    }

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
        ) {
            items(courses) { course ->

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            Toast.makeText(
                                context,
                                "${course.title} - ${course.instructor} (${(course.progress.value * 100).toInt()}%)",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .padding(16.dp)
                ) {
                    Text(text = course.title)
                    Text(text = course.instructor)

                    ProgressBar(course.progress.value)
                }
            }
        }

        Button(
            onClick = {
                for (course in courses) {
                    course.progress.value = 0f
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reset progress")
        }
    }
}

@Composable
fun ProgressBar(progress: Float) {
    LinearProgressIndicator(
        progress = { progress }
    )
}
