package com.anime.live_wallpapershd.presentation.login

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anime.live_wallpapershd.R
import com.anime.live_wallpapershd.common.Constants.CLIENT
import com.anime.live_wallpapershd.navgraph.Screen
import com.anime.live_wallpapershd.presentation.wallpapers.components.LoadRefreshItem
import com.anime.live_wallpapershd.ui.fonts.Fonts
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.util.UUID


@Composable
fun LoginScreen(
    navController: NavController
)
{
    Column(
        modifier = Modifier
           .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        TopBarLogin(navController)
        WelcomeSection()
        Spacer(modifier = Modifier.height(20.dp))
        GoogleSignIn(navController)

    }
}

@Composable
fun TopBarLogin(
    navController: NavController
)
{
        Row (
            verticalAlignment = Alignment.CenterVertically,

            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = {
                    navController.navigateUp()
                },
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(25.dp)
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.close),
                    contentDescription = "Back"
                )
            }
        }
}

@Composable
fun WelcomeSection()
{
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()

        ){
            Image(
                painter = painterResource(id = R.drawable.ic_splash) ,
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
        }
        TextSection()
    }
}

@Composable
fun TextSection()
{
    Column (
        modifier = Modifier
           .fillMaxWidth()
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
               .fillMaxWidth()

        ){
            Text(
                text = "Welcome to Aniwall",
                color = Color.Black,
                fontFamily = Fonts.fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 21.sp,
                textAlign = TextAlign.Justify

            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Explore cool anime wallpapers and videos for your phone Home screen and Lock screen.",
            color = Color.Gray,
            fontFamily = Fonts.fontFamily,
            fontWeight = FontWeight.Thin,
            fontSize = 16.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(horizontal= 20.dp)

        )

    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun GoogleSignIn(
    navController: NavController,
    signInViewModel: SignInViewModel = hiltViewModel(),

) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var isLoggingIn by remember { mutableStateOf(false) }
    val login: () -> Unit = {
        val credentialManager = CredentialManager.create(context)
        val rawNonce = UUID.randomUUID().toString()
        val bytes = rawNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        val hashedNonce = digest.fold("") { str, it -> str + "%02x".format(it) }
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(CLIENT)
            .setNonce(hashedNonce)
            .build()

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
        isLoggingIn = true
        coroutineScope.launch {
            try {
                val result = credentialManager.getCredential(
                    request = request,
                    context = context,
                )
                val credential = result.credential
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                val googleIdToken = googleIdTokenCredential.idToken
                val profileImage = googleIdTokenCredential.profilePictureUri.toString()

                Log.i("GoogleSignIn", googleIdToken)

                    signInViewModel.signInWithGoogle(googleIdToken, onSuccess = { token,userId ->
                        Prefs.putString("google_id_token", googleIdToken)
                        Prefs.putString("token_auth", token)
                        Prefs.putInt("user_id", userId)
                        Prefs.putString("profile_image", profileImage)
                        Toast.makeText(context, "You Are Signed In", Toast.LENGTH_SHORT).show()
                        navController.navigate(Screen.HomeScreen.route){
                            popUpTo(0)
                        }

                    }, onError = { error ->
                        Log.e("GoogleSignIn", "Sign in failed", error)
                        Toast.makeText(context, "Failed to sign in", Toast.LENGTH_SHORT).show()
                    })
            } catch (e: GetCredentialException) {
                Toast.makeText(context, e.message.toString(), Toast.LENGTH_SHORT).show()
            } catch (e: GoogleIdTokenParsingException) {
                Toast.makeText(context, e.message.toString(), Toast.LENGTH_SHORT).show()
            }


        }

    }


    if (isLoggingIn)
    {
        LoadRefreshItem()
    }else{
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {

            Button(onClick = login) {
                Icon(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Login With Google",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }




}






