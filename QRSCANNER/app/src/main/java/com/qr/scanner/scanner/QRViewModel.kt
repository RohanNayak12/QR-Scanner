package com.qr.scanner.scanner

import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.barcode.common.Barcode
/*
class QRViewModel(barcode: Barcode) :ViewModel() {
    var boundingRect: Rect = barcode.boundingBox!!
    var qrContent: String = ""
    var qrCodeTouchCallback = { v: View, e: MotionEvent -> false}

    init {
        when (barcode.valueType) {
            Barcode.TYPE_URL -> {

                qrContent = barcode.url!!.url!!
                qrCodeTouchCallback = { v: View, e: MotionEvent ->
                    if (e.action == MotionEvent.ACTION_DOWN && boundingRect.contains(e.getX().toInt(), e.getY().toInt())) {
                        val openBrowserIntent = Intent(Intent.ACTION_VIEW)
                        openBrowserIntent.data = Uri.parse(qrContent)
                        v.context.startActivity(openBrowserIntent)
                    }
                    true // return true from the callback to signify the event was handled
                }
            }
            /*
            Barcode.TYPE_URL ->{
                qrContent=barcode.rawValue!!
                qrCodeTouchCallback={
                    

                }
            } */
            // Add other QR Code types here to handle other types of data,
            // like Wifi credentials.
            else -> {
                qrContent = "Unsupported data type: ${barcode.rawValue.toString()}"
            }
        }
    }
}

*/
