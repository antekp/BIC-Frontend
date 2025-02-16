package com.example.bicfrontend.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomBarIcon(imageVector: ImageVector, description: String?) {
    Icon(
        imageVector = imageVector,
        contentDescription = description,
        modifier = Modifier
            .size(28.dp),
        tint = OrangeFill
    )
}

@Preview
@Composable
private fun BottomBarIconPreview() {
    BottomBarIcon(Home,"home")
}