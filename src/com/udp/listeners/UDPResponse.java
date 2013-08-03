package com.udp.listeners;

public interface UDPResponse {
	void onResponse(byte[] data);
	void onError(String error);
}
