package com.yigitmidye.app.admin.presentation.component.text_field

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.yigitmidye.app.admin.R

@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    value : String,
    onValeChange : (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
) {
    OutlinedTextField(
        value = value,
        onValueChange = {onValeChange(it)},
        shape = RoundedCornerShape(20.dp),
        maxLines = 1,
        singleLine = true,
        modifier = modifier.fillMaxWidth().padding(horizontal = 20.dp),
        label = {
            Text(
                text = "E-posta"
            )
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.DarkGray,
            unfocusedBorderColor = colorResource(R.color.black)
        )
    )
}