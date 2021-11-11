package org.bexterlab.tetrisbackend.controller;

import org.bexterlab.tetrisbackend.gateway.GameUdpSender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class GameUdpSenderIntTest {

    @Test
    void test1() throws IOException {
        GameUdpSender gameUdpSender = new GameUdpSender(9080);
        UdpClient udpClient = new UdpClient();
        gameUdpSender.send("test", List.of("localhost"));
        gameUdpSender.send("test1", List.of("localhost"));
        gameUdpSender.send("test2", List.of("localhost"));
        gameUdpSender.send("test3", List.of("localhost"));
        Assertions.assertEquals("test", udpClient.listenMessage());
        Assertions.assertEquals("test1", udpClient.listenMessage());
        Assertions.assertEquals("test2", udpClient.listenMessage());
        Assertions.assertEquals("test3", udpClient.listenMessage());
        Assertions.assertEquals("test3", udpClient.listenMessage());
    }
}