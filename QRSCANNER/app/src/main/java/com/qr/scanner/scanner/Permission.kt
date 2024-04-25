@file:OptIn(ExperimentalPermissionsApi::class, ExperimentalPermissionsApi::class)

package com.qr.scanner.scanner

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.camera.core.CameraProvider
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.Analyzer
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.common.util.concurrent.ListenableFuture
import java.util.concurrent.TimeUnit


enum class AnalyzerType {
    UNDEFINED, BARCODE, TEXT
}

@Composable
fun MainScreen() {
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
    var analyzerType by remember { mutableStateOf(AnalyzerType.UNDEFINED) }

    if (cameraPermissionState.status.isGranted) {
        CameraScreen()
    } else if (cameraPermissionState.status.shouldShowRationale) {
        Text("Camera Permission permanently denied")
    } else {
        SideEffect {
            cameraPermissionState.run { launchPermissionRequest() }
        }
        Text("No Camera Permission")
    }
}

@Composable
fun CameraScreen() {

    //var cameraClick by remember { mutableStateOf(false) }


    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val localContext = LocalContext.current


    val height:Int =localContext.resources.displayMetrics.heightPixels
    val width:Int =localContext.resources.displayMetrics.widthPixels

    val lifecycleOwner:LifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture: ListenableFuture<ProcessCameraProvider> = remember { ProcessCameraProvider.getInstance(localContext) }
    //val cameraController:LifecycleCameraController=LifecycleCameraController(localContext)
    val selector:CameraSelector = remember {
        CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()
    }
    var previewView = remember{ PreviewView(localContext)}
    var preview:Preview = Preview.Builder().build()

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        //var cameraClick2 by remember { mutableStateOf(false) }
        AndroidView(
            modifier = Modifier
                .requiredHeight(screenHeight.times(0.5f))
                .requiredWidth(screenWidth.times(0.5f)),
            factory = { context ->
                previewView = PreviewView(context)
                //preview:Preview = Preview.Builder().build()
                /*selector:CameraSelector = CameraSelector.Builder()
                    .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                    .build()*/

                preview.setSurfaceProvider(previewView.surfaceProvider)


                val imageAnalysis=ImageAnalysis.Builder().build()
                imageAnalysis.setAnalyzer(
                    ContextCompat.getMainExecutor(localContext),
                    BarcodeAnalyzer(localContext)
                )


                runCatching {
                    cameraProviderFuture.get().bindToLifecycle(
                        lifecycleOwner,
                        selector,
                        preview//,imageAnalysis//,Analyzer(context,cameraProviderFuture,lifecycleOwner,selector, preview)
                    )
                }.onFailure {
                    Log.e("CAMERA", "Camera bind error ${it.localizedMessage}", it)
                }
                /*
                if(cameraClick2){
                    Analyzer(context,cameraProviderFuture,lifecycleOwner,selector, preview)
                    cameraClick2=false
                }
                if (cameraClick2){Toast.makeText(context,"Button Clicked",Toast.LENGTH_SHORT).show()}
                 */
                previewView
            }
        )
        Button(onClick = {
            val imageAnalysis=ImageAnalysis.Builder().build()
            imageAnalysis.setAnalyzer(
                ContextCompat.getMainExecutor(localContext),
                BarcodeAnalyzer(localContext)
            )

            runCatching {

                cameraProviderFuture.get().bindToLifecycle(lifecycleOwner,selector,preview,imageAnalysis)
                //cameraProviderFuture.get().unbind(preview,imageAnalysis)

        }
        }) {
            Text(text = "Scan QR Code")
        }

    }

}
/*
fun Analyzer(
    context: Context,
    cameraProviderFuture: ListenableFuture<ProcessCameraProvider>,
    lifecycleOwner:LifecycleOwner,
    selector:CameraSelector,
    preview:Preview
){

    val barcodeAnalyzer=BarcodeAnalyzer(context)

}

*/


/*


            val imageAnalysis=ImageAnalysis.Builder().build()
            imageAnalysis.setAnalyzer(
                ContextCompat.getMainExecutor(context),
                BarcodeAnalyzer(context)
            )

            runCatching {
                cameraProviderFuture.get().bindToLifecycle(
                    lifecycleOwner,
                    selector,
                    preview,imageAnalysis
                )
            }.onFailure {
                Log.e("CAMERA", "Camera bind error ${it.localizedMessage}", it)
            }

 */