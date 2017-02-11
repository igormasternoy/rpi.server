package com.masternoy.rpi.server.protocol;

public class XBeePacket {
	
	private Short length;
	private Byte frameType;
	private Long serialAddress;
	private Short sourceNetworkAddress;
	private Byte receiveOpts;
	private Byte numberOfSampleSets;
	private Short digitalSampleSets;	
	private Byte analogChannelMask;
	private Short digitalSampleData;
	private Byte analogSampleData;
	private Byte pinsADC;
	private Byte checkSum;
	
	public XBeePacket(){
		
	}

	public Short getLength() {
		return length;
	}


	public void setLength(Short length) {
		this.length = length;
	}


	public Byte getFrameType() {
		return frameType;
	}


	public void setFrameType(Byte frameType) {
		this.frameType = frameType;
	}


	public Long getSerialAddress() {
		return serialAddress;
	}


	public void setSerialAddress(Long serialAddress) {
		this.serialAddress = serialAddress;
	}


	public Short getSourceNetworkAddress() {
		return sourceNetworkAddress;
	}


	public void setSourceNetworkAddress(Short sourceNetworkAddress) {
		this.sourceNetworkAddress = sourceNetworkAddress;
	}


	public Byte getReceiveOpts() {
		return receiveOpts;
	}


	public void setReceiveOpts(Byte receiveOpts) {
		this.receiveOpts = receiveOpts;
	}


	public Byte getNumberOfSampleSets() {
		return numberOfSampleSets;
	}


	public void setNumberOfSampleSets(Byte numberOfSampleSets) {
		this.numberOfSampleSets = numberOfSampleSets;
	}


	public Short getDigitalSampleSets() {
		return digitalSampleSets;
	}


	public void setDigitalSampleSets(Short digitalSampleSets) {
		this.digitalSampleSets = digitalSampleSets;
	}


	public Byte getAnalogChannelMask() {
		return analogChannelMask;
	}


	public void setAnalogChannelMask(Byte analogChannelMask) {
		this.analogChannelMask = analogChannelMask;
	}


	public Short getDigitalSampleData() {
		return digitalSampleData;
	}


	public void setDigitalSampleData(Short digitalSampleData) {
		this.digitalSampleData = digitalSampleData;
	}


	public Byte getAnalogSampleData() {
		return analogSampleData;
	}


	public void setAnalogSampleData(Byte analogSampleData) {
		this.analogSampleData = analogSampleData;
	}


	public Byte getPinsADC() {
		return pinsADC;
	}


	public void setPinsADC(Byte pinsADC) {
		this.pinsADC = pinsADC;
	}


	public Byte getCheckSum() {
		return checkSum;
	}


	public void setCheckSum(Byte checkSum) {
		this.checkSum = checkSum;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("XBeePacket [length=");
		builder.append(length);
		builder.append(", frameType=");
		builder.append(frameType);
		builder.append(", serialAddress=");
		builder.append(serialAddress);
		builder.append(", sourceNetworkAddress=");
		builder.append(sourceNetworkAddress);
		builder.append(", receiveOpts=");
		builder.append(receiveOpts);
		builder.append(", numberOfSampleSets=");
		builder.append(numberOfSampleSets);
		builder.append(", digitalSampleSets=");
		builder.append(digitalSampleSets);
		builder.append(", analogChannelMask=");
		builder.append(analogChannelMask);
		builder.append(", digitalSampleData=");
		builder.append(digitalSampleData);
		builder.append(", analogSampleData=");
		builder.append(analogSampleData);
		builder.append(", pinsADC=");
		builder.append(pinsADC);
		builder.append(", checkSum=");
		builder.append(checkSum);
		builder.append("]");
		return builder.toString();
	}
	

}
