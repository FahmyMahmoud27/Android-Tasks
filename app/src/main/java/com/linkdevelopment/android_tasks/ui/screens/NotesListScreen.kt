package com.linkdevelopment.android_tasks.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linkdevelopment.android_tasks.data.Note
import com.linkdevelopment.android_tasks.ui.theme.Purple40
import com.linkdevelopment.android_tasks.ui.utils.NoteItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesListScreen(
    notes: List<Note>,
    onFABClick: () -> Unit,
    onNoteClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(com.linkdevelopment.android_tasks.R.string.notes),
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFABClick,
                containerColor = Purple40,
                contentColor = Color.White,
                shape = RoundedCornerShape(100.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(notes) { singleNote ->
                NoteItem(
                    note = singleNote,
                    onNoteClick = onNoteClick
                )
            }
        }
    }
}