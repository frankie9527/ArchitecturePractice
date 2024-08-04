package com.sixth.space.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.Row


import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.sixth.space.R
import com.sixth.space.model.RemoteViewModel

@Composable
fun VideoRecommendScreen(
    navController: NavHostController,
    viewModel: RemoteViewModel
) {
    Column {
        head()
        body()
    }
}

@Preview
@Composable
fun head() {
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val (title, releaseDate, category, description) = createRefs()
        val cs = createRef()
        Text(
            text = "泰国爆笑神广，iPhone只需「碰一碰」",
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier
                .padding(start = 8.dp, top = 16.dp, end = 8.dp)
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )
        Text(
            text = "2023/02/07 12:00",
            fontSize = 12.sp,
            color = Color.LightGray,
            modifier = Modifier
                .padding(start = 8.dp, top = 4.dp)
                .constrainAs(releaseDate) {
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = "# travel",
            fontSize = 12.sp,
            color = Color.LightGray,
            modifier = Modifier
                .padding(start = 8.dp, top = 4.dp)
                .constrainAs(category) {
                    top.linkTo(title.bottom)
                    start.linkTo(releaseDate.end)
                }
        )
        Text(
            text = "当一位男子在机场对一位陌生人一见钟情，然而地板突然塌下他掉入了坑里，他在坑里不断叹气怕女孩走远。幸运的是，女孩居然朝坑里走来，如此远的距离怎么交换联系方式呢？别紧张，这是 iPhone！两台 iPhone 碰一碰就可以和对方交换名片了。",
            fontSize = 12.sp,
            color = Color.LightGray,
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp, end = 8.dp)
                .constrainAs(description) {
                    top.linkTo(releaseDate.bottom)
                    start.linkTo(parent.start)
                }
        )
        ConstraintLayout(
            Modifier
                .padding(top = 10.dp)
                .constrainAs(cs) {
                    top.linkTo(description.bottom)
                    start.linkTo(parent.start)
                }) {
            val (imgHead, author, authorDescription, rowFollow) = createRefs()
            AsyncImage(
                model = "",
                modifier = Modifier
                    .padding(start = 8.dp)
                    .width(40.dp)
                    .height(40.dp)
                    .clip(CircleShape)
                    .constrainAs(imgHead) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                contentDescription = "imgHead",
                placeholder = painterResource(R.mipmap.blurry),
                error = painterResource(R.mipmap.blurry),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "frankie",
                color = Color.White,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .constrainAs(author) {
                        top.linkTo(imgHead.top)
                        start.linkTo(imgHead.end)
                    }
            )

            Text(
                text = "this a author description and what do u want to know,this a author description and what do u want to know",
                maxLines = 1,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 8.dp, end = 100.dp)
                    .constrainAs(authorDescription) {
                        bottom.linkTo(imgHead.bottom)
                        start.linkTo(imgHead.end)
                    }
            )

            Row(
                Modifier
                    .padding(end = 16.dp)
                    .constrainAs(rowFollow) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)

                    }) {
                Image(
                    painter = painterResource(R.drawable.baseline_add_24),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 2.4.dp, end = 2.dp)
                        .width(8.dp)
                        .height(8.dp)
                )
                Text(
                    text = "follow",
                    maxLines = 1,
                    fontSize = 12.sp,
                    color = Color.White,
                )
            }
        }
    }
}

@Preview
@Composable
fun body() {

}



