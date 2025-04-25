package com.yigitmidye.app.admin.presentation.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yigitmidye.app.admin.R
import com.yigitmidye.app.admin.domain.model.OrdersModel

@Composable
fun OrdersCard(
    modifier: Modifier = Modifier,
    ordersModel: OrdersModel,
    onClick : (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(ordersModel.documentId) }
            .background(color = colorResource(R.color.white)),
    ) {
        Column(
            modifier = modifier.fillMaxWidth().padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = modifier.fillMaxWidth().padding(horizontal = 10.dp) ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ){
                Icon(
                    painter = painterResource(R.drawable.order_product_icon), contentDescription = null,
                    modifier = modifier.size(30.dp),
                    tint = colorResource(R.color.app_const_color)
                )
                Text(
                    text = ordersModel.orderStatus,
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.W600
                    )
                )
            }
            Text(
                text = ordersModel.address,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.W500
                ),
                modifier = modifier.fillMaxWidth().padding(horizontal = 10.dp)
            )
            Text(
                text = "%.2f \u20ba".format(ordersModel.totalPrice),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.W500
                ),
                modifier = modifier.fillMaxWidth().padding(horizontal = 10.dp)
            )
        }
        HorizontalDivider()
    }
}

@Preview
@Composable
private fun T() {
    val ordersModel = OrdersModel(
        orderStatus = "Onay bekliyor",
        address = "Hacıyusuf mahallesi Santral caddesi no:49/A Yiğit midye Balıkesir/Bandırma",
        totalPrice = 212.00
    )
    OrdersCard(
        ordersModel = ordersModel
    ) {

    }
}