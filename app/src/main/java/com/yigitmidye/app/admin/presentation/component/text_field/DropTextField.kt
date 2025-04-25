package com.yigitmidye.app.admin.presentation.component.text_field

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import com.yigitmidye.app.admin.R
import com.yigitmidye.app.admin.util.dropTextFieldItem

@Composable
fun DropTextField(
    modifier: Modifier = Modifier,
    status: String,
    updateStatus : (String) -> Unit
) {
    val localDensity = LocalDensity.current
    var selectedStatus by remember { mutableStateOf(status) }
    var textFieldWith by remember { mutableStateOf(0) }
    var expanded by remember { mutableStateOf(false) }

    val option = dropTextFieldItem()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        OutlinedTextField(
            value = selectedStatus,
            onValueChange = {},
            modifier = modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    textFieldWith = it.size.width
                },
            enabled = false,
            readOnly = false,
            maxLines = 1,
            singleLine = true,
            trailingIcon = {
                IconButton(
                    onClick = { expanded = !expanded }
                ) {
                    if (expanded) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = null,
                            tint = colorResource(R.color.app_const_color)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = colorResource(R.color.app_const_color)
                        )
                    }
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.LightGray,
                focusedBorderColor = colorResource(R.color.app_const_color),
                focusedLabelColor = Color.Black,
                disabledBorderColor = Color.DarkGray,
                disabledTextColor = Color.DarkGray,
                unfocusedLabelColor = Color.DarkGray,
                cursorColor = Color.DarkGray,
            )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = modifier.width(with(localDensity){textFieldWith.toDp()}),
            containerColor = colorResource(R.color.white)
        ) {
            option.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        selectedStatus = item
                        expanded = false
                        updateStatus(item)
                    }
                )
            }
        }
    }
}