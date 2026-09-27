package com.kathsoft.kathpos;

import java.awt.EventQueue;

import com.kathsoft.kathpos.app.view.Fr_LogIn;

public class App {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fr_LogIn frame = new Fr_LogIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
