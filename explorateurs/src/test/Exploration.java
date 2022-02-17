package test;

import gui.MainGui;

public class Exploration {

public static void main(String[] args) {
	 	
		MainGui game = new MainGui(); 
		Thread guiTread = new Thread(game);
		guiTread.start();
	}
}
