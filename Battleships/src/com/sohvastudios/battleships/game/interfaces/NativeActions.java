package com.sohvastudios.battleships.game.interfaces;


public interface NativeActions {

	/**
	 * @param title Dialog title.
	 * @param message Dialog message.
	 * @param cancelable True if dialog can be canceled with back button. Calls CancelListener.cancel().
	 * @param listener Listener for cancel done by user.
	 */
	public void createProgressDialog(String title, String message, boolean cancelable, CancelListener listener);
	
	/**
	 * Dismiss currently displayed progress dialog.
	 */
	public void dismissProgressDialog();

	/**
	 * @param title Dialog title.
	 * @param message Dialog message.
	 * @param yes Text for positive button
	 * @param no Text for negative button
	 * @param confirmListener Listener for positive and negative button presses.
	 */
	public void createConfirmDialog(String title, String message, String yes, String no, final ConfirmListener confirmListener);
	
	/**
	 * @param text Text to be displayed.
	 * @param duration 0 for short duration. 1 for long duration.
	 */
	public void createToast(String text, int duration);
}
