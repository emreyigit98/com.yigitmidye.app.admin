package com.yigitmidye.app.admin.presentation.component.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.yigitmidye.app.admin.R

@Composable
fun LoginButton(
    modifier: Modifier = Modifier,
    onClick : () -> Unit,
    enabled : Boolean
) {
    Button(
        onClick = {onClick()},
        modifier = modifier.fillMaxWidth().padding(horizontal = 50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.app_const_color)
        ),
        enabled = enabled
    ) {
        Text(text = "Giriş yap")
    }
}