package com.yigitmidye.app.admin.presentation.screen.home.road

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
fun RoadScreen(
    modifier: Modifier = Modifier,
    roadViewModel: RoadViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val roadUiState by roadViewModel.roadUiState.collectAsState()

    //    LaunchedEffect(Unit) { roadViewModel.fetchRoadList() }

    if (roadUiState.loading) {
        LoadingItem(
            modifier = modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.white))
        )
    }

    roadUiState.errorMessage?.let { error ->
        ErrorItem(
            message = error,
            modifier = modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.white))
        )
    }

    roadUiState.roadList?.let { roadList ->
        if (roadList.isNotEmpty()) {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = colorResource(R.color.white))
            ) {
                items(items = roadList, key = { it.documentId }) { ordersModel ->
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
        } else {
            EmptyListItem(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = colorResource(R.color.white))
            )
        }
    }
}