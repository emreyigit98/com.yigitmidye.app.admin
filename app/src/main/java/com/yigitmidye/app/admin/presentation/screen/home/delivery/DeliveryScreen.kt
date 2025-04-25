package com.yigitmidye.app.admin.presentation.screen.home.delivery

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.yigitmidye.app.admin.DetailActivity
import com.yigitmidye.app.admin.R
import com.yigitmidye.app.admin.presentation.component.card.OrdersCard
import com.yigitmidye.app.admin.presentation.component.util.EmptyListItem
import com.yigitmidye.app.admin.presentation.component.util.ErrorItem
import com.yigitmidye.app.admin.presentation.component.util.LoadingItem

@Composable
fun DeliveryScreen(
    modifier: Modifier = Modifier,
    deliveryViewModel: DeliveryViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val deliveryUiState by deliveryViewModel.deliveryUiState.collectAsState()


    if (deliveryUiState.loading) {
        LoadingItem(modifier = modifier.fillMaxSize().background(color = colorResource(R.color.white)))
    }

    deliveryUiState.errorMessage?.let { error ->
        ErrorItem(message = error, modifier = modifier.fillMaxSize().background(color = colorResource(R.color.white)))
    }

    deliveryUiState.deliveryList?.let { deliveryList ->
        if (deliveryList.isNotEmpty()) {
           LazyColumn(
               modifier = modifier.fillMaxSize().background(color = colorResource(R.color.white))
           ) {
               items(items = deliveryList, key = {it.documentId}) { ordersModel ->
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
           EmptyListItem(modifier = modifier.fillMaxSize().background(color = colorResource(R.color.white)))
        }
    }
}