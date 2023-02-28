package tech.mobiledeveloper.mfti.screen.onboarding

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController

@Composable
fun OnboardingScreen(navController: NavController) {
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                navController.navigate("main") {
                    popUpTo("onboarding") {
                        inclusive = true
                    }
                }
            }
        }

    Box(modifier = Modifier.fillMaxSize()) {
        Button(modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .fillMaxWidth()
            .align(Alignment.BottomStart), onClick = {
            launcher.launch(android.Manifest.permission.ACCESS_COARSE_LOCATION)
        }) {
            Text("Access to Location")
        }
    }

    val context = LocalContext.current
    LaunchedEffect(key1 = Unit, block = {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) -> {
                navController.navigate("main") {
                    popUpTo("onboarding") {
                        inclusive = true
                    }
                }
            }
        }
    })
}
