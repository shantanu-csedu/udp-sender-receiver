package com.udp.sender;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.udp.listeners.UDPResponse;


public class UDPSender {
	DatagramSocket dsocket;
	
	public UDPSender() {
		try {
			dsocket = new DatagramSocket();
			dsocket.setSoTimeout(10000);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void sndRcvPacket(byte[] msg,String url,int port,UDPResponse response){
		InetAddress address=null;
		try {
			address = InetAddress.getByName(url);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		byte[] recData = new byte[1500];
		DatagramPacket sndPkt = new DatagramPacket(msg, msg.length,address, port);
		DatagramPacket rcvPkt=new DatagramPacket(recData, recData.length);;
		try {
			dsocket.send(sndPkt);
			dsocket.receive(rcvPkt);
			response.onResponse(rcvPkt.getData());
		} catch (IOException e) {
			response.onError(e.getMessage());
		}
		
		
	}
	public void sendPacket(byte[] msg,String url,int port){
		InetAddress address=null;
		try {
			address = InetAddress.getByName(url);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		DatagramPacket sndPkt = new DatagramPacket(msg, msg.length,address,port);
		
		try {
			dsocket.send(sndPkt);
		} catch (SocketException e) {
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
