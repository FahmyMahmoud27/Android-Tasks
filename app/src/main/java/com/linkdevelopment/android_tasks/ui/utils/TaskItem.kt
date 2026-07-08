package com.linkdevelopment.android_tasks.ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linkdevelopment.android_tasks.ui.theme.Black
import com.linkdevelopment.android_tasks.ui.theme.LightGreyBg

@Composable
fun TaskItem(
    taskName: String,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = LightGreyBg),
        shape = RoundedCornerShape(10.dp),

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = taskName,
                fontSize = 20.sp,
                color = Black
            )

            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier.size(30.dp)
            ) {
                Icon(imageVector = Icons.Default.Delete,
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                    )
            }
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun TaskItemPreview() {
    TaskItem(taskName = "Task 1", onDeleteClick = {})
}




