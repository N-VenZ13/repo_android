package com.example.component_basic_jetpack.ui.components

import android.icu.text.ListFormatter.Width
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SweepGradient
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.Navigator

@Composable
fun ButtonCustom(
    color: Color,
//    navigator: Navigator? = null,
    text: String,
    onclick: () -> Unit,
    width: Dp,
    height: Dp
) {
    Box(
        modifier = Modifier
            .width(width)
            .height(height)
    ) {
        FilledTonalButton(
            onClick = onclick,
            modifier = Modifier.fillMaxSize(),
            colors = ButtonDefaults.buttonColors(containerColor = color)
        ) {
            Text(
                text = text,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun GradientButton(
    text: String,
    textcolor: Color = Color.White,
    gradient: Brush = Brush.horizontalGradient(
        colors = listOf(Color(0xFF6200EE), Color(0xFF03DAC5))
    ),
    onclick: () -> Unit
) {
    Button(
        onClick = onclick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(),
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(
            modifier = Modifier
                .background(gradient).width(250.dp)
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .width(250.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, color = textcolor)
        }
    }

}

@Preview
@Composable
fun ButtonCustomPreview() {
//    ButtonCustom(color = Color.Green, text = "Login", onclick = {}, width = 120.dp, height = 50.dp)

    GradientButton(
        text = "Submit", gradient = Brush.horizontalGradient(
            colors = listOf(Color.Cyan, Color.Green)
        )
    )
    {

    }
}