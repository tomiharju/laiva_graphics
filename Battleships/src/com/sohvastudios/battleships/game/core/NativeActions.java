package com.sohvastudios.battleships.game.core;

public interface NativeActions {

	public void createProgressDialog(String title, String message,boolean cancelable);
	public void createProgressDialog(String title, String message,boolean cancelable,CancelListener listener);
	public void dismissProgressDialog();
	public void launchGameIntent();
	
}
