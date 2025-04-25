package com.yigitmidye.app.admin.presentation.screen.home.pending


import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.yigitmidye.app.admin.DetailActivity
import com.yigitmidye.app.admin.presentation.component.card.OrdersCard
import com.yigitmidye.app.admin.presentation.component.util.EmptyListItem
import com.yigitmidye.app.admin.presentation.component.util.ErrorItem
import com.yigitmidye.app.admin.presentation.component.util.LoadingItem

@Composable
fun PendingScreen(
    modifier: Modifier = Modifier,
    pendingViewModel: PendingViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val pendingUiState by pendingViewModel.pendingUiState.collectAsState()

    if (pendingUiState.loading) {
        LoadingItem(
            modifier = modifier.fillMaxSize().background(color = Color.White)
        )
    }

    pendingUiState.errorMessage?.let {
        ErrorItem(message = it, modifier = modifier)
    }
    pendingUiState.pendingList?.let { pendingList ->

        if (pendingList.isNotEmpty()) {
            LazyColumn(
                modifier = modifier.fillMaxSize().background(color = Color.White)
            ) {
                items(items = pendingList, key = {it.documentId}) { ordersModel ->
                    OrdersCard(
                        ordersModel = ordersModel,
                        onClick = { documentId ->
                            val intent = Intent(context,DetailActivity::class.java).apply {
                                putExtra("documentId",documentId)
                            }
                            context.startActivity(intent)
                        }
                    )
                }
            }
        }else {
            EmptyListItem(
                modifier = modifier.fillMaxSize().background(color = Color.White)
            )
        }
    }
}