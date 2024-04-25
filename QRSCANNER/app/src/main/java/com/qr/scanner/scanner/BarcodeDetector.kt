package com.qr.scanner.scanner

import androidx.camera.mlkit.vision.MlKitAnalyzer
import androidx.camera.view.CameraController
import androidx.camera.view.CameraController.COORDINATE_SYSTEM_VIEW_REFERENCED
import androidx.camera.view.LifecycleCameraController
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode


/*

val options = FirebaseVisionBarcodeDetectorOptions.Builder()
    .setBarcodeFormats(FirebaseVisionBarcode.FORMAT_QR_CODE)
    .build()
val options2 = BarcodeScannerOptions.Builder()
    .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
    .build()
val barcodeScanner = BarcodeScanning.getClient(options2)



@Composable
fun ScannerUI(){
    var text by remember { mutableStateOf("") }

    val context= LocalContext.current

    val cameraController=LifecycleCameraController(context)
    fun Scanner(){
        cameraController.setImageAnalysisAnalyzer(
            ContextCompat.getMainExecutor(context),
            MlKitAnalyzer(
                listOf(barcodeScanner),
                COORDINATE_SYSTEM_VIEW_REFERENCED,
                ContextCompat.getMainExecutor(context)
            ) { result: MlKitAnalyzer.Result? ->
                // The value of result.getResult(barcodeScanner) can be used directly for drawing UI overlay.
                text= result.toString()

            }
        )
    }

}

*/