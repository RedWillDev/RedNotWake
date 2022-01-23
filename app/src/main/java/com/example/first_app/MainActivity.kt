package com.example.first_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress



class SoftOptions {
    var RemoteHost: String = "192.168.1.20"
    var RemotePort: Int = 28075
    constructor()
    init{}
}

val Settings = SoftOptions()


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun TurnOff(view: View) {
        sendUDP("TurnOff")
    }

    fun TurnOn(view: android.view.View) {
        sendUDP("TurnON")
    }
}

fun sendUDP(messageStr: String) {
    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)
    try {
        //Open a port to send the package
        val socket = DatagramSocket()
        socket.broadcast = true
        val sendData = messageStr.toByteArray()
        val sendPacket = DatagramPacket(sendData, sendData.size, InetAddress.getByName(Settings.RemoteHost), Settings.RemotePort)
        socket.send(sendPacket)
        println("fun sendBroadcast: packet sent to: " + InetAddress.getByName(Settings.RemoteHost) + ":" + Settings.RemotePort)
    } catch (e: IOException) {
        //            Log.e(FragmentActivity.TAG, "IOException: " + e.message)
    }
}