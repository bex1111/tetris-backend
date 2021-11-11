package org.bexterlab.tetrisbackend.controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class UdpClient {


    public List<String> receivedMessage = new ArrayList<>();
    private DatagramSocket socket;


    public UdpClient() throws SocketException {
        socket = new DatagramSocket(10080);

    }

    public String listenMessage() throws IOException {
        byte[] buf = new byte[256];
        InetSocketAddress inetAddress = new InetSocketAddress("localhost", 9080);
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, inetAddress);
        socket.receive(packet);
        return new String(
                packet.getData(), 0, packet.getLength());
    }

    public void close() {
        socket.close();
    }


}
