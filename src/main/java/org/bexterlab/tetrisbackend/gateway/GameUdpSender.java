package org.bexterlab.tetrisbackend.gateway;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.List;


public class GameUdpSender {

    private final DatagramSocket socket;

    public GameUdpSender(int port) {
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(String text, List<String> hostNameList) {
        hostNameList.forEach(x -> sendSocketToHost(text, x));

    }

    private void sendSocketToHost(String text, String hostName) {
        InetSocketAddress inetAddress = new InetSocketAddress(hostName, 10080);
        DatagramPacket packet = new DatagramPacket(text.getBytes(), text.length(), inetAddress);
        try {
            socket.send(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
