package uz.context.broadcastreceiverb_13.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi

class MyBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (isConnectingToInternet(context!!)) {
            Toast.makeText(context, "Internet Connected", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Internet Disconnected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isConnectingToInternet(context: Context): Boolean {
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}