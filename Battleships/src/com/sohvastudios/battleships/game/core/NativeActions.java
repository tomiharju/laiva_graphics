package com.sohvastudios.battleships.game.core;

public interface NativeActions {

	public void createProgressDialog(String title, String message, boolean cancelable);
	public void createProgressDialog(String title, String message, boolean cancelable, CancelListener listener);
	public void dismissProgressDialog();
<<<<<<< HEAD
	public void launchGameIntent();
	public void createConfirmDialog(String string, String string2,
			String string3, String string4, ConfirmListener confirmListener);
=======
>>>>>>> Changes reflecting core project.
	
	public void createConfirmDialog(String title, String message, String yes, String no, final ConfirmListener confirmListener);
}
