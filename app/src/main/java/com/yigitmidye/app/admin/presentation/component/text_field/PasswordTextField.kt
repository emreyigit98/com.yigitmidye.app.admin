package com.yigitmidye.app.admin.presentation.component.text_field

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.yigitmidye.app.admin.R

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value : String,
    onValueChange : (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
) {
    var expanded by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = {onValueChange(it)},
        maxLines = 1,
        singleLine = true,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        visualTransformation = if (expanded) VisualTransformation.None else PasswordVisualTransformation(),
        label = {
            Text(
                text = "Şifre"
            )
        },
        trailingIcon = {
            IconButton(
                onClick = { expanded = !expanded }
            ) {
                when(expanded) {
                    true -> {
                        Icon(
                            painter = painterResource(R.drawable.eye_open), contentDescription = null,
                            modifier = modifier.size(24.dp)
                        )
                    }
                    false -> {
                        Icon(
                            painter = painterResource(R.drawable.eye_close), contentDescription = null,
                            modifier = modifier.size(24.dp)
                        )
                    }
                }
            }
        },
        shape = RoundedCornerShape(20.dp),
        modifier = modifier.fillMaxWidth().padding(horizontal = 20.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.DarkGray,
            unfocusedBorderColor = colorResource(R.color.black)
        )
    )
}