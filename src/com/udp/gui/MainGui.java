package com.udp.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.udp.listeners.UDPResponse;
import com.udp.sender.UDPSender;

import net.miginfocom.swing.MigLayout;

public class MainGui  {
	
	
	public static void main(String args[]){
		
		final UDPSender usender = new UDPSender();
		JFrame frame = new JFrame("UDP Sender");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel topPanel = new JPanel(new MigLayout());
		final JTextField url = new JTextField("192.168.1.145");
		url.setColumns(50);
		final JTextField port = new JTextField("1010");
		port.setColumns(10);
		topPanel.setSize(100, 20);
		topPanel.add(url);
		topPanel.add(port);
		JPanel panel = new JPanel(new MigLayout());
		final JTextArea output = new JTextArea(100, 80);
		JScrollPane scroll = new JScrollPane(output);
		final JTextArea input = new JTextArea(50,80);
		JButton btn = new JButton("Send/receive");
		JButton obtn = new JButton("only Send");
		panel.add(topPanel,"wrap");
		panel.add(scroll,"wrap");
		panel.add(input);
		panel.add(btn);
		panel.add(obtn,"wrap");
		
		frame.add(panel);
		frame.setVisible(true);
		
		final UDPResponse respnose = new UDPResponse() {
			
			@Override
			public void onResponse(byte[] data) {
				
				output.append(new String(data));
				output.append("=======END RESPONSE=======\n");
			}
			
			@Override
			public void onError(String error) {
				output.append("Exception: " + error + "\n");
				
			}
		};
		btn.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				usender.sndRcvPacket(input.getText().getBytes(), url.getText(), Integer.parseInt(port.getText()),respnose );
			}
		});
		
		obtn.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				usender.sendPacket(input.getText().getBytes(), url.getText(), Integer.parseInt(port.getText()) );
			}
		});
	}

	
}
