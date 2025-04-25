package com.yigitmidye.app.admin.presentation.screen.home.main


import android.content.Intent
import androidx.compose.foundation.indication
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yigitmidye.app.admin.R
import com.yigitmidye.app.admin.SettingActivity
import com.yigitmidye.app.admin.presentation.component.top_app_bar.MainTopAppBar
import com.yigitmidye.app.admin.presentation.screen.home.approved.ApprovedScreen
import com.yigitmidye.app.admin.presentation.screen.home.cancel.CancelScreen
import com.yigitmidye.app.admin.presentation.screen.home.delivery.DeliveryScreen
import com.yigitmidye.app.admin.presentation.screen.home.pending.PendingScreen
import com.yigitmidye.app.admin.presentation.screen.home.road.RoadScreen
import com.yigitmidye.app.admin.util.currentScreen
import com.yigitmidye.app.admin.util.tabRowOptions
import kotlinx.coroutines.launch


@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = tabRowOptions()
    val pager = rememberPagerState(initialPage = 0) { options.size }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            MainTopAppBar(
                text = currentScreen(index = selectedIndex),
                settingClick = {
                    val intent = Intent(context, SettingActivity::class.java)
                    context.startActivity(intent)
                },
                queryClick = {}
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
        ) {
            ScrollableTabRow(
                selectedTabIndex = pager.currentPage,
                edgePadding = 0.dp,
                modifier = modifier.fillMaxWidth(),
                containerColor = colorResource(R.color.white),
                contentColor = colorResource(R.color.app_const_color),
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = modifier.tabIndicatorOffset(tabPositions[pager.currentPage]),
                        color = colorResource(R.color.app_const_color_1)
                    )
                }
            ) {
                options.forEachIndexed { index, value ->
                    Tab(
                        selected = pager.pageCount == index,
                        text = { Text(text = value) },
                        onClick = {
                            coroutineScope.launch {
                                pager.animateScrollToPage(index)
                            }
                        },
                        unselectedContentColor = Color.DarkGray,
                    )
                }
            }
            HorizontalPager(
                state = pager,
                modifier = modifier.fillMaxSize()
            ) { page ->
                when (page) {
                    0 -> {
                        PendingScreen(
                            modifier = modifier.weight(1f)
                        )
                    }

                    1 -> {
                        ApprovedScreen(
                            modifier = modifier.weight(1f)
                        )
                    }

                    2 -> {
                        RoadScreen()
                    }

                    3 -> {
                        DeliveryScreen()
                    }

                    4 -> {
                        CancelScreen()
                    }
                }
            }
        }
    }
}
