package com.codehacks.pkg2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Sender {

    public Sender() throws Exception {
        DatagramSocket socket = new DatagramSocket();
        Scanner keyboard = new Scanner(System.in);

        while (true) {
            System.out.print("Enter your message: ");
            String message = keyboard.nextLine();
            if (message.equals("bye")) {
                break;
            }
            byte[] buffer = message.getBytes();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length,
                    InetAddress.getByName("127.0.0.1"), 2021);
            socket.send(packet);
            System.out.println("Sent: " + message);

            // Receiving part
            buffer = new byte[1500];
            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            message = new String(buffer).trim();
            System.out.println("Received: " + message);
        }
        System.out.println("Thanks for the time");
    }

    public static void main(String[] args) {
        try {
            new Sender();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
