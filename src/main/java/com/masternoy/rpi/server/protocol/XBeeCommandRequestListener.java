package com.masternoy.rpi.server.protocol;

/**
 * Callback listener on successful command execution 
 * 
 * @author imasternoy
 *
 */
public abstract class XBeeCommandRequestListener {
	
	private	XBeeCommandRequest request;
	
	public XBeeCommandRequestListener(XBeeCommandRequest request) {
		this.request = request;
	}
	
	/**
	 * Method with a result of successful command
	 * @param packet
	 */
	public abstract void onSuccess(XBeePacket packet);
	
	/**
	 * Method with a result of failed command
	 * @param wasEvicted
	 */
	public abstract void onFailed(boolean wasEvicted);
	
	public XBeeCommandRequest getRequest() {
		return request;
	}
	

}
