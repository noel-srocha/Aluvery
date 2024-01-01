package dev.noelsrocha.aluvery.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.noelsrocha.aluvery.R
import dev.noelsrocha.aluvery.ui.theme.Purple500
import dev.noelsrocha.aluvery.ui.theme.Teal200


@Composable
fun ProductItemCard() {
    Surface(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(15.dp),
        shadowElevation = 4.dp
    ) {
        Column(
            Modifier
                .width(200.dp)
                .heightIn(250.dp, 300.dp)
        ) {
            val imageSize = 100.dp

            Box(
                Modifier
                    .height(imageSize)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Purple500, Teal200)
                        )
                    )
                    .fillMaxWidth()
            ) {
                Image(
                    contentDescription = null,
                    modifier = Modifier
                        .size(imageSize)
                        .offset(0.dp, imageSize / 2)
                        .clip(shape = CircleShape)
                        .align(Alignment.BottomCenter),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                )
            }
            Spacer(modifier = Modifier.height(imageSize / 2))
            Column(
                Modifier
                    .padding(16.dp)
            ) {
                Text(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    text = LoremIpsum(50).values.first(),
                )
                Text(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.padding(top = 8.dp),
                    text = "R$ 59,90"
                )
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ProductItemCardPreview() {
    ProductItemCard()
}