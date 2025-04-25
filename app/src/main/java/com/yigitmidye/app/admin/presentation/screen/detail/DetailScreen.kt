package com.yigitmidye.app.admin.presentation.screen.detail

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.yigitmidye.app.admin.R
import com.yigitmidye.app.admin.domain.model.OrdersModel
import com.yigitmidye.app.admin.presentation.component.card.DetailCard
import com.yigitmidye.app.admin.presentation.component.top_app_bar.DetailTopAppBar
import com.yigitmidye.app.admin.presentation.component.util.ErrorItem
import com.yigitmidye.app.admin.presentation.component.util.LoadingItem

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val activity = (context as? Activity?)
    val intent = (context as? Activity)?.intent

    val documentId = intent?.getStringExtra("documentId") ?: ""

    val detailUiState by detailViewModel.detailUiState.collectAsState()

    LaunchedEffect(Unit) {
        detailViewModel.fetchOrderDetail(documentId = documentId)
    }

    Scaffold(
        topBar = {
            DetailTopAppBar(
                onBack = {
                    activity?.finish()
                }
            )
        }
    ) { paddingValues ->
        if (detailUiState.loading) {
            LoadingItem(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = colorResource(R.color.white))
                    .padding(paddingValues = paddingValues)
            )
        }
        detailUiState.errorMessage?.let {
            ErrorItem(
                message = it,
                modifier = modifier
                    .fillMaxSize()
                    .background(color = colorResource(R.color.white))
                    .padding(paddingValues = paddingValues)
            )
        }
        detailUiState.order?.let { orderModel ->
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = colorResource(R.color.white))
                    .padding(paddingValues = paddingValues)
            ) {
                item {
                    DetailCard(
                        ordersModel = orderModel,
                        updateStatus = { status ->

                        }
                    )
                }
            }
        }
    }
}

