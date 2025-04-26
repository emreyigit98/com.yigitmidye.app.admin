package com.yigitmidye.app.admin.presentation.component.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yigitmidye.app.admin.R
import com.yigitmidye.app.admin.domain.model.OrdersModel
import com.yigitmidye.app.admin.presentation.component.text_field.DropTextField

@Composable
fun DetailCard(
    modifier: Modifier = Modifier,
    ordersModel: OrdersModel,
    updateStatus: (String) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = colorResource(R.color.app_const_color),
                modifier = modifier.size(26.dp)
            )
            Spacer(modifier = modifier.width(20.dp))
            Text(
                text = "Sipariş durumu",
                fontWeight = FontWeight.W600
            )
        }
            DropTextField(
                status = ordersModel.orderStatus,
                updateStatus = { updateStatus(it) },
                modifier = modifier.padding(vertical = 20.dp)
            )
            HorizontalDivider()
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = colorResource(R.color.app_const_color),
                    modifier = modifier.size(26.dp)
                )
                Spacer(modifier = modifier.width(20.dp))
                Text(
                    text = "İsim",
                    fontWeight = FontWeight.W600
                )
            }
            Text(
                text = ordersModel.customerName,
                modifier = modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                fontWeight = FontWeight.W400,
                fontSize = 12.sp
            )
            HorizontalDivider()
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = null,
                    tint = colorResource(R.color.app_const_color),
                    modifier = modifier.size(26.dp)
                )
                Spacer(modifier = modifier.width(20.dp))
                Text(
                    text = "Telefon",
                    fontWeight = FontWeight.W600
                )
            }
            Text(
                text = ordersModel.phone,
                modifier = modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                fontWeight = FontWeight.W400,
                fontSize = 12.sp
            )
            HorizontalDivider()
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = colorResource(R.color.app_const_color),
                    modifier = modifier.size(26.dp)
                )
                Spacer(modifier = modifier.width(20.dp))
                Text(
                    text = "Teslimat adresi",
                    fontWeight = FontWeight.W600
                )
            }
            Text(
                text = ordersModel.address,
                modifier = modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                fontWeight = FontWeight.W400,
                fontSize = 12.sp
            )
            HorizontalDivider()
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_payment_24),
                    contentDescription = null,
                    tint = colorResource(R.color.app_const_color),
                    modifier = modifier.size(26.dp)
                )
                Spacer(modifier = modifier.width(20.dp))
                Text(
                    text = "Ödeme yöntemi",
                    fontWeight = FontWeight.W600
                )
            }
            Text(
                text = ordersModel.paymentType,
                modifier = modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                fontWeight = FontWeight.W400,
                fontSize = 12.sp
            )
            HorizontalDivider()
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = null,
                    tint = colorResource(R.color.app_const_color),
                    modifier = modifier.size(26.dp)
                )
                Spacer(modifier = modifier.width(20.dp))
                Text(
                    text = "Sipariş notu",
                    fontWeight = FontWeight.W600
                )
            }
            Text(
                text = if (ordersModel.orderNote.isNotEmpty()) ordersModel.orderNote else "Sipariş notu yok",
                modifier = modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                fontWeight = FontWeight.W400,
                fontSize = 12.sp
            )
            HorizontalDivider()
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.order_product_icon),
                    contentDescription = null,
                    tint = colorResource(R.color.app_const_color),
                    modifier = modifier.size(26.dp)
                )
                Spacer(modifier = modifier.width(20.dp))
                Text(
                    text = "Sipariş özeti",
                    fontWeight = FontWeight.W600
                )
            }
            ordersModel.orders.forEach { order ->
                Column {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = modifier.weight(1f)
                        ) {
                            Text(
                                text = "${order.amount}x",
                                fontSize = 12.sp
                            )
                            Spacer(modifier = modifier.width(5.dp))
                            Text(
                                text = order.productName,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 12.sp
                            )
                        }
                        Text(
                            text = "%.2f \u20ba".format(order.totalPrice),
                            fontSize = 12.sp
                        )
                    }
                }
            }

            HorizontalDivider()
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Ara toplam",
                    fontWeight = FontWeight.W600
                )
                Text(
                    text = "%.2f \u20ba".format(ordersModel.totalPrice),
                    fontSize = 12.sp
                )
            }
        }
    }
}