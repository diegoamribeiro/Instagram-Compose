package com.dmribeiro87.instagram_compose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.dmribeiro87.instagram_compose.R
import com.dmribeiro87.instagram_compose.model.Post
import com.dmribeiro87.instagram_compose.model.Stories
import com.dmribeiro87.instagram_compose.model.User
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@Composable
fun HomeScreen() {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppToolBar()
        StoriesSection(storyList = getStories())
        Divider(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .height(2.dp))
        PostSection(postList =  getPostData())
    }

}

@Composable
fun PostSection(
    modifier: Modifier = Modifier,
    postList: List<Post>
) {

    LazyColumn{
        items(postList){ post ->
            PostItem(modifier = modifier,
                post = Post(profile = R.drawable.profile_img,
                userName = "dinoknot",
                postImageList = listOf(
                    R.drawable.profile_img,
                    R.drawable.profile_img,
                    R.drawable.profile_img,
                    R.drawable.profile_img
                ),
                description = post.description,
                likedBy = post.likedBy
                )
            )
            PostItem(modifier = modifier,
                post = Post(profile = R.drawable.profile_img,
                    userName = "dinoknot",
                    postImageList = listOf(
                        R.drawable.profile_img,
                        R.drawable.profile_img,
                        R.drawable.profile_img,
                        R.drawable.profile_img
                    ),
                    description = "description",
                    likedBy = post.likedBy)
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PostItem(
    modifier: Modifier,
    post: Post
) {
    val pagerState = rememberPagerState()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){
            Row(
                modifier = modifier
                    .align(CenterStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = post.profile),
                    contentDescription = "",
                    modifier = modifier
                        .size(30.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = post.userName,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .width(100.dp)
                        .padding(10.dp),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold
                )
            }
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "more",
                modifier = modifier.align(Alignment.CenterEnd)
            )
        }

        PostCarousel(post.postImageList, pagerState)
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)){
            Row(
                modifier = modifier.align(CenterStart)
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_heart), contentDescription = "like", modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Icon(painter = painterResource(id = R.drawable.ic_comment), contentDescription = "message", modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Icon(painter = painterResource(id = R.drawable.ic_share), contentDescription = "share", modifier = Modifier.size(24.dp))
            }

            if (pagerState.pageCount > 1){
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    activeColor = Color("#32B5FF".toColorInt()),
                    inactiveColor = Color("#C4C4C4".toColorInt()),
                    modifier = modifier.align(Center)
                )
            }

            Icon(painter = painterResource(id = R.drawable.ic_bookmark),
                contentDescription = "share", modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterEnd))
        }

        LikeSection(post.likedBy)

        val annotatedString = buildAnnotatedString {
            withStyle(style = SpanStyle(Color.Black, fontWeight = FontWeight.Bold)){
                append("${post.userName} ")
            }
            append(post.description)
        }
            Text(
                text = annotatedString,
                fontSize = 12.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                modifier = Modifier.padding(horizontal = 10.dp)
            )



    }
}

