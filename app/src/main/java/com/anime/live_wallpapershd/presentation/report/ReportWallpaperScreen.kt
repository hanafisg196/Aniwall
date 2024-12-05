package com.anime.live_wallpapershd.presentation.report

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.anime.live_wallpapershd.R
import com.anime.live_wallpapershd.ui.fonts.Fonts


@Composable
fun ReportWallpaperScreen(
    navController: NavController
){
    Column(modifier = Modifier.fillMaxWidth())
    {
        Spacer(modifier = Modifier.height(35.dp))
        TopBar()
        Spacer(modifier = Modifier.height(35.dp))
        FormReportEmail()
        Spacer(modifier = Modifier.height(20.dp))
        Button(
        onClick = { /*TODO*/ },
        modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Send Report")
        }
    }

}

@Composable
fun FormReportEmail(){
    EmailField(email = "test@gmail.com"){ }
    Spacer(modifier = Modifier.height(20.dp))
    NameField(name = "Test") { }
    Spacer(modifier = Modifier.height(20.dp))
    DescriptionField(description =
    "Hello Admin\n" +
    "Please check this wallpaper\n" +
    "Name : Wallpaper Name\n" +
    "From : User Name\n" +
    "Describe your reason : ..."
    ) { }
}


@Composable
fun EmailField(
    email: String,
    onEmailChange: (String) -> Unit
){
    TextField(
        value = email,
        onValueChange = onEmailChange,
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Left
        ),
        label = {
            Text(text = "Title")
        },
        placeholder = {
            Text(text = "Your Email")
        },
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp)
    )
}
@Composable
fun NameField(
    name: String,
    onNameChange: (String) -> Unit
){
    TextField(
        value = name,
        onValueChange = onNameChange,
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Left
        ),
        label = {
            Text(text = "Name")
        },
        placeholder = {
            Text(text = "Your Name")
        },
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp)
    )
}

@Composable
fun DescriptionField(
    description: String,
    onDescriptionChange: (String) -> Unit
){
    TextField(
        value = description,
        onValueChange = onDescriptionChange,
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Left
        ),
        label = {
            Text(text = "Description")
        },
        placeholder = {
            Text(text = "Description")
        },
        maxLines = 5,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(start = 30.dp, end = 30.dp)
    )
}



@Composable
fun TopBar(

){
    Row (
        verticalAlignment = Alignment.CenterVertically,

        modifier = Modifier
            .fillMaxWidth()

    ){
        IconButton(
            onClick = {
                //Todo
            },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "Back"
            )
        }
        Text(
            text = "Report Wallpaper",
            overflow = TextOverflow.Ellipsis,
            fontFamily = Fonts.fontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 25.sp,
            modifier = Modifier.padding(start = 30.dp)
        )
    }

}