package com.yigitmidye.app.admin.presentation.screen.query


import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.Timestamp
import com.yigitmidye.app.admin.DetailActivity
import com.yigitmidye.app.admin.R
import com.yigitmidye.app.admin.presentation.component.card.OrdersCard
import com.yigitmidye.app.admin.presentation.component.top_app_bar.QueryTopAppBar
import com.yigitmidye.app.admin.presentation.component.util.EmptyListItem
import com.yigitmidye.app.admin.presentation.component.util.ErrorItem
import com.yigitmidye.app.admin.presentation.component.util.LoadingItem
import java.util.Calendar

@Composable
fun QueryScreen(
    modifier: Modifier = Modifier,
    queryViewModel: QueryViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val activity = (context as? Activity)
    val calendar = Calendar.getInstance()

    val queryUiState by queryViewModel.queryUiState.collectAsState()

    val datePicker = android.app.DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->

            val startCalendar = Calendar.getInstance()
            startCalendar.set(year, month, dayOfMonth, 0, 0, 0)
            startCalendar.set(Calendar.MILLISECOND, 0)

            val endCalendar = Calendar.getInstance()
            endCalendar.set(year, month, dayOfMonth, 23, 59, 59)
            endCalendar.set(Calendar.MILLISECOND, 999)

            val start = Timestamp(startCalendar.time)
            val end = Timestamp(endCalendar.time)

            queryViewModel.fetchQueryDateList(startDate = start, endDate = end)

        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Scaffold(
        topBar = {
            QueryTopAppBar(
                onBackPress = {
                    activity?.finish()
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(
                        text = "Tarih seçin",
                        color = colorResource(R.color.white)
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_date_range_24),
                        contentDescription = null,
                        tint = colorResource(R.color.white)
                    )
                },
                onClick = {
                    datePicker.show()
                },
                containerColor = colorResource(R.color.app_const_color)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .background(color = colorResource(R.color.white))
        ) {

            if (queryUiState.successful == null) {
                Column(
                    modifier = modifier.fillMaxSize().background(color = colorResource(R.color.white)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Seçtiğiniz güne ait kayıtlar burada gözükücektir.",
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp
                    )
                }
            }

            if (queryUiState.loading) {
                LoadingItem(modifier = modifier.fillMaxSize())
            }

            queryUiState.errorMessage?.let { message ->
                ErrorItem(message = message, modifier = modifier.fillMaxSize())
            }

            queryUiState.successful?.let { response ->
                if (response.isEmpty()) {
                    EmptyListItem(modifier = modifier.fillMaxSize())
                }else {
                    LazyColumn(
                        modifier = modifier.fillMaxSize()
                    ) {
                        items(items = response, key = { value -> value.documentId}) { order ->
                            OrdersCard(
                                ordersModel = order,
                                onClick = { documentId ->
                                    val intent = Intent(context,DetailActivity::class.java).apply {
                                        putExtra("documentId",documentId)
                                    }
                                    context.startActivity(intent)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