@Composable
fun LikeSection(likedBy: List<User>) {
    if (likedBy.size > 10){
        Text(
            text = "$likedBy likes",
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }else if (likedBy.size == 1){
        Text(
            text = "liked by ${likedBy[0].userName}",
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    } else{
        Row(verticalAlignment = CenterVertically, modifier = Modifier.padding(horizontal = 10.dp)) {
            PostLikedViewByProfile(likedBy)
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = "liked by ${likedBy[0].userName} and ${likedBy.size - 1} others",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )
        }

    }
}

@Composable
fun PostLikedViewByProfile(
    likedBy: List<User>
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy((-5).dp)
    ){
        items(likedBy.take(4)){ likeBy ->
            Image(
                painter = painterResource(id = likeBy.profile),
                contentDescription = "profile", modifier = Modifier
                    .size(28.dp)
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = CircleShape
                    )
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PostNudgeCount(
    modifier: Modifier = Modifier,
    pagerState: PagerState
) {
    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(Color.Black.copy(0.3f))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(text = "${pagerState.currentPage + 1}", color = Color.White, fontSize = 11.sp)
        Text(text = "/", color = Color.White, fontSize = 11.sp)
        Text(text = pagerState.pageCount.toString(), color = Color.White, fontSize = 11.sp)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PostCarousel(
    postImageList: List<Int>,
    pagerState: PagerState
) {
    Box(modifier = Modifier.fillMaxWidth()){

        HorizontalPager(
            state = pagerState,
            count = postImageList.size,
            modifier = Modifier.fillMaxWidth()
        ) { currentPage ->
            Image(
                painter = painterResource(id = postImageList[currentPage]),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(375.dp),
                contentScale = ContentScale.Crop
            )
        }

        if (pagerState.pageCount > 1){
            PostNudgeCount(
                modifier = Modifier
                    .align(TopEnd)
                    .padding(10.dp),
                pagerState = pagerState)
        }
    }
}

@Composable
fun StoriesSection(
    modifier: Modifier = Modifier,
    storyList: List<Stories>
) {
    LazyRow{
        items(storyList){ story ->
            StoryItem(modifier = modifier, story = story)
        }
    }
}

@Composable
fun StoryItem(
    modifier: Modifier,
    story: Stories
) {
    Column(
        modifier = modifier.padding(5.dp)
    ) {
        Image(
            painter = painterResource(id = story.profile),
            contentDescription = "profile", modifier = modifier
                .size(60.dp)
                .border(
                    width = 2.dp,
                    brush = Brush.linearGradient(
                        listOf(
                            Color("#DE0046".toColorInt()),
                            Color("#F7A34B".toColorInt())
                        )
                    ),
                    shape = CircleShape
                )
                .padding(5.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = modifier.height(4.dp))
        Text(
            text = story.userName,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = modifier.width(60.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@Composable
fun AppToolBar() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)){
        Image(
            painter = painterResource(id = R.drawable.instagram_logo_svg),
            contentDescription = "logo",
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .align(Alignment.TopStart)
            )
        Row(modifier = Modifier.align(Alignment.CenterEnd)) {
            Icon(painter = painterResource(id = R.drawable.ig_add), contentDescription = "add", modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(20.dp))
            Icon(painter = painterResource(id = R.drawable.ic_heart), contentDescription = "add", modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(20.dp))
            Icon(painter = painterResource(id = R.drawable.ic_messenger), contentDescription = "add", modifier = Modifier.size(24.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    AppToolBar()
}

@Preview(showBackground = true)
@Composable
fun InstaProfilePreview() {
    StoryItem(modifier = Modifier, story = Stories(
        "", 0
    ))
}

@Preview(showBackground = true)
@Composable
fun PostSectionPreview() {
    PostSection(
        modifier = Modifier,
        listOf()
        )
}

fun getStories() = listOf(
    Stories(userName = "dinoknot", R.drawable.profile_img),
    Stories(userName = "dinoknot", R.drawable.profile_img),
    Stories(userName = "dinoknot", R.drawable.profile_img),
    Stories(userName = "dinoknot", R.drawable.profile_img),
    Stories(userName = "dinoknot", R.drawable.profile_img),
    Stories(userName = "dinoknot", R.drawable.profile_img),
    Stories(userName = "dinoknot", R.drawable.profile_img),
    Stories(userName = "dinoknot", R.drawable.profile_img),
    Stories(userName = "dinoknot", R.drawable.profile_img),
    Stories(userName = "dinoknot", R.drawable.profile_img)
)

fun getPostData() = listOf(
    Post(
        profile = R.drawable.profile_img,
        userName = "dinoknot",
        postImageList = listOf(),
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit sit amet, consectetur adipiscing elit",
        likedBy = listOf(
            User(
                R.drawable.profile_img,
                "dinoknot"
            ),
            User(
                R.drawable.profile_img,
                "dinoknot"
            ),
            User(
                R.drawable.profile_img,
                "dinoknot"
            )
        )
    )
)

@Preview(showBackground = true)
@Composable
fun NudgeCountPreview() {
    //NudgeCount()
}
