package com.smartchart.cn.mpandroidchart.utils;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * @author 张海洋
 * @Date on 2018/09/27.
 * @org 上海..科技有限公司
 * @describe
 */


public class BaseUtils {


    /**
     * 获得UDP 发送数据
     */
    public static String getUDPSendData(String wifiName, String wifiPwd) {
        return "sjap=" + '"' + wifiName + '"' + ',' + '"' + wifiPwd + '"';
    }
    public static void sendUdp(final String message, final String ipAdrress, final int mPort, final DatagramSocket dataSocket, final X3ck call) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("GGG", "发送的数据---message-> " + message);
                try {
                    byte[] buffer = message.getBytes("UTF-8");
                    //将要发送的数据、要发送到什么地址设置好并打成一个 DatagramPacket 包
                    DatagramPacket sendPack = new DatagramPacket(buffer, buffer.length, new InetSocketAddress(ipAdrress, mPort));
                    dataSocket.send(sendPack);
                    byte[] by = new byte[4096];
                    DatagramPacket dataPacket2 = new DatagramPacket(by, by.length);
                    dataSocket.receive(dataPacket2);
                    String mRevicedata = new String(by, 0, dataPacket2.getLength());
                    call.onSuccess(mRevicedata);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    public interface X3ck {


        void onSuccess(String result);
    }

    public static DatagramSocket initUdpSocket(int port) {
        DatagramSocket dataSocket = null;
        try {
            dataSocket = new DatagramSocket(8266);
            Log.i("NNN", "初始化socket 端口 8266");
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return dataSocket;
    }
}
