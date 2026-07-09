package com.linkdevelopment.android_tasks.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.linkdevelopment.android_tasks.data.Note
import com.linkdevelopment.android_tasks.ui.screens.AddNoteScreen
import com.linkdevelopment.android_tasks.ui.screens.NoteDetailsScreen
import com.linkdevelopment.android_tasks.ui.screens.NotesListScreen


import kotlinx.serialization.Serializable

@Serializable
data object NotesListRoute

@Serializable
data object AddNoteRoute

@Serializable
data class NoteDetailsRoute(val noteId: String)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val notesList = remember { mutableStateListOf<Note>() }

    NavHost(
        navController = navController,
        startDestination = NotesListRoute
    ) {
        composable<NotesListRoute> {
            NotesListScreen(
                notes = notesList,
                onFABClick = {
                    navController.navigate(AddNoteRoute)
                },
                onNoteClick = { id ->
                    navController.navigate(NoteDetailsRoute(noteId = id))
                }
            )
        }

        composable<AddNoteRoute> {
            AddNoteScreen(
                onSaveClick = { title, description, date ->
                    notesList.add(Note(title = title, description = description, createdAt = date))
                    navController.popBackStack()
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable<NoteDetailsRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<NoteDetailsRoute>()

            val note = remember(route.noteId) { notesList.find { it.id == route.noteId } }

            if (note != null) {
                NoteDetailsScreen(
                    note = note,
                    onDeleteClick = {
                        navController.popBackStack()
                        notesList.remove(note)
                    },
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            } else {
                navController.popBackStack()
            }
        }
    }
}