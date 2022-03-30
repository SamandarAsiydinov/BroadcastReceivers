package uz.context.broadcastreceiverb_13

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.auth.api.phone.SmsRetriever
import uz.context.broadcastreceiverb_13.receivers.MyBroadcastReceiver
import uz.context.broadcastreceiverb_13.receivers.NetworkBroadcastReceiver
import uz.context.broadcastreceiverb_13.receivers.SmsBroadcastReceiver
import uz.context.broadcastreceiverb_13.util.toast

class MainActivity : AppCompatActivity() {

    private lateinit var receiver: NetworkBroadcastReceiver
    private lateinit var myBroadcastReceiver: MyBroadcastReceiver
    private lateinit var smsBroadcastReceiver: SmsBroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    private fun initViews() {
        startSmsUserContent()
        registerSmsReceiver()
    }

    private fun registerSmsReceiver() {
        smsBroadcastReceiver = SmsBroadcastReceiver()
        smsBroadcastReceiver.smsBroadcastReceiverListener =
            object : SmsBroadcastReceiver.SmsBroadcastReceiverListener {
                override fun onSuccess(intent: Intent?) {
                    val message = intent?.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE)
                    toast(message!!)
                    Log.d("@@@@@",message)
                }

                override fun onFailure(text: String) {
                    toast(text)
                }
            }
        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
        registerReceiver(smsBroadcastReceiver, intentFilter)
    }

    private fun startSmsUserContent() {
        val client = SmsRetriever.getClient(this)
        client.startSmsUserConsent(null)
    }
}

/*
private fun initViews() {
    myBroadcastReceiver = MyBroadcastReceiver()
    val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
    registerReceiver(myBroadcastReceiver, intentFilter)
}

override fun onDestroy() {
    super.onDestroy()
    unregisterReceiver(myBroadcastReceiver)
}
}
 */

/*
private fun initViews() {
    receiver = NetworkBroadcastReceiver()
}

override fun onStart() {
    super.onStart()
    val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
    registerReceiver(receiver,filter)
}

override fun onDestroy() {
    super.onDestroy()
    unregisterReceiver(receiver)
}
}
 */