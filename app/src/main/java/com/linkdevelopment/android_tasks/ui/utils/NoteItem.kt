package com.linkdevelopment.android_tasks.ui.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linkdevelopment.android_tasks.data.Note

@Composable
fun NoteItem(
    note: Note,
    onNoteClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNoteClick(note.id) },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent // عشان نخليه أبيض وحواليه بوردر خفيف زي الديزاين
        ),
        border = BorderStroke(1.dp, Color(0xFFE0E0E0))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = note.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = note.description,
                fontSize = 14.sp,
                color = Color.DarkGray,
                maxLines = 2, // مطلوب في التاسك سطرين كحد أقصى
                overflow = TextOverflow.Ellipsis // بيحط ... لو الكلام أطول من سطرين
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = note.createdAt,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}







